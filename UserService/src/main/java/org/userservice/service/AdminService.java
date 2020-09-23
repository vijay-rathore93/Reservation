package org.userservice.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.userservice.entity.Admin;
import org.userservice.entity.Travels;
import org.userservice.exception.NoCustomerFoundException;
import org.userservice.model.AdminDTO;
import org.userservice.repo.AdminRepo;
import org.userservice.utility.ApplicationMessage;
import org.userservice.utility.ApplicationUserRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminRepo adminRepo;
	private final EmailServiceAdmin ems;
	private final ModelMapper modelMapper;

	public void createAdmin(AdminDTO adminDTO, HttpServletRequest htsr) {

		String token = UUID.randomUUID().toString();
		adminDTO.setRoleName(ApplicationUserRole.ADMIN.name());
		adminDTO.setIsActive(false);
		adminDTO.setToken(token);
		adminRepo.save(modelMapper.map(adminDTO, Admin.class));
		ems.sendMail(htsr, token);

	}

	public String confirmAdmin(String token) {

		Admin admin = adminRepo.findByToken(token).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		admin.setIsActive(true);
		admin.setToken(null);
		adminRepo.save(admin);
		return ApplicationMessage.TOKEN_VERIFY;
	}

	public String updateAdmin(Long id, AdminDTO adminDTO) {

		Admin admin = adminRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		if (admin.getIsActive() == false) {
			return ApplicationMessage.NOT_ACTIVATED;
		}

		admin.setAadharNumber(adminDTO.getAadharNumber());
		admin.setAdminName(adminDTO.getAdminName());
		admin.setContactNumber(adminDTO.getContactNumber());
		admin.setEmailId(adminDTO.getEmailId());
		
		adminRepo.save(admin);

		return ApplicationMessage.UPDATE_MESSAGE;
	}

	public String deleteAdmin(Long id) {

		Admin admin = adminRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));
		adminRepo.delete(admin);

		return ApplicationMessage.DELETE_MESSAGE;
	}

}
