package com.benkinmat.restful.webservices.udemyspringboot.basic.auth;

public class AuthenticationBean {
	
	private String message;
	
	public AuthenticationBean(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}