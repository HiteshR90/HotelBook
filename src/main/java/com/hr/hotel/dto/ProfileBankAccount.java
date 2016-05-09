package com.hr.hotel.dto;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@GroupSequence({ CheckProfileBankAccountFirst.class,
		CheckProfileBankAccountSecond.class, ProfileBankAccount.class })
public class ProfileBankAccount {
	@NotEmpty(groups = { CheckProfileBankAccountFirst.class }, message = "Please Enter Bank Account Number")
	@Pattern(regexp = "\\d{9,20}", message = "Not valid account number", groups = { CheckProfileBankAccountSecond.class })
	private String bankAccountNumber;

	@NotEmpty(groups = { CheckProfileBankAccountFirst.class }, message = "Please Enter Bank Routing Number")
	@Pattern(regexp = "\\d{8,9}", message = "Not valid routing number", groups = { CheckProfileBankAccountSecond.class })
	private String bankRoutingNumber;

	public ProfileBankAccount(String bankAccountNumber, String bankRoutingNumber) {
		this.bankAccountNumber = bankAccountNumber;
		this.bankRoutingNumber = bankRoutingNumber;
	}

	public ProfileBankAccount() {
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankRoutingNumber() {
		return bankRoutingNumber;
	}

	public void setBankRoutingNumber(String bankRoutingNumber) {
		this.bankRoutingNumber = bankRoutingNumber;
	}

}

interface CheckProfileBankAccountFirst {

}

interface CheckProfileBankAccountSecond {

}