package com.week_3_ecommerce.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestItemDto {
	private Long id;
	private Long productId;
	private Integer quantity;
}
