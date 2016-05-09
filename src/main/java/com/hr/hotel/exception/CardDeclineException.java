package com.hr.hotel.exception;

public class CardDeclineException extends BaseWebApplicationException {

	public CardDeclineException() {
		super(402, "40201", "card is declined", "Not valid card");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
