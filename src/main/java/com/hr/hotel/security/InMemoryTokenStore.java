package com.hr.hotel.security;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.userdetails.UserDetails;

import com.hr.hotel.dto.AccessTokenContainer;
import com.hr.hotel.dto.AccessTokenModel;
import com.hr.hotel.exception.AuthenticationException;
import com.hr.hotel.util.DateUtil;
import com.hr.hotel.util.SecurityUtils;

/**
 * Implementation of token services that stores tokens in memory.
 * 
 * @author HR
 */
public class InMemoryTokenStore {
	private ConcurrentHashMap<String, AccessTokenModel> token = new ConcurrentHashMap<String, AccessTokenModel>();

	private Integer expTimeInMinute = new Integer(30);

	public InMemoryTokenStore() {

	}

	public InMemoryTokenStore(Integer expTimeInMinute) {
		this.expTimeInMinute = expTimeInMinute;
	}

	public void removeToken(String accessToken) {
		if (this.token.containsKey(accessToken)) {
			this.token.remove(accessToken);
		}
	}

	public UserDetails readAccessToken(String accessToken) {
		UserDetails user = null;
		Date currentDate = null;
		Date lastAccess = null;
		Long diff = null;
		Integer sessionTimeOut = expTimeInMinute * 60 * 1000;
		AccessTokenModel accessTokenModel = null;
		if (this.token.containsKey(accessToken)) {
			accessTokenModel = this.token.get(accessToken);
			lastAccess = accessTokenModel.getLastAccess();
			currentDate = new Date();
			diff = currentDate.getTime() - lastAccess.getTime();
			if (diff > sessionTimeOut) {
				this.token.remove(accessToken);
			} else {
				accessTokenModel.setLastAccess(currentDate);
				this.token.put(accessToken, accessTokenModel);
				user = accessTokenModel.getUserDetails();
			}
		}
		return user;
	}

	/*
	 * public AccessToken readAccessToken(String accessToken) {
	 * 
	 * try{
	 * 
	 * }finally{
	 * 
	 * } }
	 */

	public Integer getExpTimeInMinute() {
		return expTimeInMinute;
	}

	public void setExpTimeInMinute(Integer expTimeInMinute) {
		this.expTimeInMinute = expTimeInMinute;
	}

	public AccessTokenContainer generateAccessToken(UserDetails user) {
		String accessToken = null;
		String formatedDate = null;
		AccessTokenModel accessTokenModel = null;
		try {
			accessToken = SecurityUtils.getAuthenticationTokenOrSecret(user
					.getUsername());
			if (null != accessToken) {
				formatedDate = DateUtil.getDate(
						DateUtil.getAddedDate(0, expTimeInMinute, 0),
						"yyyy-MM-dd HH:mm:ss");
				accessTokenModel = new AccessTokenModel(
						DateUtil.getCurrentDate(), user);
				this.token.put(accessToken, accessTokenModel);

				return new AccessTokenContainer(accessToken, formatedDate);
			} else {
				throw new AuthenticationException(
						"Error in generation of access token",
						"Something went wrong when generating access token");
			}
		} finally {

		}
	}

}
