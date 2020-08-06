package com.bookingservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookingservice.entity.Booking;
import com.bookingservice.service.BookingService;
import com.bookingservice.utility.ApplicationMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookingController {

	private final BookingService bookingService;

	@PostMapping("/booking")
	public ResponseEntity<String> createCustomer(@RequestBody Booking booking) {
		bookingService.bookingCreation(booking);
		return new ResponseEntity<String>(ApplicationMessage.JOURNEY_SET, HttpStatus.CREATED);

	}

}