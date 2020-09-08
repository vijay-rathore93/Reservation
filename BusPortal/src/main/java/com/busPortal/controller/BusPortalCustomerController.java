package com.busPortal.controller;

import java.util.List;

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

import com.busPortal.model.CustomerDTO;
import com.busPortal.model.ResponseDTO;
import com.busPortal.service.BusPortalCustomerService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
public class BusPortalCustomerController {
	
	
	
	private final BusPortalCustomerService busPortalCustomerService;

	@PostMapping("/customer")
	public ResponseEntity<ResponseDTO<String>> createCustomer(@RequestBody CustomerDTO customer, HttpServletRequest htsr) {

		return new ResponseEntity<ResponseDTO<String>>(
				new ResponseDTO<String>(busPortalCustomerService.customerCreation(customer, htsr)), HttpStatus.CREATED);

	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> getCustomers() {

		return new ResponseEntity<>(busPortalCustomerService.getAllCustomers(), HttpStatus.OK);

	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {

		return new ResponseEntity<>(busPortalCustomerService.getCustomer(id), HttpStatus.OK);

	}

	@GetMapping("/customer/{name}")
	public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String name) {

		return new ResponseEntity<>(busPortalCustomerService.getCustomerByName(name), HttpStatus.OK);

	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<ResponseDTO<String>> deleteCustomer(@PathVariable Long id) {

		return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>(busPortalCustomerService.delCustomer(id)),
				HttpStatus.OK);

	}

	@PatchMapping("/customer/{id}")
	public ResponseEntity<ResponseDTO<String>> postCustomer(@PathVariable Long id, @RequestBody CustomerDTO customer) {
		return new ResponseEntity<>(new ResponseDTO<>(busPortalCustomerService.updCustomer(id, customer)), HttpStatus.OK);

	}

	@GetMapping("/confirmCustomer")
	public ResponseEntity<ResponseDTO<String>> confirmCustomer(@RequestParam String token) {
		return new ResponseEntity<>(new ResponseDTO<>(busPortalCustomerService.tokenVerifier(token)), HttpStatus.OK);
	}

}
