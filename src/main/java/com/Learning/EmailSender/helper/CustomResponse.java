package com.Learning.EmailSender.helper;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomResponse {

	private String message;
	
	private HttpStatus httpStatus;
	
	private boolean success= false;
}
