package com.busPortal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/")
    public String homePage() {
        return "login";
    }

}
