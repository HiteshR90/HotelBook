package com.hr.hotel.exception;

public class EmptyCartException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyCartException() {
		super(400, "40002", "Cart is empty", "Cart is empty");
	}

	public EmptyCartException(String errorMessage, String developerMessage) {
		super(400, "40002", errorMessage, developerMessage);
	}

	public EmptyCartException(String errorMessage) {
		super(400, "40002", errorMessage, "Cart is empty");
	}

}
