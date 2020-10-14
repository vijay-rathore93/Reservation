package org.userservice.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.userservice.entity.Customer;
import org.userservice.entity.LoginCredentials;
import org.userservice.entity.Travels;
import org.userservice.exception.NoCustomerFoundException;
import org.userservice.model.CustomerDTO;
import org.userservice.model.LoginTravellerDTO;
import org.userservice.model.TravellerDTO;
import org.userservice.repo.LoginRepo;
import org.userservice.repo.TravelsRepo;
import org.userservice.utility.ApplicationMessage;
import org.userservice.utility.ApplicationUserRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelsService {
	
	
	private final TravelsRepo travelsRepo;
	private final EmailService ems;
	private final ModelMapper modelMapper;
	private final LoginRepo loginRepo;
	
	
	@Value("${MAIL_SERVICE_TRAVELS}")
	private String mailId;

	public void createTravels(LoginTravellerDTO loginTravellerDTO, HttpServletRequest htsr) {
		
		
		
		String token = UUID.randomUUID().toString();

		Travels travels = new Travels();

		travels.setAadharNumber(loginTravellerDTO.getAadharNumber());
		travels.setContactNumber(loginTravellerDTO.getContactNumber());
		travels.setName(loginTravellerDTO.getName());
		travels.setEmailId(loginTravellerDTO.getEmailId());

		

		LoginCredentials loginCredentials = new LoginCredentials();
		
		loginCredentials.setUserName(loginTravellerDTO.getUserName());
		loginCredentials.setPassword(loginTravellerDTO.getPassword());
		loginCredentials.setRoleName(ApplicationUserRole.TRAVELS.name());
		loginCredentials.setToken(token);
		loginCredentials.setIsActive(false);

		travels.setLoginCredentials(loginCredentials);
		
		travelsRepo.save(travels);
		ems.sendMail(mailId,htsr, token);

	}


	public String updateTravels(Long id, TravellerDTO travellerDTO) {


		Travels travels=travelsRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		
		
		if(travels.getLoginCredentials().getIsActive()==false)
		{
			return ApplicationMessage.NOT_ACTIVATED;
		}
		
		travels.setAadharNumber(travellerDTO.getAadharNumber());
		travels.setContactNumber(travellerDTO.getContactNumber());
		travels.setEmailId(travellerDTO.getEmailId());
		travels.setName(travellerDTO.getName());
		travelsRepo.save(travels);
		
		return ApplicationMessage.UPDATE_MESSAGE;
	}

	public String deleteTravels(Long id) {

		Travels travels=travelsRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		
		travelsRepo.delete(travels);
		
		return ApplicationMessage.DELETE_MESSAGE;
	}

	public TravellerDTO getTravelsById(Long id) {


		Travels travels=travelsRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		
		return modelMapper.map(travels, TravellerDTO.class);
		
		
	}


	public TravellerDTO getTravelsByName(String name) {
		

		LoginCredentials login = loginRepo.findByUserName(name)
				.orElseThrow(() -> new NoCustomerFoundException("No Such Customer Found"));
		
		Travels travels = travelsRepo.findByLoginCredentials(login).get();

		TravellerDTO travellerDTO = modelMapper.map(travels, TravellerDTO.class);

		travellerDTO.setRoleName(travels.getLoginCredentials().getRoleName());
		travellerDTO.setUserName(travels.getLoginCredentials().getUserName());
		
		
		return travellerDTO;
	}

}