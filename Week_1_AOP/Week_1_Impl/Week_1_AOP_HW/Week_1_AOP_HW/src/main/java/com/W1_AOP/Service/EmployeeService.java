package com.W1_AOP.Service;

import com.W1_AOP.Entities.Employee;
import com.W1_AOP.Exception.ResourceNotFoundException;
import com.W1_AOP.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return employeeRepository.findByEmail(username)
				.orElseThrow(() -> new BadCredentialsException("User with email "+username + " not found!"));
	}

	public Employee getUserById(Long userId) {
		return employeeRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with this id: "+userId));
	}
}
