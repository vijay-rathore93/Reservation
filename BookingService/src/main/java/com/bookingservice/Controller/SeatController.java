package com.bookingservice.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bookingservice.entity.Seat;
import com.bookingservice.service.SeatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SeatController {

	private final SeatService seatService;

	@PatchMapping("/seats")
	public ResponseEntity<Void> makeSeatAvailable(@RequestHeader String busNumber) {

		seatService.updateSeats(busNumber);
		return new ResponseEntity<>(HttpStatus.OK);

	}
 

	@GetMapping("/seats")
	public ResponseEntity<List<Seat>> getSeats() {

		
		return new ResponseEntity<>(seatService.displaySeats(),HttpStatus.OK);

	}

	
	
	
}
