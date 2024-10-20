package com.week5Kafka.notification_service.consumer;

import com.week5Kafka.notification_service.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserKafkaConsumer {

	@KafkaListener(topics = "user-created-topic")
	public void handleUserCreated(UserCreatedEvent userCreatedEvent){
		log.info("handleUserCreated: {}",userCreatedEvent);
	}

	@KafkaListener(topics = "user-random-topic")
	public void handleUserRandomTopic1(String message){
		log.info("message received: {}",message);
	}

	@KafkaListener(topics = "user-random-topic")
	public void handleUserRandomTopic2(String message){
		log.info("message received: {}",message);
	}

	@KafkaListener(topics = "user-random-topic")
	public void handleUserRandomTopic3(String message){
		log.info("message received: {}",message);
	}

}
