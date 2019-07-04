package com.benkinmat.restful.webservices.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BasicAuthenticationdController {
	
	@GetMapping(path = "/basicauth")
	public AuthenticationBean helloWorldBean() {
//		throw new RuntimeException("Some error has happened! Contact Support");
		return new AuthenticationBean("You are authenticated");
	}	
}
