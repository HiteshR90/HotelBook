package com.hr.hotel.exception;

public class PathParamMissingException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PathParamMissingException() {
		super(400, "40003", "path parameter missing",
				"For request pata paramer is need");
	}
}
