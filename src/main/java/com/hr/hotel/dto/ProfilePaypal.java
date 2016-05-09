package com.hr.hotel.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ProfilePaypal {
	public interface CheckProfilePaypal {

	}

	@Email(groups = { CheckProfilePaypal.class }, message = "Not valid email id")
	@NotEmpty(groups = { CheckProfilePaypal.class }, message = "Please enter paypal email id")
	private String paypalId;

	public ProfilePaypal() {

	}

	public ProfilePaypal(String paypalId) {
		this.paypalId = paypalId;
	}

	public String getPaypalId() {
		return paypalId;
	}

	public void setPaypalId(String paypalId) {
		this.paypalId = paypalId;
	}
}
