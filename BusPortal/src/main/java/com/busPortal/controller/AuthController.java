package com.busPortal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busPortal.model.LoginDTO;
import com.busPortal.model.ResponseDTO;
import com.busPortal.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	
	private final AuthService authService;
	
	@GetMapping("/confirm")
	public ResponseEntity<ResponseDTO<String>> confirm(@RequestParam String token) {
		return new ResponseEntity<>(new ResponseDTO<>(authService.confirm(token)), HttpStatus.OK);
	}
	
	
}
