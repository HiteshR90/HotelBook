package com.hr.hotel.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

public class ForgotPasswordDto {

	public interface RestForgotPassword {

	}

	public interface ForgotPassword {

	}

	@NotNull(message = "Please enter username or email address", groups = {
			RestForgotPassword.class, ForgotPassword.class })
	private String userNameOrEmail;

	@URL(message = "Please Enter Valid URL", groups = { RestForgotPassword.class })
	@NotEmpty(message = "call back url must not empty", groups = { RestForgotPassword.class })
	private String redirectUrl;

	public String getUserNameOrEmail() {
		return userNameOrEmail;
	}

	public void setUserNameOrEmail(String userNameOrEmail) {
		this.userNameOrEmail = userNameOrEmail;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
