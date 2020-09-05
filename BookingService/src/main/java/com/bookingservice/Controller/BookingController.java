package com.bookingservice.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bookingservice.model.BookingDTO;
import com.bookingservice.model.ResponseDTO;
import com.bookingservice.service.BookingService;
import com.bookingservice.utility.ApplicationMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookingController {

	private final BookingService bookingService;

	@PostMapping("/booking")
	public ResponseEntity<ResponseDTO<String>> createCustomer(@RequestBody BookingDTO booking) {

		bookingService.bookingCreation(booking);

		return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>(ApplicationMessage.JOURNEY_SET),
				HttpStatus.CREATED);

	}

	@GetMapping("/booking/{name}")
	public ResponseEntity<BookingDTO> createCustomerName(@PathVariable String name) {

		return new ResponseEntity<>(bookingService.bookingGetter(name), HttpStatus.OK);

	}

	@GetMapping("/bookings")
	public ResponseEntity<List<BookingDTO>> getCustomers() {

		return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);

	}

	@GetMapping("/ticket")
	public ResponseEntity<BookingDTO> getTicket(@RequestHeader String bookingId) {

		return new ResponseEntity<BookingDTO>(bookingService.getTicket(bookingId), HttpStatus.OK);

	}

	@GetMapping("/cancelTicket")
	public ResponseEntity<ResponseDTO<String>> cancelTicket(@RequestHeader String bookingId) {

		return new ResponseEntity<>((new ResponseDTO<>(bookingService.cancelTicket(bookingId))), HttpStatus.OK);

	}

}