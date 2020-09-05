package com.busPortal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.busPortal.model.BusDTO;
import com.busPortal.service.BusPortalBusService;
import com.busPortal.utility.BusCategory;
import com.busPortal.utility.BusStatus;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BusPortalBusController {
	
	
	private final BusPortalBusService busPortalBusService;
	
	
	@GetMapping("/buses")
	public ResponseEntity<List<BusDTO>> getBuses() {
		
		return new ResponseEntity<>(busPortalBusService.displayBuses(), HttpStatus.OK);

	}
	
	@GetMapping("/bus/{busNumber}")
	public ResponseEntity<BusDTO> getBuses(@PathVariable String busNumber) {
		
		return new ResponseEntity<>(busPortalBusService.displayBus(busNumber), HttpStatus.OK);

	}

	@GetMapping("/busStatus/{busStatus}")
	public ResponseEntity<List<BusDTO>> getActiveSeats(@PathVariable BusStatus busStatus) {
		
		return new ResponseEntity<>(busPortalBusService.activeBuses(busStatus), HttpStatus.OK);

	}

	@PatchMapping("/busStatus")
	public ResponseEntity<Void> updateBusStatus(@RequestHeader BusStatus busStatus, @RequestHeader String busNumber) {

		busPortalBusService.updateStatus(busStatus, busNumber);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/busCategory/{busCategory}")
	public ResponseEntity<List<BusDTO>> getActiveSeats(@PathVariable BusCategory busCategory) {
		
		return new ResponseEntity<>(busPortalBusService.busCategory(busCategory), HttpStatus.OK);

	}

}
