package com.week5Kafka.notification_service.event;

import lombok.Data;

@Data
public class UserCreatedEvent {

	private Long id;
	private String email;

}