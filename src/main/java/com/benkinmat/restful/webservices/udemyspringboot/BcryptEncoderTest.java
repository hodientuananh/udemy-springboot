package com.benkinmat.restful.webservices.udemyspringboot;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		for (int i = 0; i <= 10; i++) {
			String encodedString = encoder.encode("password");
			System.out.println(encodedString);
		}
	}
	
}
