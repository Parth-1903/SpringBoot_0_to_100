package com.week_3_ecommerce.order_service.service;

import com.week_3_ecommerce.order_service.clients.InventoryOpenFeignClient;
import com.week_3_ecommerce.order_service.dto.OrderRequestDto;
import com.week_3_ecommerce.order_service.entity.OrderItem;
import com.week_3_ecommerce.order_service.entity.OrderStatus;
import com.week_3_ecommerce.order_service.entity.Orders;
import com.week_3_ecommerce.order_service.repository.OrdersRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersService {

	private final OrdersRepository ordersRepository;
	private final ModelMapper modelMapper;

	private final InventoryOpenFeignClient inventoryOpenFeignClient;

	public List<OrderRequestDto> getAllOrders(){
		log.info("Fetching all orders");
		List<Orders> orders = ordersRepository.findAll();
		return orders.stream().map(order -> modelMapper.map(order, OrderRequestDto.class)).toList();
	}

	public OrderRequestDto getOrderById(Long id){
		log.info("Fetching order with ID: {}",id);
		Orders order = ordersRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
		return modelMapper.map(order, OrderRequestDto.class);
	}

//	@Retry(name = "inventoryRetry", fallbackMethod = "createOrderFallBack")
	@CircuitBreaker(name = "inventoryCircuitBreaker", fallbackMethod = "createdOrderFallBack")
	@RateLimiter(name = "inventoryRateLimiter", fallbackMethod = "createOrderFallBack") // comment out this to use circuit breaker
	public OrderRequestDto createOrders(OrderRequestDto orderRequestDto) {
		Double totalPrice = inventoryOpenFeignClient.reduceStocks(orderRequestDto);

		Orders orders = modelMapper.map(orderRequestDto, Orders.class);
		for(OrderItem orderItem : orders.getItems()){
			orderItem.setOrders(orders);
		}
		orders.setTotalPrice(totalPrice);
		orders.setOrderStatus(OrderStatus.CONFIRMED);

		Orders savedOrders = ordersRepository.save(orders);
		return modelMapper.map(savedOrders, OrderRequestDto.class);
	}

	@Transactional
	public boolean cancelOrders(OrderRequestDto orderRequestDto, Long orderId){
		String answer = inventoryOpenFeignClient.restocks(orderRequestDto);

		Orders orders = ordersRepository.findById(orderId).orElseThrow(() ->
				new RuntimeException("Order doesn't exist with this id: "+ orderId));

		ordersRepository.delete(orders);
		return true;
	}

	public OrderRequestDto createOrderFallBack(OrderRequestDto orderRequestDto, Throwable throwable){
		log.error("Fallback occurred due to : {}", throwable.getMessage());
		return new OrderRequestDto();
	}
}
