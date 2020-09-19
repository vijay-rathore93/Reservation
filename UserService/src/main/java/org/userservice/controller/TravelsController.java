package org.userservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.userservice.service.TravelsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TravelsController {
	
	private final TravelsService travelsService;
	

}
