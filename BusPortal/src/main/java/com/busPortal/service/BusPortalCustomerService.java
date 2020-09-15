package com.busPortal.service;

import java.util.ArrayList;
import java.util.List;

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
import com.busPortal.model.CustomerDTO;
import com.busPortal.model.ResponseDTO;
import com.busPortal.utility.ApplicationMessages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusPortalCustomerService {

	@Value("${CUSTOMER_SERVICE}")
	private String customerURL;

	@Value("${CONFIRM_CUSTOMER_SERVICE}")
	private String confirmCustomerURL;

	@Value("${CUSTOMERS_SERVICE}")
	private String customersURL;

	@Value("${CUSTOMER_SERVICE_BY_ID}")
	private String customersByIdURL;

	@Value("${CUSTOMER_SERVICE_BY_NAME}")
	private String customersByNameURL;

	private final RestTemplate restTemplate;
	private final PasswordEncoder passwordEncoder;

	public String customerCreation(CustomerDTO customerDTO, HttpServletRequest htsr) {

		customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<CustomerDTO> httpEntity = new HttpEntity<CustomerDTO>(customerDTO, httpHeader);

		ResponseEntity<CustomerDTO> rec = restTemplate.postForEntity(customerURL, httpEntity, CustomerDTO.class);

		return ApplicationMessages.CREATE_MESSAGE;

	}

	public List<CustomerDTO> getAllCustomers() {

		ResponseEntity<ArrayList> rec = restTemplate.getForEntity(customersURL, ArrayList.class);

		return rec.getBody();

	}

	public CustomerDTO getCustomerByName(String name) {

		if (!SecurityContextHolder.getContext().getAuthentication().getName().equals(name)) {
			throw new CustomerException("Sorry! Privacy is a Thing, bro!");
		}

		ResponseEntity<CustomerDTO> rec = restTemplate.getForEntity(customersByNameURL + name, CustomerDTO.class);

		return rec.getBody();

	}

	public CustomerDTO getCustomer(Long id) {

		if (getCustomerByName(SecurityContextHolder.getContext().getAuthentication().getName()).getCustomerId() != id) {
			throw new CustomerException("Sorry! Privacy is a Thing, bro!");
		}

		ResponseEntity<CustomerDTO> rec = restTemplate.getForEntity(customersByIdURL + id, CustomerDTO.class);

		return rec.getBody();

	}

	public String delCustomer(Long id) {

		if (getCustomerByName(SecurityContextHolder.getContext().getAuthentication().getName()).getCustomerId() != id) {
			throw new CustomerException("Sorry! Can't delete Another Customer!");
		}

		restTemplate.delete(customerURL + id, Void.class);

		return ApplicationMessages.CUSTOMER_DELETED;

	}

	public String tokenVerifier(String name) {

		ResponseEntity<CustomerDTO> rec = restTemplate.getForEntity(confirmCustomerURL + name, CustomerDTO.class);

		return ApplicationMessages.TOKEN_VERIFY;

	}

	public String updCustomer(Long id, CustomerDTO customerDTO) {

		if (getCustomerByName(SecurityContextHolder.getContext().getAuthentication().getName()).getCustomerId() != id) {
			throw new CustomerException("Sorry! Can't delete Another Customer!");
		}

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(2000);
		requestFactory.setReadTimeout(2000);

		restTemplate.setRequestFactory(requestFactory);

		HttpEntity<CustomerDTO> httpEntity = new HttpEntity<>(customerDTO);

		ResponseEntity<ResponseDTO> response = restTemplate.exchange(customerURL + id, HttpMethod.PATCH, httpEntity,
				ResponseDTO.class);

		return response.getBody().getMessage().toString();

	}

}