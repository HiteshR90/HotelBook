package com.hr.hotel.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

public class PaypalRedirect {
	@NotEmpty(message = "Please Enter URL Return Url")
	@URL(message = "Not Valid URL")
	private String returnURL;

	@NotEmpty(message = "Please Enter URL Cancel Url")
	@URL(message = "Not Valid URL")
	private String cancelURL;

	public String getReturnURL() {
		return returnURL;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}

	public String getCancelURL() {
		return cancelURL;
	}

	public void setCancelURL(String cancelURL) {
		this.cancelURL = cancelURL;
	}

}
