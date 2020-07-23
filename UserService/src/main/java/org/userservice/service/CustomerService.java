package org.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.userservice.entity.Customer;
import org.userservice.exception.NoCustomerFoundException;
import org.userservice.repo.CustomerRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepo customerRepo;

	public Customer customerCreation(Customer customer) {
		return customerRepo.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomer(Long id) {
		return customerRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
	}

}
