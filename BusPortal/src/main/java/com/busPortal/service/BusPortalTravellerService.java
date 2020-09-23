package com.busPortal.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.model.CustomerDTO;
import com.busPortal.model.TravellerDTO;
import com.busPortal.model.TravelsDTO;
import com.busPortal.utility.ApplicationMessages;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class BusPortalTravellerService {
	
	
	private final RestTemplate restTemplate;
	
	@Value("${TRAVELLER_SERVICE}")
	private String travellerURL;

	@Value("${CONFIRM_TRAVELLER_SERVICE}")
	private String confirmTravellerURL;
	

	public String createTravels(TravellerDTO travellerDTO, HttpServletRequest htsr) {
		
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<TravellerDTO> httpEntity = new HttpEntity<TravellerDTO>(travellerDTO, httpHeader);

		restTemplate.postForEntity(travellerURL, httpEntity, Void.class);
		
		return ApplicationMessages.TRAVEL_SAVED;
	}

	public String confirmTravels(String token) {

		ResponseEntity<TravellerDTO> rec = restTemplate.getForEntity(confirmTravellerURL + token, TravellerDTO.class);
		
		return ApplicationMessages.TOKEN_VERIFY;
	}
	
	
	
	

}
