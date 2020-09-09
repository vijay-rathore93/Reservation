package org.userservice.controller;

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
import org.userservice.entity.Customer;
import org.userservice.model.CustomerDTO;
import org.userservice.model.ResponseDTO;
import org.userservice.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping("/customer")
	public ResponseEntity<ResponseDTO<String>> createCustomer(@RequestBody CustomerDTO customer, HttpServletRequest htsr) {

		return new ResponseEntity<ResponseDTO<String>>(
				new ResponseDTO<String>(customerService.customerCreation(customer, htsr)), HttpStatus.CREATED);

	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> getCustomers() {

		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);

	}

	@GetMapping("/customerById/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {

		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);

	}

	@GetMapping("/customerByName/{name}")
	public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String name) {

		return new ResponseEntity<>(customerService.getCustomerByName(name), HttpStatus.OK);

	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<ResponseDTO<String>> deleteCustomer(@PathVariable Long id) {

		return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>(customerService.delCustomer(id)),
				HttpStatus.OK);

	}

	@PatchMapping("/customer/{id}")
	public ResponseEntity<ResponseDTO<String>> postCustomer(@PathVariable Long id, @RequestBody CustomerDTO customer) {
		return new ResponseEntity<>(new ResponseDTO<>(customerService.updCustomer(id, customer)), HttpStatus.OK);

	}

	@GetMapping("/confirmCustomer")
	public ResponseEntity<ResponseDTO<String>> confirmCustomer(@RequestParam String token) {
		return new ResponseEntity<>(new ResponseDTO<>(customerService.tokenVerifier(token)), HttpStatus.OK);
	}

}
