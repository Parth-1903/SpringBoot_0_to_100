package com.linkedin.connections_service.service;

import com.linkedin.connections_service.entity.Person;
import com.linkedin.connections_service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectionsService {

	private final PersonRepository personRepository;

	public List<Person> getFirstDegreeConnections(Long userId){
		log.info("Getting first degree connections for user with id: {}", userId);

		return personRepository.getFirstDegreeConnections(userId);
	}

}
