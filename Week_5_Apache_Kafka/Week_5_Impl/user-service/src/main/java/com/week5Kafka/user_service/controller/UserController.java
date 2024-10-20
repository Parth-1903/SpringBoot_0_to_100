package com.week5Kafka.user_service.controller;

import com.week5Kafka.user_service.dto.CreateUserRequestDto;
import com.week5Kafka.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final KafkaTemplate<String,String> kafkaTemplate;
	private final UserService userService;

	@Value("${kafka.topic.user-random-topic}")
	private String KAFKA_RANDOM_USER_TOPIC;

	@PostMapping
	public ResponseEntity<String> createdUser(@RequestBody CreateUserRequestDto createUserRequestDto){
		userService.createUser(createUserRequestDto);
		return ResponseEntity.ok("User is created");
	}

	@PostMapping("/{message}")
	public ResponseEntity<String> sendMessage(@PathVariable String message){

//		kafkaTemplate.send(KAFKA_RANDOM_USER_TOPIC, message);

		for(int i=0;i<1000;i++){
			kafkaTemplate.send(KAFKA_RANDOM_USER_TOPIC, ""+i%2 , message);
		}

		return ResponseEntity.ok("Message queued");
	}

}
