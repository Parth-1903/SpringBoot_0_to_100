package com.W1_AOP.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

	private LocalDateTime localDateTime;
	private String error;
	private HttpStatus httpStatus ;

	public ApiError() {
		this.localDateTime = LocalDateTime.now();
	}

	public ApiError(String error, HttpStatus httpStatus) {
		this.localDateTime = LocalDateTime.now();
		this.error = error;
		this.httpStatus = httpStatus;
	}

}
