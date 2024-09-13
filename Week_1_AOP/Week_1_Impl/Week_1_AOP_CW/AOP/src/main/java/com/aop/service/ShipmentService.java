package com.aop.service;

public interface ShipmentService {
	String orderPackage(Long orderId);

	String trackPackage(Long orderId);
}
