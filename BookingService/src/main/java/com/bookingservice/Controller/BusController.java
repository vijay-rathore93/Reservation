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
import com.bookingservice.service.BusService;
import com.bookingservice.utility.BusCategory;
import com.bookingservice.utility.BusStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BusController {

	private final BusService busService;

	@GetMapping("/buses")
	public ResponseEntity<List<Bus>> getBuses() {

		return new ResponseEntity<>(busService.displayBuses(), HttpStatus.OK);

	}

	@GetMapping("/busStatus/{busStatus}")
	public ResponseEntity<List<Bus>> getActiveSeats(@PathVariable BusStatus busStatus) {

		return new ResponseEntity<List<Bus>>(busService.activeBuses(busStatus), HttpStatus.OK);

	}

	@PatchMapping("/busStatus")
	public ResponseEntity<Void> updateBusStatus(@RequestHeader BusStatus busStatus, @RequestHeader String busNumber) {

		busService.updateStatus(busStatus, busNumber);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/busCategory/{busCategory}")
	public ResponseEntity<List<Bus>> getActiveSeats(@PathVariable BusCategory busCategory) {

		return new ResponseEntity<List<Bus>>(busService.busCategory(busCategory), HttpStatus.OK);

	}
}
