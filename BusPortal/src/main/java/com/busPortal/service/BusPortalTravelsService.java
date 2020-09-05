package com.busPortal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.model.TravelsDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusPortalTravelsService {

	private final RestTemplate restTemplate;

	//TRAVELS_PLURAL_SERVICE
	@Value("${TRAVELS_SERVICE}")
	private String travelURL;
	
	@Value("${TRAVELS_PLURAL_SERVICE}")
	private String travelsURL;
	
	
	public TravelsDTO getTravel(String name) {

		ResponseEntity<TravelsDTO> rec = restTemplate.getForEntity(travelURL + name,
				TravelsDTO.class);

		return rec.getBody();

	}
	
	public TravelsDTO postTravel(TravelsDTO travelsDTO) {
		
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<TravelsDTO> httpEntity = new HttpEntity<TravelsDTO>(travelsDTO, httpHeader);

		ResponseEntity<TravelsDTO> rec = restTemplate.postForEntity(travelURL,httpEntity,
				TravelsDTO.class);

		return rec.getBody();

	}
	
	
	
	public List<TravelsDTO> bookings() {

		ResponseEntity<ArrayList> rec = restTemplate.getForEntity(travelsURL,
				ArrayList.class);

		return rec.getBody();

	}
	
	
	public TravelsDTO getTravelsByContactNumber(String contactNumber) {

		

		ResponseEntity<TravelsDTO> rec = restTemplate.getForEntity(travelURL + contactNumber,
				TravelsDTO.class);
		
		return rec.getBody();

	}
	
	
	

}
