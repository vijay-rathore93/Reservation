package com.busPortal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busPortal.model.AdminDTO;
import com.busPortal.model.ResponseDTO;
import com.busPortal.service.BusPortalAdminService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class BusPortalAdminController {
	
	private final BusPortalAdminService busPortalAdminService;

	@PostMapping("/admin")
	public ResponseEntity<ResponseDTO<String>> createAdmin(@RequestBody AdminDTO adminDTO,
			HttpServletRequest htsr) {

		return new ResponseEntity<ResponseDTO<String>>(
				new ResponseDTO<String>(busPortalAdminService.createAdmin(adminDTO, htsr)), HttpStatus.CREATED);

	}
	
	
	@GetMapping("/confirmAdmin")
	public ResponseEntity<ResponseDTO<String>> confirmAdmin(@RequestParam String token) {
		return new ResponseEntity<>(new ResponseDTO<>(busPortalAdminService.confirmAdmin(token)), HttpStatus.OK);
	}
	
}