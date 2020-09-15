package org.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.userservice.entity.Customer;
import org.userservice.exception.NoCustomerFoundException;
import org.userservice.model.CustomerDTO;
import org.userservice.repo.CustomerRepo;
import org.userservice.utility.ApplicationMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepo customerRepo;
	private final EmailService ems;
	private final ModelMapper modelMapper;

	
	
	public List<CustomerDTO> getAllCustomers() {
		
		List<Customer> customerList=customerRepo.findAll();
		List<CustomerDTO> cdtoList=new ArrayList<CustomerDTO>();
		
		for(Customer c: customerList)
		{
			cdtoList.add(modelMapper.map(c,CustomerDTO.class));
		}
		
		return cdtoList;
	}

	public CustomerDTO getCustomer(Long id) {

		Customer cust = customerRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		return modelMapper.map(cust, CustomerDTO.class);
	}

	public String delCustomer(Long id) {

		Customer customer = customerRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		customerRepo.delete(customer);

		return ApplicationMessage.DELETE_MESSAGE;

	}

	public String updCustomer(Long id, CustomerDTO custmr) {
		Customer customer = customerRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		customer.setAadharNumber(custmr.getAadharNumber());
		customer.setContactNumber(custmr.getContactNumber());
		customer.setEmailId(custmr.getEmailId());
		customer.setCustName(custmr.getCustName());
		customerRepo.save(customer);
		return ApplicationMessage.UPDATE_MESSAGE;
	}

	public String customerCreation(CustomerDTO customer, HttpServletRequest htsr) {
		String token = UUID.randomUUID().toString();
		customer.setToken(token);
		customer.setIsActive(false);
		ems.sendMail(customer.getEmailId(), htsr, token);
		
		//customer.setPassword(passwordE);
		
		
		customerRepo.save(modelMapper.map(customer, Customer.class));
		return ApplicationMessage.CREATE_MESSAGE;
	}

	public String tokenVerifier(String token) {

		Customer customer = customerRepo.findByToken(token)
				.orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		customer.setIsActive(true);
		customer.setToken(null);
		customerRepo.save(customer);
		return ApplicationMessage.TOKEN_VERIFY;
	}

	public CustomerDTO getCustomerByName(String name) {

		
		
		
		Customer cust = customerRepo.findByUserName(name)
				.orElseThrow(() -> new NoCustomerFoundException("No Such Customer Found"));

		return modelMapper.map(cust, CustomerDTO.class);

	}

}
