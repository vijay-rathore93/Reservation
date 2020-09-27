package org.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.userservice.model.LoginDTO;
import org.userservice.model.ResponseDTO;
import org.userservice.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	
	private final AuthService authService;
	
//	@GetMapping("/login")
//	public ResponseEntity<LoginDTO> login(@RequestParam String userName) {
//		return new ResponseEntity<>(authService.login(userName), HttpStatus.OK);
//	}
	
	@GetMapping("/login/{userName}")
	public ResponseEntity<LoginDTO> login(@PathVariable String userName) {
		return new ResponseEntity<>(authService.login(userName), HttpStatus.OK);
	}
	
	

	
	@GetMapping("/confirm")
	public ResponseEntity<ResponseDTO<String>> confirm(@RequestParam String token) {
		return new ResponseEntity<>(new ResponseDTO<>(authService.confirm(token)), HttpStatus.OK);
	}
	
	
}
