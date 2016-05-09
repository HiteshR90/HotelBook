package com.hr.hotel.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class DeleagetDto {

	@NotEmpty(message = "Please Enter UserName or Email address")
	private String userNameOrEmail;

	// @NotEmpty(message = "Please Enter Url For Redirection")
	// @URL(message = "Not Valid URL")
	// private String redirectUrl;

	public String getUserNameOrEmail() {
		return userNameOrEmail;
	}

	public void setUserNameOrEmail(String userNameOrEmail) {
		this.userNameOrEmail = userNameOrEmail;
	}

}
