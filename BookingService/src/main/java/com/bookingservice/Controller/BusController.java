package com.bookingservice.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bookingservice.entity.Bus;
import com.bookingservice.model.BusDTO;
import com.bookingservice.service.BusService;
import com.bookingservice.utility.BusCategory;
import com.bookingservice.utility.BusStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BusController {

	private final BusService busService;

	@GetMapping("/buses")
	public ResponseEntity<List<BusDTO>> getBuses() {
		
		return new ResponseEntity<>(busService.displayBuses(), HttpStatus.OK);

	}
	
	@GetMapping("/bus/{busNumber}")
	public ResponseEntity<BusDTO> getBuses(@PathVariable String busNumber) {
		
		return new ResponseEntity<>(busService.displayBus(busNumber), HttpStatus.OK);

	}

	@GetMapping("/busStatus/{busStatus}")
	public ResponseEntity<List<BusDTO>> getActiveSeats(@PathVariable String busStatus) {
		
		return new ResponseEntity<>(busService.activeBuses(busStatus), HttpStatus.OK);

	}

	@PatchMapping("/busStatus")
	public ResponseEntity<Void> updateBusStatus(@RequestHeader BusStatus busStatus, @RequestHeader String busNumber) {

		busService.updateStatus(busStatus, busNumber);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/busCategory/{busCategory}")
	public ResponseEntity<List<BusDTO>> getActiveSeats(@PathVariable BusCategory busCategory) {
		
		return new ResponseEntity<>(busService.busCategory(busCategory), HttpStatus.OK);

	}
}
