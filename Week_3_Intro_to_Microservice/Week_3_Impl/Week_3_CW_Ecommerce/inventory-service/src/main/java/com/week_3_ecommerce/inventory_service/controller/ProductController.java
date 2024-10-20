package com.week_3_ecommerce.inventory_service.controller;

import com.week_3_ecommerce.inventory_service.clients.OrdersFeignClient;
import com.week_3_ecommerce.inventory_service.dto.OrderRequestDto;
import com.week_3_ecommerce.inventory_service.dto.ProductDto;
import com.week_3_ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	private final DiscoveryClient discoveryClient;
	private final RestClient restClient;

	private final OrdersFeignClient ordersFeignClient;

	@GetMapping("/fetchOrders")
	public String fetchFromOrdersService(){
		ServiceInstance orderService = discoveryClient.getInstances("order-service").get(0);
//		return restClient.get()
//				.uri(orderService.getUri()+"/orders/core/helloOrders")
//				.retrieve()
//				.body(String.class);
		return ordersFeignClient.helloOrders();
	}

	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllInventory(){
		List<ProductDto> inventories = productService.getAllInventory();
		return ResponseEntity.ok(inventories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id){
		ProductDto inventory = productService.getProductById(id);
		return ResponseEntity.ok(inventory);
	}

	@PutMapping("/reduce-stocks")
	public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestDto orderRequestDto){

		Double totalPrice = productService.reduceStocks(orderRequestDto);
		return ResponseEntity.ok(totalPrice);
	}

	@PutMapping("/restocks")
	public ResponseEntity<String> restocks(@RequestBody OrderRequestDto orderRequestDto){

		boolean success = productService.restocks(orderRequestDto);
		return success ? ResponseEntity.ok("Successfully discarded from Orders!") : ResponseEntity.ok("Not success");
	}
}
