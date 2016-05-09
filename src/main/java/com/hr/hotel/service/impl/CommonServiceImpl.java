package com.hr.hotel.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import com.hr.hotel.common.Constant;
import com.hr.hotel.service.CommonService;

@Service(Constant.SERVICE_COMMON)
public class CommonServiceImpl implements CommonService {

	/**
	 * decodeURL get query string as input and store it in map
	 * 
	 * @param queryString
	 *            queryString of URL
	 * @return map contains key and value pair from queryString
	 */
	// @Override
	// public Map<String, String> decodeURL(String queryString) {
	// // store decoded URL
	// byte[] decode = null;
	// // store decoded URL data as array
	// String[] params = null;
	// // object of Map
	// Map<String, String> map = null;
	// // store decode url
	// String decodeURL = null;
	// try {
	// // store decoded URL in byte array
	// decode = Base64.decodeBase64(queryString);
	// // create object of String
	// decodeURL = new String(decode);
	// // break URL data after & and store it in string array
	// params = decodeURL.split(Constant.STRING_AND);
	// // crate object of HashMap
	// map = new HashMap<String, String>();
	// // store URL data in map
	// for (String param : params) {
	// // split date after =
	// map.put(param.split(Constant.STRING_EQUAL)[0],
	// param.split(Constant.STRING_EQUAL)[1]);
	// }
	// // free system resource
	// } finally {
	// decode = null;
	// params = null;
	// decodeURL = null;
	// }
	// return map;
	// }

	/**
	 * encodeURL encode URL using Base64 algorithm
	 * 
	 * @param user
	 *            object of User
	 * @param operation
	 *            object of String
	 * @return encoded URL
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	// @Override
	// public String encodeURL(User user, String operation, String url)
	// throws UnsupportedEncodingException {
	// // Create object of StringBuilder
	// StringBuilder objSBUrl = null;
	// StringBuilder objSBEncode = null;
	// // create byte array
	// byte[] encodeByte = null;
	// Calendar objCalendar = null;
	// Long expTime = null;
	// try {
	// objCalendar = Calendar.getInstance();
	// // add 24 hours
	// expTime = objCalendar.getTimeInMillis() + (24 * 3600000);
	//
	// objSBUrl = new StringBuilder();
	// objSBEncode = new StringBuilder();
	// // assign redirection URL
	// objSBUrl.append(url).append(Constant.STRING_QUESTION)
	// .append(Constant.URL_KEY).append(Constant.STRING_EQUAL);
	//
	// // generate URL from user
	// objSBEncode
	// .append(Constant.QUERY_STRING_OPERATION)
	// .append(Constant.STRING_EQUAL)
	// .append(operation)
	// .append(Constant.STRING_AND)
	// .append("userId")
	// .append(Constant.STRING_EQUAL)
	//
	// // add userId in URL
	// .append(URLEncoder.encode(user.getId().toString(),
	// Constant.UTF8))
	// .append(Constant.STRING_AND)
	// // add email in URL
	// .append("email")
	// .append(Constant.STRING_EQUAL)
	// .append(URLEncoder.encode(user.getEmail(), Constant.UTF8))
	// .append(Constant.STRING_AND)
	// // add date in URL
	// .append("expTime")
	// .append(Constant.STRING_EQUAL)
	// .append(URLEncoder.encode(String.valueOf(expTime),
	// Constant.UTF8));
	//
	// // convert String to byte array
	// encodeByte = objSBEncode.toString().getBytes(Constant.UTF8);
	// // add encoded url to main URL
	// objSBUrl.append(Base64.encodeBase64URLSafeString(encodeByte));
	// } finally {
	// encodeByte = null;
	// if (objSBEncode != null) {
	// objSBEncode.delete(0, objSBEncode.length());
	// objSBEncode = null;
	// }
	// }
	// return objSBUrl.toString();
	// }

	// @Override
	// public String encodeURL(String baseURL, Map<String, String> parameters)
	// throws UnsupportedEncodingException {
	// StringBuilder objSBUrl = null;
	// StringBuilder objSBEncode = null;
	// // create byte array
	// byte[] encodeByte = null;
	// try {
	// objSBUrl = new StringBuilder();
	// objSBEncode = new StringBuilder();
	// objSBUrl = new StringBuilder();
	// objSBEncode = new StringBuilder();
	// // assign redirection URL
	// objSBUrl.delete(0, objSBUrl.length()).append(baseURL)
	// .append(Constant.STRING_QUESTION).append(Constant.URL_KEY)
	// .append(Constant.STRING_EQUAL);
	//
	// Set<String> keys = parameters.keySet();
	//
	// for (String key : keys) {
	// objSBEncode.append(Constant.STRING_AND).append(key)
	// .append(Constant.STRING_EQUAL)
	// .append(parameters.get(key));
	// }
	// if (objSBEncode.toString().startsWith(Constant.STRING_AND))
	// objSBEncode.delete(0, 1);
	// // convert String to byte array
	// encodeByte = objSBEncode.toString().getBytes(Constant.UTF8);
	// // add encoded url to main URL
	// objSBUrl.append(Base64.encodeBase64URLSafeString(encodeByte));
	//
	// } finally {
	//
	// }
	// return objSBUrl.toString();
	// }

	/**
	 * isValid check is valid with pattern or not
	 * 
	 * @param target
	 *            object of String
	 * @param pattern
	 *            object of String
	 * @return true if pattern match else return false
	 */
	public boolean isValid(String target, String pattern) {
		// object of Pattern
		Pattern objPattern = null;
		// object of Matcher
		Matcher objMatcher = null;
		boolean isValid = false;
		try {
			// apply pattern to given string
			objPattern = Pattern.compile(pattern);
			objMatcher = objPattern.matcher(target);
			isValid = objMatcher.matches();
		} finally {
			objPattern = null;
			objMatcher = null;
		}
		return isValid;
	}

	@Override
	public Boolean isValidDate(String date, String dateFormat) {
		DateTimeFormatter objDateFormat = null;
		Boolean isValidDate = Boolean.FALSE;
		try {
			objDateFormat = DateTimeFormat.forPattern(dateFormat);
			LocalDate.parse(date, objDateFormat);
			isValidDate = Boolean.TRUE;
		} catch (Exception exception) {
			isValidDate = Boolean.FALSE;
		} finally {
			objDateFormat = null;
		}
		return isValidDate;
	}
	//
	// @Override
	// public Date getDate(String date, String dateFormat) {
	// DateTimeFormatter objDateFormat = null;
	// LocalDate objLocalDate = null;
	// Date objDate = null;
	// try {
	// objDateFormat = DateTimeFormat.forPattern(dateFormat);
	// objLocalDate = LocalDate.parse(date, objDateFormat);
	// objDate = objLocalDate.toDate();
	// } catch (Exception exception) {
	// objDate = null;
	// } finally {
	// objDateFormat = null;
	// objLocalDate = null;
	// }
	// return objDate;
	// }
	//
	// @Override
	// public Date currentDateWithoutTime() {
	// Date currentDate = null;
	// Calendar newDate = null;
	// try {
	// currentDate = new Date();
	// newDate = Calendar.getInstance();
	// newDate.setLenient(false);
	// newDate.setTime(currentDate);
	// newDate.set(Calendar.HOUR_OF_DAY, 0);
	// newDate.set(Calendar.MINUTE, 0);
	// newDate.set(Calendar.SECOND, 0);
	// newDate.set(Calendar.MILLISECOND, 0);
	// currentDate = newDate.getTime();
	// } finally {
	//
	// }
	// return currentDate;
	//
	// }

}
