package org.userservice.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.userservice.entity.Travels;
import org.userservice.exception.NoCustomerFoundException;
import org.userservice.model.TravellerDTO;
import org.userservice.repo.TravelsRepo;
import org.userservice.utility.ApplicationMessage;
import org.userservice.utility.ApplicationUserRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelsService {
	
	
	private final TravelsRepo travelsRepo;
	private final EmailServiceTravels ems;
	private final ModelMapper modelMapper;

	public void createTravels(TravellerDTO travelsDTO, HttpServletRequest htsr) {
		
		String token = UUID.randomUUID().toString();
		travelsDTO.setIsActive(false);
		travelsDTO.setRoleName(ApplicationUserRole.TRAVELS.name());
		travelsDTO.setToken(token);
		
		travelsRepo.save(modelMapper.map(travelsDTO, Travels.class));
		ems.sendMail(htsr, token);

	}
	
	public String confirmTravels(String token) {

		Travels travels = travelsRepo.findByToken(token)
				.orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
	
		travels.setIsActive(true);
		travels.setToken(null);
		travelsRepo.save(travels);
		return ApplicationMessage.TOKEN_VERIFY;
	}

	public String updateTravels(Long id, TravellerDTO travellerDTO) {


		Travels travels=travelsRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		
		
		if(travels.getIsActive()==false)
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