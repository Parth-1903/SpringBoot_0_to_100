package com.week_3_ecommerce.order_service.controller;

import com.week_3_ecommerce.order_service.dto.OrderRequestDto;
import com.week_3_ecommerce.order_service.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class OrderController {

	private final OrdersService ordersService;

	@GetMapping("/helloOrders")
	public String helloOrders(){
		return "Hello from Orders service";
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

}
