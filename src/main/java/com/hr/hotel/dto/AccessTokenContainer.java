package com.hr.hotel.dto;

import java.io.Serializable;

public class AccessTokenContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String access_token;
	private String expires_in;

	public AccessTokenContainer() {

	}

	public AccessTokenContainer(String accessToken, String expireIn) {
		this.access_token = accessToken;
		this.expires_in = expireIn;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
}
