package com.busPortal.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.exception.CustomerException;
import com.busPortal.model.ResponseDTO;
import com.busPortal.model.TravellerDTO;
import com.busPortal.utility.ApplicationMessages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusPortalTravellerService {

	private final RestTemplate restTemplate;
	private final PasswordEncoder passwordEncoder;

	@Value("${TRAVELLER_SERVICE}")
	private String travellerURL;

	@Value("${CONFIRM_TRAVELLER_SERVICE}")
	private String confirmTravellerURL;
	
	
	@Value("${TRAVELLER_BY_NAME_SERVICE}")
	private String travellerByNameURL;
	
	@Value("${TRAVELLER_BY_ID_SERVICE}")
	private String travellerByIdURL;
	

	public String createTravels(TravellerDTO travellerDTO, HttpServletRequest htsr) {

		travellerDTO.setPassword(passwordEncoder.encode(travellerDTO.getPassword()));

		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<TravellerDTO> httpEntity = new HttpEntity<TravellerDTO>(travellerDTO, httpHeader);

		restTemplate.postForEntity(travellerURL, httpEntity, Void.class);

		return ApplicationMessages.TRAVEL_SAVED;
	}


	public String updateTravels(Long id, TravellerDTO travellerDTO) {

		

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(2000);
		requestFactory.setReadTimeout(2000);
		restTemplate.setRequestFactory(requestFactory);

	

		ResponseEntity<TravellerDTO> res = restTemplate.getForEntity(
				travellerByNameURL + "/" + SecurityContextHolder.getContext().getAuthentication().getName(), TravellerDTO.class);
		
		if(res.getBody().getTravelsId()!=id)
		{
			throw new CustomerException("Sorry! can't update other's Details!");
		}

		HttpEntity<TravellerDTO> httpEntity = new HttpEntity<>(travellerDTO);

		ResponseEntity<ResponseDTO> response = restTemplate.exchange(travellerURL + "/" + id, HttpMethod.PATCH,
				httpEntity, ResponseDTO.class);

		return response.getBody().getMessage().toString();
	}
	
	
	public String deleteTravels(Long id) {

		ResponseEntity<TravellerDTO> res = restTemplate.getForEntity(
				travellerByNameURL + "/" + SecurityContextHolder.getContext().getAuthentication().getName(), TravellerDTO.class);
		
		if(res.getBody().getTravelsId()!=id)
		{
			throw new CustomerException("Sorry! can't update other's Details!");
		}
		
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity httpEntity = new HttpEntity(httpHeader);
		ResponseEntity<String> response = restTemplate.exchange(travellerByIdURL + "/" + id, HttpMethod.DELETE,
				httpEntity, String.class);

		 
		return response.getBody();
	}
	

}
