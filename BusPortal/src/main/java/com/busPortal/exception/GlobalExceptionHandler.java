package com.busPortal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.busPortal.model.ResponseDTO;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ResponseDTO<String>> handlingCustomerException(CustomerException ex) {
		return new ResponseEntity<>(new ResponseDTO<>( ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ResponseDTO<String>> handlingUserException(UserException ex) {
		return new ResponseEntity<>(new ResponseDTO<>( ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	

}
