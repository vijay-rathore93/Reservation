package com.busPortal.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.exception.CustomerException;
import com.busPortal.model.CustomerDTO;
import com.busPortal.model.LoginDTO;
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

	@Value("${CUSTOMER_BY_ID_SERVICE}")
	private String customerByIdURL;

	@Value("${CUSTOMER_BY_NAME_SERVICE}")
	private String customerByNameURL;

	private final RestTemplate restTemplate;
	private final PasswordEncoder passwordEncoder;

	public String createCustomer(CustomerDTO customerDTO, HttpServletRequest htsr) {

		customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<CustomerDTO> httpEntity = new HttpEntity<CustomerDTO>(customerDTO, httpHeader);

		ResponseEntity<Void> rec = restTemplate.postForEntity(customerURL, httpEntity, Void.class);

		if (rec.getStatusCode().value() == HttpStatus.CREATED.value())

			return "Account Created Succesfully";

		throw new CustomerException("Problem Occured");

	}

	public List<CustomerDTO> getCustomers() {

		List<? extends GrantedAuthority> x = (List<? extends GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();

		GrantedAuthority y = x.get(0);

		String z = y.getAuthority();

		if (z.equalsIgnoreCase("ROLE_TRAVELS")) {

			// return null;

		}

		ResponseEntity<ArrayList> rec = restTemplate.getForEntity(customersURL, ArrayList.class);

		return rec.getBody();

	}

	public CustomerDTO getCustomerByName(String name) {

		List<? extends GrantedAuthority> x = (List<? extends GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();

		GrantedAuthority y = x.get(0);

		String z = y.getAuthority();

		if (!z.equalsIgnoreCase("ROLE_TRAVELS")) {

			if (!SecurityContextHolder.getContext().getAuthentication().getName().equals(name)) {
				throw new CustomerException("Sorry! can't check other Customer Details!");
			}

		}

		ResponseEntity<CustomerDTO> rec = restTemplate.getForEntity(customerByNameURL + name, CustomerDTO.class);
		
		
		if(rec.getBody().getUserName()==null)
		{
			throw new CustomerException("No Data Found");
		}

		return rec.getBody();

	}

//	public CustomerDTO getCustomerById(Long id) {
//
//		if (getCustomerByName(SecurityContextHolder.getContext().getAuthentication().getName()).getCustomerId() != id) {
//			throw new CustomerException("Sorry! can't check other Customer Details!");
//		}
//
//		ResponseEntity<CustomerDTO> rec = restTemplate.getForEntity(customerByIdURL + id, CustomerDTO.class);
//
//		return rec.getBody();
//
//	}
	
	public CustomerDTO getCustomerById(Long id) {

		List<? extends GrantedAuthority> x = (List<? extends GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();

		GrantedAuthority y = x.get(0);

		String z = y.getAuthority();
		
		if (!z.equalsIgnoreCase("ROLE_TRAVELS")) {

			if (getCustomerByName(SecurityContextHolder.getContext().getAuthentication().getName()).getCustomerId() != id) {
				throw new CustomerException("Sorry! can't check other Customer Details!");
			}

		}
		
		ResponseEntity<CustomerDTO> rec = restTemplate.getForEntity(customerByIdURL + id, CustomerDTO.class);

		if(rec.getBody().getUserName()==null)
		{
			throw new CustomerException("No Data Found");
		}
		
		
		return rec.getBody();

	}
	
	
	

	public String deleteCustomer(Long id) {
		
		List<? extends GrantedAuthority> x = (List<? extends GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();

		GrantedAuthority y = x.get(0);

		String z = y.getAuthority();
		
		if (!z.equalsIgnoreCase("ROLE_TRAVELS")) {

			if (getCustomerByName(SecurityContextHolder.getContext().getAuthentication().getName()).getCustomerId() != id) {
				throw new CustomerException("Sorry! can't delete other Customer Details!");
			}

		}
		
		

		restTemplate.delete(customerURL +"/"+ id, Void.class);

		return ApplicationMessages.CUSTOMER_DELETED;

	}

	public String confirmCustomer(String token) {

		ResponseEntity<CustomerDTO> rec = restTemplate.getForEntity(confirmCustomerURL + token, CustomerDTO.class);

		return ApplicationMessages.TOKEN_VERIFY;

	}

	public String updateCustomer(Long id, CustomerDTO customerDTO) {
		
		List<? extends GrantedAuthority> x = (List<? extends GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();

		GrantedAuthority y = x.get(0);

		String z = y.getAuthority();
		
		if (!z.equalsIgnoreCase("ROLE_TRAVELS")) {

			if (getCustomerByName(SecurityContextHolder.getContext().getAuthentication().getName()).getCustomerId() != id) {
				throw new CustomerException("Sorry! Can't update Another Customer!");
			}

		}
		


		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(2000);
		requestFactory.setReadTimeout(2000);

		restTemplate.setRequestFactory(requestFactory);

		HttpEntity<CustomerDTO> httpEntity = new HttpEntity<>(customerDTO);

		ResponseEntity<ResponseDTO> response = restTemplate.exchange(customerURL +"/"+ id, HttpMethod.PATCH, httpEntity,
				ResponseDTO.class);

		return response.getBody().getMessage().toString();

	}

}