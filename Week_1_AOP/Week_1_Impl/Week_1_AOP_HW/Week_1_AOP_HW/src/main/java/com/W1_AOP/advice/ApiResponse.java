package com.W1_AOP.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	private HttpStatus status;
	private String message;
	private T data;

	public ApiResponse(HttpStatus status,String message){
		this.status = status;
		this.message = message;
	}
	public ApiResponse(HttpStatus status, String message, T data){
		this.status = status;
		this.message = message;
		this.data = data;
	}

}
