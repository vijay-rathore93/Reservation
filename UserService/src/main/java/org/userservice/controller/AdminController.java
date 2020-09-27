package org.userservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.userservice.model.AdminDTO;
import org.userservice.model.LoginAdminDTO;
import org.userservice.model.ResponseDTO;
import org.userservice.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

	@PostMapping("/admin")
	public ResponseEntity<Void> createAdmin(@RequestBody AdminDTO adminDTO, HttpServletRequest htsr) {

		adminService.createAdmin(adminDTO, htsr);

		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@GetMapping("/confirmAdmin")
	public ResponseEntity<ResponseDTO<String>> confirmAdmin(@RequestParam String token) {
		return new ResponseEntity<>(new ResponseDTO<>(adminService.confirmAdmin(token)), HttpStatus.OK);
	}
	
	
	@PatchMapping("/admin/{id}")
	public ResponseEntity<ResponseDTO<String>> updateAdmin(@PathVariable Long id,
			@RequestBody AdminDTO adminDTO) {
		return new ResponseEntity<>(new ResponseDTO<>(adminService.updateAdmin(id, adminDTO)), HttpStatus.OK);

	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<ResponseDTO<String>> deleteAdmin(@PathVariable Long id) {

		return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>(adminService.deleteAdmin(id)),
				HttpStatus.OK);

	}


}
