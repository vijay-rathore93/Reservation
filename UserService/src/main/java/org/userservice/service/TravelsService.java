package org.userservice.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.userservice.entity.LoginCredentials;
import org.userservice.entity.Travels;
import org.userservice.exception.NoCustomerFoundException;
import org.userservice.model.LoginTravellerDTO;
import org.userservice.model.TravellerDTO;
import org.userservice.repo.TravelsRepo;
import org.userservice.utility.ApplicationMessage;
import org.userservice.utility.ApplicationUserRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelsService {
	
	
	private final TravelsRepo travelsRepo;
	private final EmailService ems;
	
	
	
	@Value("${MAIL_SERVICE_TRAVELS}")
	private String mailId;

	public void createTravels(LoginTravellerDTO loginTravellerDTO, HttpServletRequest htsr) {
		
		
		
		String token = UUID.randomUUID().toString();

		Travels travels = new Travels();

		travels.setAadharNumber(loginTravellerDTO.getAadharNumber());
		travels.setContactNumber(loginTravellerDTO.getContactNumber());
		travels.setTravelsName(loginTravellerDTO.getTravelsName());
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
	
//	public String confirmTravels(String token) {
//
//		LoginCredentials login = loginRepo.findByToken(token)
//				.orElseThrow(() -> new NoCustomerFoundException("No Such Customer Found"));
//		
//		login.setIsActive(true);
//		login.setToken(null);
//
//		loginRepo.save(login);
//		return ApplicationMessage.TOKEN_VERIFY;
//	}

	public String updateTravels(Long id, TravellerDTO travellerDTO) {


		Travels travels=travelsRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		
		
		if(travels.getLoginCredentials().getIsActive()==false)
		{
			return ApplicationMessage.NOT_ACTIVATED;
		}
		
		travels.setAadharNumber(travellerDTO.getAadharNumber());
		travels.setContactNumber(travellerDTO.getContactNumber());
		travels.setEmailId(travellerDTO.getEmailId());
		travels.setTravelsName(travellerDTO.getTravelsName());
		travelsRepo.save(travels);
		
		return ApplicationMessage.UPDATE_MESSAGE;
	}

	public String deleteTravels(Long id) {

		Travels travels=travelsRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		
		travelsRepo.delete(travels);
		
		return ApplicationMessage.DELETE_MESSAGE;
	}

}