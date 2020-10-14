package org.userservice.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.userservice.entity.Admin;
import org.userservice.entity.LoginCredentials;
import org.userservice.exception.NoCustomerFoundException;
import org.userservice.model.AdminDTO;
import org.userservice.model.LoginAdminDTO;
import org.userservice.repo.AdminRepo;
import org.userservice.repo.LoginRepo;
import org.userservice.utility.ApplicationMessage;
import org.userservice.utility.ApplicationUserRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminRepo adminRepo;
	private final EmailService ems;
	private final ModelMapper modelMapper;
	private final LoginRepo loginRepo;

	@Value("${MAIL_SERVICE_ADMIN}")
	private String mailId;

	public void createAdmin(AdminDTO adminDTO, HttpServletRequest htsr) {

		String token = UUID.randomUUID().toString();

		LoginCredentials loginCredentials = new LoginCredentials();

		Admin admin = new Admin();
		loginCredentials.setPassword(adminDTO.getPassword());
		loginCredentials.setRoleName(ApplicationUserRole.ADMIN.name());
		loginCredentials.setToken(token);
		loginCredentials.setIsActive(false);
		loginCredentials.setUserName(adminDTO.getUserName());

		admin.setAadharNumber(adminDTO.getAadharNumber());
		admin.setName(adminDTO.getName());
		admin.setContactNumber(adminDTO.getContactNumber());
		admin.setEmailId(adminDTO.getEmailId());
		admin.setLoginCredentials(loginCredentials);

		adminRepo.save(admin);

		ems.sendMail(mailId, htsr, token);

	}

	public String confirmAdmin(String token) {

		LoginCredentials loginCredentials = loginRepo.findByToken(token)
				.orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		loginCredentials.setIsActive(true);
		loginCredentials.setToken(null);

		loginRepo.save(loginCredentials);
		return ApplicationMessage.TOKEN_VERIFY;
	}

	public String updateAdmin(Long id, AdminDTO adminDTO) {

		Admin admin = adminRepo.findById(id).orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		if (admin.getLoginCredentials().getIsActive() == false) {
			return ApplicationMessage.NOT_ACTIVATED;
		}

		admin.setAadharNumber(adminDTO.getAadharNumber());
		admin.setName(adminDTO.getName());
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
