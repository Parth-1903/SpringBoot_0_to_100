package com.linkedin.user_service.service;

import com.linkedin.user_service.dto.LoginRequestDto;
import com.linkedin.user_service.dto.SignupRequestDto;
import com.linkedin.user_service.dto.UserDto;
import com.linkedin.user_service.entity.User;
import com.linkedin.user_service.exception.BadRequestException;
import com.linkedin.user_service.exception.ResourceNotFoundException;
import com.linkedin.user_service.repository.UserRepository;
import com.linkedin.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final JwtService jwtService;

	public UserDto signUp(SignupRequestDto signupRequestDto) {

		boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());

		if(exists){
			throw new BadRequestException("User already exists, cannot signup again.");
		}

		User user = modelMapper.map(signupRequestDto, User.class);
		user.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));

		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	public String login(LoginRequestDto loginRequestDto) {
		User user = userRepository.findByEmail(loginRequestDto.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with email: "+loginRequestDto.getEmail()));

		boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDto.getPassword(), user.getPassword());

		if(!isPasswordMatch){
			throw new BadRequestException("Incorrect password");
		}

		return jwtService.generateAccessToken(user);
	}
}
