package com.week_3_ecommerce.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
	private Long id;
	private List<OrderRequestItemDto> items;
	private BigDecimal totalPrice;
}
