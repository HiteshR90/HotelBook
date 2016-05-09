package com.hr.hotel.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import com.hr.hotel.constraint.PasswordMatch;
import com.hr.hotel.constraint.UserExist;

//@PasswordMatch(password = "password", repassword = "retypePassword", property = "retypePassword", message = "Password Not Match", groups = {
@PasswordMatch(password = "password", passwordproperty = "password", repassword = "retypePassword", repasswordproperty = "retypePassword", message = "Password Not Match", emptyPasswordMessage = "password must not empty", emptyRepasswordMessage = "retype password must not empty", max = 40, min = 8, passwordLengthMessage = "Password must be 8 to 40 char long", groups = {
		SignupUser.MvcSignUpUser.class, SignupUser.RestSignUpUser.class })
@UserExist(email = "email", emailEmptyError = "{NotEmpty.userMaster.email}", invalidEmailError = "Not Valid Email", emailExistError = "Email Already Exist!", emailProperty = "email", userName = "userName", userNameEmptyError = "{NotEmpty.userMaster.userName}", userNameExistError = "UserName Already Exist!", userNameProperty = "userName", groups = {
		SignupUser.MvcSignUpUser.class, SignupUser.RestSignUpUser.class }, fName = "fName", fNameEmptyError = "Please enter first name", lName = "lName", lNameEmptyError = "Please enter last name", fNameProperty = "fName", lNameProperty = "lName")
public class SignupUser {

	public interface RestSignUpUser {
	}

	public interface MvcSignUpUser {
	}

	// @NotEmpty(message = "{NotEmpty.userMaster.userName}", groups = {
	// RestSignUpUser.class, MvcSignUpUser.class })
	private String userName;

	private String fName;

	private String lName;

	// @NotEmpty(message = "{NotEmpty.userMaster.email}", groups = {
	// RestSignUpUser.class, MvcSignUpUser.class })
	// @Email(message = "{Email}")
	private String email;

	// @NotEmpty(message = "{NotEmpty.userMaster.password}", groups = {
	// RestSignUpUser.class, MvcSignUpUser.class })
	// @Size(min = 8, max = 40, groups = { RestSignUpUser.class,
	// MvcSignUpUser.class })
	private String password;

	// @NotEmpty(message = "{NotEmpty.userMaster.retypePassword}", groups = {
	// RestSignUpUser.class, MvcSignUpUser.class })
	private String retypePassword;

	// @AssertTrue(message = "{NotEmpty.userMaster.termAgreement}")
	// private Boolean termAgree;

	@NotEmpty(message = "Please enter redirect url after user activation", groups = { RestSignUpUser.class })
	@URL(message = "Not valid url", groups = { RestSignUpUser.class })
	private String redirectURL;

	@AssertTrue(message = "Please aggree terms and conditions", groups = {
			RestSignUpUser.class, MvcSignUpUser.class })
	@NotNull(message = "Please aggree terms and conditions", groups = {
			RestSignUpUser.class, MvcSignUpUser.class })
	private Boolean isAgree;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	/*
	 * public Boolean getTermAgree() { return termAgree; }
	 * 
	 * public void setTermAgree(Boolean termAgree) { this.termAgree = termAgree;
	 * }
	 */

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public Boolean getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(Boolean isAgree) {
		this.isAgree = isAgree;
	}

}