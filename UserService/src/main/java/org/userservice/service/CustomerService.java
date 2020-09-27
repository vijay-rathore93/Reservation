package org.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.userservice.entity.Customer;
import org.userservice.entity.LoginCredentials;
import org.userservice.exception.NoCustomerFoundException;
import org.userservice.model.CustomerDTO;
import org.userservice.model.LoginCustomerDTO;
import org.userservice.repo.CustomerRepo;
import org.userservice.repo.LoginRepo;
import org.userservice.utility.ApplicationMessage;
import org.userservice.utility.ApplicationUserRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepo customerRepo;
	private final EmailService ems;
	private final ModelMapper modelMapper;
	private final LoginRepo loginRepo;

	public List<CustomerDTO> getCustomers() {

		List<Customer> customerList = customerRepo.findAll();
		List<CustomerDTO> cdtoList = new ArrayList<CustomerDTO>();

		for (Customer c : customerList) {
			cdtoList.add(modelMapper.map(c, CustomerDTO.class));
		}

		return cdtoList;
	}

	public CustomerDTO getCustomerById(Long id) {

		Customer cust = customerRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		return modelMapper.map(cust, CustomerDTO.class);
	}

	public String deleteCustomer(Long id) {

		Customer customer = customerRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		customerRepo.delete(customer);

		return ApplicationMessage.DELETE_MESSAGE;

	}

	public String updateCustomer(Long id, CustomerDTO customerDTO) {
		Customer customer = customerRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		if (customer.getLoginCredentials().getIsActive() == false) {
			return ApplicationMessage.NOT_ACTIVATED;
		}

		customer.setAadharNumber(customerDTO.getAadharNumber());
		customer.setContactNumber(customerDTO.getContactNumber());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setCustName(customerDTO.getCustName());
		customerRepo.save(customer);
		return ApplicationMessage.UPDATE_MESSAGE;
	}

	public void createCustomer(CustomerDTO customerDTO, HttpServletRequest htsr) {
		String token = UUID.randomUUID().toString();

		LoginCredentials loginCredentials = LoginCredentials.builder().userName(customerDTO.getUserName())
				.isActive(false).password(customerDTO.getPassword()).roleName(ApplicationUserRole.PASSENGER.name())
				.token(token).build();
		
		Customer customer=modelMapper.map(customerDTO, Customer.class);
		
		customer.setLoginCredentials(loginCredentials);

		customerRepo.save(customer);
		ems.sendMail(customerDTO.getEmailId(), htsr, token);

	}



	public CustomerDTO getCustomerByName(String name) {

		LoginCredentials login = loginRepo.findByUserName(name)
				.orElseThrow(() -> new NoCustomerFoundException("No Such Customer Found"));

		Customer cust = customerRepo.findByLoginCredentials(login).get();

		CustomerDTO customerDTO = modelMapper.map(cust, CustomerDTO.class);

		customerDTO.setRoleName(cust.getLoginCredentials().getRoleName());
		customerDTO.setUserName(cust.getLoginCredentials().getUserName());

		return customerDTO;

	}

}
