package com.busPortal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.busPortal.model.SeatDTO;
import com.busPortal.service.BusPortalSeatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BusPortalSeatController {
	
	private final BusPortalSeatService busPortalSeatService;
	
	@PatchMapping("/seats")
	public ResponseEntity<Void> makeSeatAvailable(@RequestHeader String busNumber) {

		busPortalSeatService.updateSeats(busNumber);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/seats")
	public ResponseEntity<List<SeatDTO>> getSeats() {

		return new ResponseEntity<>(busPortalSeatService.displaySeats(), HttpStatus.OK);

	}

	@GetMapping("/seat/{seatNumber}")
	public ResponseEntity<SeatDTO> getSeats(@PathVariable String seatNumber) {

		return new ResponseEntity<>(busPortalSeatService.displaySeat(seatNumber), HttpStatus.OK);

	}

}
