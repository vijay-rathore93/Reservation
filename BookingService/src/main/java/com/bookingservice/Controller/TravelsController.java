package com.bookingservice.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookingservice.entity.Travels;
import com.bookingservice.model.ResponseDTO;
import com.bookingservice.model.TravelsDTO;
import com.bookingservice.service.TravelService;
import com.bookingservice.utility.ApplicationMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TravelsController {

	private final TravelService travelService;

	@PostMapping("/travels")
	public ResponseEntity<ResponseDTO<String>> createTravel(@RequestBody TravelsDTO travels) {

		travelService.travelCreator(travels);

		return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>(ApplicationMessage.TRAVEL_SAVED),
				HttpStatus.CREATED);

	}

	@GetMapping("/travel")
	public ResponseEntity<List<TravelsDTO>> getCustomers() {

		return new ResponseEntity<>(travelService.getAllTravels(), HttpStatus.OK);

	}

}
