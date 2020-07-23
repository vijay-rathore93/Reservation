package org.userservice.service;

import org.springframework.stereotype.Service;
import org.userservice.entity.Customer;
import org.userservice.repo.CustomerRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepo customerRepo;

	public Customer customerCreation(Customer customer) {
		return customerRepo.save(customer);
	}

}
