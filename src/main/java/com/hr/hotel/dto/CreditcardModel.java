package com.hr.hotel.dto;

import com.hr.hotel.constraint.PatternMatch;

public class CreditcardModel {

	// @NotEmpty(message = "Please Select card type")
	// private String cardType;

	@PatternMatch(isRequire = true, emptyString = "Please Enter Credit Card Number", pattern = "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$", wrongPattern = "{error.paymentDetail.number.pattern}")
	private String cardNumber;

	@PatternMatch(isRequire = true, emptyString = "Please Enter Exp Month", pattern = "^(0?[1-9]|1[0-2])$", wrongPattern = "{error.paymentDetail.expMonth.pattern}")
	private String expMonth;

	// match year between 2014-2050
	@PatternMatch(isRequire = true, emptyString = "Please Enter Exp Year", pattern = "\\d{4}", wrongPattern = "{error.paymentDetail.expYear.pattern}")
	private String expYear;

	// check that cvv number is three to four digit long
	@PatternMatch(isRequire = true, emptyString = "Please Enter CVV", pattern = "\\d{3,4}", wrongPattern = "{error.paymentDetail.cvv.pattern}")
	private String cvv;

	private String nameOnCard;

	private String address;

	private String city;

	private String state;

	private String zipCode;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	// public String getCardType() {
	// return cardType;
	// }
	//
	// public void setCardType(String cardType) {
	// this.cardType = cardType;
	// }

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}