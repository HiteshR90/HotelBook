package com.hr.hotel.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.hr.hotel.constraint.PasswordMatch;

@PasswordMatch(password = "password", passwordproperty = "password", repassword = "retypePassword", repasswordproperty = "retypePassword", message = "Password Not Match", emptyPasswordMessage = "password must not empty", emptyRepasswordMessage = "retype password must not empty", max = 40, min = 8, passwordLengthMessage = "Password must be 8 to 40 char long", groups = {
		ResetPasswordDTO.RestResetpassword.class,
		ResetPasswordDTO.MVCResetpassword.class })
public class ResetPasswordDTO {
	public interface RestResetpassword {

	}

	public interface MVCResetpassword {

	}

	@NotEmpty(message = "token must not be empty", groups = { RestResetpassword.class })
	private String token;

	// @NotEmpty(message = "password must not empty", groups = {
	// RestResetpassword.class, MVCResetpassword.class })
	// @Size(min = 8, max = 40, groups = { RestResetpassword.class,
	// MVCResetpassword.class })
	private String password;

	// @NotEmpty(message = "retype password must not empty", groups = {
	// RestResetpassword.class, MVCResetpassword.class })
	private String retypePassword;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

}
