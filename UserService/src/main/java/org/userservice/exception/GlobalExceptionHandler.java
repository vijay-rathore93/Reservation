package org.userservice.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.userservice.model.ResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ResponseDTO<String>> handlingCustomerException(CustomerException ex) {
		return new ResponseEntity<>(new ResponseDTO<>(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoCustomerFoundException.class)
	public ResponseEntity<ResponseDTO<String>> handlingCustomerException(NoCustomerFoundException ex) {
		return new ResponseEntity<>(new ResponseDTO<>(ex.getMessage()), HttpStatus.OK);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseDTO<String>> constraintViolationHandler(
			ConstraintViolationException constraintViolationException) {

		return new ResponseEntity<>(
				new ResponseDTO<>(constraintViolationException.getSQLException().getLocalizedMessage()),
				HttpStatus.OK);
	}

}
