package com.busPortal.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.model.AdminDTO;
import com.busPortal.model.TravellerDTO;
import com.busPortal.utility.ApplicationMessages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusPortalAdminService {

	private final RestTemplate restTemplate;
	private final PasswordEncoder passwordEncoder;

	@Value("${ADMIN_SERVICE}")
	private String adminURL;

	@Value("${CONFIRM_ADMIN_SERVICE}")
	private String confirmAdminURL;

	public String createAdmin(AdminDTO adminDTO, HttpServletRequest htsr) {

		
		adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<AdminDTO> httpEntity = new HttpEntity<AdminDTO>(adminDTO, httpHeader);

		restTemplate.postForEntity(adminURL, httpEntity, Void.class);

		return ApplicationMessages.TRAVEL_SAVED;
	}

	public String confirmAdmin(String token) {

		ResponseEntity<AdminDTO> rec = restTemplate.getForEntity(confirmAdminURL + token, AdminDTO.class);

		return ApplicationMessages.TOKEN_VERIFY;
	}

}
