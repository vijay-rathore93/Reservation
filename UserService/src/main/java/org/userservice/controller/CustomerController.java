package org.userservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.userservice.entity.Customer;
import org.userservice.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.customerCreation(customer), HttpStatus.CREATED);

	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers() {
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);

	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);

	}
	
}
