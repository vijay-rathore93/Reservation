package org.userservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.userservice.model.LoginTravellerDTO;
import org.userservice.model.ResponseDTO;
import org.userservice.model.TravellerDTO;
import org.userservice.service.TravelsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TravelsController {

	private final TravelsService travelsService;

	@PostMapping("/travels")
	public ResponseEntity<Void> createTravels(@RequestBody LoginTravellerDTO loginTravellerDTO,
			HttpServletRequest htsr) {
		travelsService.createTravels(loginTravellerDTO, htsr);

		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@PatchMapping("/travels/{id}")
	public ResponseEntity<ResponseDTO<String>> updateTravels(@PathVariable Long id,
			@RequestBody TravellerDTO travellerDTO) {
		return new ResponseEntity<>(new ResponseDTO<>(travelsService.updateTravels(id, travellerDTO)), HttpStatus.OK);

	}
	
	@GetMapping("/travels/{id}")
	public ResponseEntity<TravellerDTO> getTravelsById(@PathVariable Long id,
			@RequestBody TravellerDTO travellerDTO) {
		return new ResponseEntity<>(travelsService.getTravelsById(id), HttpStatus.OK);
		

	}
	
	
	@GetMapping("/travelsByName/{name}")
	public ResponseEntity<TravellerDTO> getTravelsByName(@PathVariable String name) {

		return new ResponseEntity<>(travelsService.getTravelsByName(name), HttpStatus.OK);
	}


	@DeleteMapping("/travelsById/{id}")
	public ResponseEntity<ResponseDTO<String>> deleteTravels(@PathVariable Long id) {

		return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>(travelsService.deleteTravels(id)),
				HttpStatus.OK);

	}

}
