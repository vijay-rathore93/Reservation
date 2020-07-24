package org.userservice.service;

import java.util.List;
import java.util.Optional;

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

	public Customer delCustomer(Long id) {
		
		Optional<Customer> customer=customerRepo.findById(id);
		Customer cust=customer.get();
		
		
		if(customer.isPresent())
		{
			
			customerRepo.deleteById(id);
			return cust;
		}
		else
		{
			throw new NoCustomerFoundException("No Data Found");
		}
		
	}

	public Customer updCustomer(Long id, Customer custmr) {

		Optional<Customer> customer=customerRepo.findById(id);
		Customer cust=customer.get();
		

		if(customer.isPresent())
		{
			
			cust.setAadharNumber(custmr.getAadharNumber());
			cust.setContactNumber(custmr.getContactNumber());
			cust.setEmailId(custmr.getEmailId());
			cust.setName(custmr.getName());
			
			return cust;
		}
		else
		{
			throw new NoCustomerFoundException("No Data Found");
		}
		
	}

}
