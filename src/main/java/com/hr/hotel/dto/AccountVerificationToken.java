package com.hr.hotel.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class AccountVerificationToken {

	@NotEmpty(message = "Please Enter Verification Token")
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
