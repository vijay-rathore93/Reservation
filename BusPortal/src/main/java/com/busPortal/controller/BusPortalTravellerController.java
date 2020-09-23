package com.busPortal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busPortal.model.ResponseDTO;
import com.busPortal.model.TravellerDTO;
import com.busPortal.service.BusPortalTravellerService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class BusPortalTravellerController {
	
	private final BusPortalTravellerService busPortalTravellerService;

	@PostMapping("/traveller")
	public ResponseEntity<ResponseDTO<String>> createTravels(@RequestBody TravellerDTO travellerDTO,
			HttpServletRequest htsr) {

		return new ResponseEntity<ResponseDTO<String>>(
				new ResponseDTO<String>(busPortalTravellerService.createTravels(travellerDTO, htsr)), HttpStatus.CREATED);

	}
	
	
	@GetMapping("/confirmTraveller")
	public ResponseEntity<ResponseDTO<String>> confirmTravels(@RequestParam String token) {
		return new ResponseEntity<>(new ResponseDTO<>(busPortalTravellerService.confirmTravels(token)), HttpStatus.OK);
	}
	
	

}
