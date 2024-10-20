package com.week5Kafka.user_service.service;

import com.week5Kafka.user_service.dto.CreateUserRequestDto;
import com.week5Kafka.user_service.entity.User;
import com.week5Kafka.user_service.event.UserCreatedEvent;
import com.week5Kafka.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;

	@Value("${kafka.topic.user-created-topic}")
	private String KAFKA_USER_CREATED_TOPIC;

	public void createUser(CreateUserRequestDto createUserRequestDto) {
		User user = modelMapper.map(createUserRequestDto, User.class);
		User savedUser = userRepository.save(user);
		UserCreatedEvent userCreatedEvent = modelMapper.map(savedUser,UserCreatedEvent.class);
		kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC, userCreatedEvent.getId(), userCreatedEvent);
	}
}
