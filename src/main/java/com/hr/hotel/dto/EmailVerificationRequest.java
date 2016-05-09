package com.hr.hotel.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

public class EmailVerificationRequest {

	@NotEmpty(message = "Please enter email address")
	@Email(message = "Not valid email id")
	private String email;

	@NotEmpty(message = "Please enter redirect url")
	@URL(message = "Not valid Url")
	private String redirectUrl;

	public EmailVerificationRequest() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}