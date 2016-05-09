package com.hr.hotel.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class OAuth2Request {

	private String accessToken;

	@NotEmpty(message="Please Enter Access Token")
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
