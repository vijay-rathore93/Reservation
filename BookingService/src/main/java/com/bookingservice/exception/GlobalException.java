package com.bookingservice.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalException {

	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<String> userNotFound(NoUserFoundException noUserFoundException) {
		return new ResponseEntity<String>("No user Found", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> dataIntegrityExceptionHandler(DataIntegrityViolationException noUserFoundException) {
		return new ResponseEntity<String>("data integrity violation", HttpStatus.BAD_REQUEST);
	}

}
