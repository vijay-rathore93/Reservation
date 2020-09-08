package com.busPortal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.busPortal.model.ResponseDTO;
import com.busPortal.model.TravelsDTO;
import com.busPortal.service.BusPortalTravelsService;
import com.busPortal.utility.ApplicationMessages;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BusPortalTravelsController {
	
	private final BusPortalTravelsService busPortalTravelsService;
	
	
	@PostMapping("/travels")
	public ResponseEntity<ResponseDTO<String>> createTravel(@RequestBody TravelsDTO travels) {

		busPortalTravelsService.postTravel(travels);

		return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>(ApplicationMessages.TRAVEL_SAVED),
				HttpStatus.CREATED);

	}

	@GetMapping("/travel")
	public ResponseEntity<List<TravelsDTO>> getCustomers() {

		return new ResponseEntity<>(busPortalTravelsService.bookings(), HttpStatus.OK);

	}

	@GetMapping("/travel/{contactNumber}")
	public ResponseEntity<TravelsDTO> getCustomer(@PathVariable String contactNumber) {

		return new ResponseEntity<>(busPortalTravelsService.getTravelsByContactNumber(contactNumber), HttpStatus.OK);

	}
	
	
	

}
