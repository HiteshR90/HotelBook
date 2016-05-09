package com.hr.hotel.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class UserLoginDto {

	@NotEmpty(message = "{NotEmpty.userMaster.userName}")
	private String userName;

	@NotEmpty(message = "{NotEmpty.userMaster.password}")
	// @Size(min = 8, max = 40)
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
