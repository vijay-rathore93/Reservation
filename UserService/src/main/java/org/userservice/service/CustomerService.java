package org.userservice.service;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.userservice.entity.Customer;
import org.userservice.exception.NoCustomerFoundException;
import org.userservice.repo.CustomerRepo;
import org.userservice.utility.ApplicationMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepo customerRepo;
	private final EmailService ems;

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomer(Long id) {
		return customerRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
	}

	public String delCustomer(Long id) {

		Customer customer = customerRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		customerRepo.delete(customer);

		return ApplicationMessage.DELETE_MESSAGE;

	}

	public String updCustomer(Long id, Customer custmr) {
		Customer customer = customerRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		customer.setAadharNumber(custmr.getAadharNumber());
		customer.setContactNumber(custmr.getContactNumber());
		customer.setEmailId(custmr.getEmailId());
		customer.setName(custmr.getName());
		customerRepo.save(customer);
		return ApplicationMessage.UPDATE_MESSAGE;
	}

	public String customerCreation(Customer customer, HttpServletRequest htsr) {
		String token = UUID.randomUUID().toString();
		customer.setToken(token);
		customer.setIsActive(false);
		ems.sendMail(customer.getEmailId(), htsr, token);
		customerRepo.save(customer);
		return ApplicationMessage.CREATE_MESSAGE;
	}

	public String tokenVerifier(String token) {


		Customer customer = customerRepo.findByToken(token).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		customer.setIsActive(true);
		customer.setToken(null);
		customerRepo.save(customer);
		return ApplicationMessage.TOKEN_VERIFY;
	}

}
