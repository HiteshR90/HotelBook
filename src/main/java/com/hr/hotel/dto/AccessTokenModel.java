package com.hr.hotel.dto;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

public class AccessTokenModel {

	private Date lastAccess;
	private UserDetails userDetails;

	public AccessTokenModel() {

	}

	public AccessTokenModel(Date lastAccess, UserDetails userDetails) {
		this.lastAccess = lastAccess;
		this.userDetails = userDetails;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
