package com.week_3_ecommerce.order_service.service;

import com.week_3_ecommerce.order_service.dto.OrderRequestDto;
import com.week_3_ecommerce.order_service.entity.Orders;
import com.week_3_ecommerce.order_service.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersService {

	private final OrdersRepository ordersRepository;
	private final ModelMapper modelMapper;

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



}
