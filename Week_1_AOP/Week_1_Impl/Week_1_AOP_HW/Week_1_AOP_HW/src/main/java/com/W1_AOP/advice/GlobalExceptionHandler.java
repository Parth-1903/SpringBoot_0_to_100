package com.W1_AOP.advice;

import com.W1_AOP.Exception.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handlerResourceNotFoundException(ResourceNotFoundException exception){
		ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiError> handlerAuthenticationException(AuthenticationException exception){
		ApiError apiError = new ApiError(exception.getLocalizedMessage(),HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ApiError> handlerJwtException(JwtException exception){
		ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handlerResourceNotFoundException(Exception exception){
		ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
	}

}
