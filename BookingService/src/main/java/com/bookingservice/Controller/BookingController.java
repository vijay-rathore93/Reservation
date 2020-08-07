package com.bookingservice.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bookingservice.entity.Booking;
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
	public ResponseEntity<ResponseDTO<Integer, String>> createCustomer(@RequestBody Booking booking) {
		bookingService.bookingCreation(booking);
		//return new ResponseEntity<String>(ApplicationMessage.JOURNEY_SET, HttpStatus.CREATED);
		return new ResponseEntity<ResponseDTO<Integer,String>>(new ResponseDTO<Integer,String>(HttpStatus.CREATED.value(), ApplicationMessage.JOURNEY_SET), HttpStatus.CREATED);

	}
	
	
	
	@GetMapping("/bookings")
	public ResponseEntity<List<Booking>> getCustomers() {
		return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);

	}
	
	

	@GetMapping("/ticket")
	public ResponseEntity<ResponseDTO<Integer, BookingDTO>> createTicket(@RequestHeader String bookingId) {
		
		//return new ResponseEntity<String>(ApplicationMessage.JOURNEY_SET, HttpStatus.CREATED);
		return new ResponseEntity<ResponseDTO<Integer,BookingDTO>>(new ResponseDTO<Integer,BookingDTO>(HttpStatus.OK.value(), bookingService.getTicket(bookingId)), HttpStatus.OK);

	}
	
	

}