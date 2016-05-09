package com.hr.hotel.exception;

public class NotVerifiedException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotVerifiedException() {
		super(403, "40303", "User is not verified", "User not verified account");
	}
}
