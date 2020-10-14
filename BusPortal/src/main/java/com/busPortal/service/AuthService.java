package com.busPortal.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.exception.UserException;
import com.busPortal.model.ResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final RestTemplate restTemplate;
	
	public String confirm(String token) {
        String message="User has beeen Verified";
		ResponseEntity<ResponseDTO> rec = restTemplate.getForEntity("http://localhost:9001/confirm?token=" + token, ResponseDTO.class);
		
//		if(rec.getStatusCode().value()!=HttpStatus.OK.value())
//			throw new UserException("Verification Failed");
		return rec.getBody().getMessage().toString();
	}

}
