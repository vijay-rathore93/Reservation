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
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<String> userNotFound(DataNotFoundException dataNotFoundException) {
		return new ResponseEntity<String>("Return details Missing", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSeatFoundException.class)
	public ResponseEntity<String> noSeatFound(NoSeatFoundException noSeatFoundException) {
		return new ResponseEntity<String>(noSeatFoundException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookingException.class)
	public ResponseEntity<String> noBookingException(BookingException bookingException) {
		return new ResponseEntity<String>(bookingException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TravelException.class)
	public ResponseEntity<String> noTravelException(TravelException travelException) {
		return new ResponseEntity<String>(travelException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> noTravelException(Exception exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
