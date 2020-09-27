package org.userservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.userservice.entity.LoginCredentials;
import org.userservice.exception.NoCustomerFoundException;
import org.userservice.model.LoginDTO;
import org.userservice.repo.LoginRepo;
import org.userservice.utility.ApplicationMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final LoginRepo loginRepo;
	private final ModelMapper modelMapper;

	public LoginDTO login(String userName) {

		LoginCredentials login = loginRepo.findByUserName(userName)
				.orElseThrow(() -> new NoCustomerFoundException("No Data Found"));

		return modelMapper.map(login, LoginDTO.class);

	}

	public String confirm(String token) {

		LoginCredentials login = loginRepo.findByToken(token)
				.orElseThrow(() -> new NoCustomerFoundException("No Such Customer Found"));

		login.setIsActive(true);
		login.setToken(null);

		loginRepo.save(login);
		return ApplicationMessage.TOKEN_VERIFY;
	}

}
