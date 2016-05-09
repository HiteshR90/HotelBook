package com.hr.hotel.exception;

public class AlreadyDeleagateException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyDeleagateException() {
		super(409, "40904", "User already delegate user",
				"Given user is already delegate for login user");
	}
}
