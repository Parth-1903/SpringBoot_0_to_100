package com.week_3_ecommerce.order_service.controller;

import com.week_3_ecommerce.order_service.clients.InventoryOpenFeignClient;
import com.week_3_ecommerce.order_service.dto.OrderRequestDto;
import com.week_3_ecommerce.order_service.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class OrderController {

	private final OrdersService ordersService;

	@GetMapping("/helloOrders")
	public String helloOrders(@RequestHeader("X-User-Id") Long userId){
		return "Hello from Orders service, userId: "+userId;
	}

	@GetMapping
	public ResponseEntity<List<OrderRequestDto>> getAllOrders(){
		log.info("Fetching all orders via controller");
		List<OrderRequestDto> orders = ordersService.getAllOrders();
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id){
		log.info("Fetching order with ID: {}",id);
		OrderRequestDto order = ordersService.getOrderById(id);
		return ResponseEntity.ok(order);
	}

	@PostMapping("/create-order")
	public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
		OrderRequestDto orderRequestDto1 = ordersService.createOrders(orderRequestDto);
		return ResponseEntity.ok(orderRequestDto1);
	}

	@PatchMapping("/cancel-order/{orderId}")
	public ResponseEntity<Boolean> cancelOrder(@RequestBody OrderRequestDto orderRequestDto,@PathVariable Long orderId){
		boolean flag = ordersService.cancelOrders(orderRequestDto,orderId);
		return ResponseEntity.ok(flag);
	}

}
