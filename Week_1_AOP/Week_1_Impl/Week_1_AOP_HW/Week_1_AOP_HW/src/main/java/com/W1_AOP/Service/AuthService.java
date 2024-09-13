package com.W1_AOP.Service;

import com.W1_AOP.Dto.LoginDto;
import com.W1_AOP.Entities.Employee;
import com.W1_AOP.Repository.EmployeeRepository;
import com.W1_AOP.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;

	public String login(LoginDto loginDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
		);
		Employee user = (Employee) authentication.getPrincipal();

		return jwtService.generateToken(user);
	}

	public Employee signUp(Employee employee){
		Optional<Employee> employee1 = employeeRepository.findByEmail(employee.getEmail());
		if(employee1.isPresent()){
			throw new BadCredentialsException("Employee with this email already exists "+employee.getEmail());
		}

		employee.setPassword(passwordEncoder.encode(employee.getPassword()));

		return employeeRepository.save(employee);
	}

}
