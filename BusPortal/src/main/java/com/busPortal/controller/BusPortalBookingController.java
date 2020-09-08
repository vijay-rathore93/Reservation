package com.busPortal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.busPortal.model.BookingDTO;
import com.busPortal.model.ResponseDTO;
import com.busPortal.service.BusPortalBookingService;
import com.busPortal.utility.ApplicationMessages;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class BusPortalBookingController {
	
	
	
	private final BusPortalBookingService busPortalBookingService;
	
	@PostMapping("/booking")
	public ResponseEntity<ResponseDTO<String>> createCustomer(@RequestBody BookingDTO booking) {

		busPortalBookingService.bookingCreation(booking);

		return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>(ApplicationMessages.JOURNEY_SET),
				HttpStatus.CREATED);

	}

	@GetMapping("/booking/{name}")
	public ResponseEntity<BookingDTO> createCustomerName(@PathVariable String name) {

		return new ResponseEntity<>(busPortalBookingService.bookingGetter(name), HttpStatus.OK);

	}

	@GetMapping("/bookings")
	public ResponseEntity<List<BookingDTO>> getCustomers() {

		return new ResponseEntity<>(busPortalBookingService.getAllBookings(), HttpStatus.OK);

	}

	@GetMapping("/ticket")
	public ResponseEntity<BookingDTO> getTicket(@RequestHeader String bookingId) {

		return new ResponseEntity<BookingDTO>(busPortalBookingService.getTicket(bookingId), HttpStatus.OK);

	}

	@GetMapping("/cancelTicket")
	public ResponseEntity<ResponseDTO<String>> cancelTicket(@RequestHeader String bookingId) {

		return new ResponseEntity<>((new ResponseDTO<>(busPortalBookingService.cancelTicket(bookingId))), HttpStatus.OK);

	}
	

}
