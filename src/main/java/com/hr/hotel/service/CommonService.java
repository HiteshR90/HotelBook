package com.hr.hotel.service;


public interface CommonService {

	// public Map<String, String> decodeURL(String queryString);

	// public String encodeURL(User user, String operation, String url)
	// throws UnsupportedEncodingException;

	// public UserMaster getFacebookUserMaster(FacebookProfile facebookProfile);

	public boolean isValid(String target, String pattern);

	// public String encodeURL(String baseURL, Map<String, String> parameters)
	// throws UnsupportedEncodingException;

	public Boolean isValidDate(String date, String dateFormat);

	//public Date getDate(String date, String dateFormat);

	//public Date currentDateWithoutTime();

}
