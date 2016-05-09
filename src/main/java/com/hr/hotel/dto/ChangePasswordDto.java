package com.hr.hotel.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.hr.hotel.constraint.PasswordMatch;

@PasswordMatch(password = "newPassword", passwordproperty = "newPassword", repassword = "retypeNewPassword", repasswordproperty = "retypeNewPassword", message = "Password Not Match", emptyPasswordMessage = "password must not empty", emptyRepasswordMessage = "retype password must not empty", max = 40, min = 8, passwordLengthMessage = "Password must be 8 to 40 char long")
public class ChangePasswordDto {

	private String newPassword;

	private String retypeNewPassword;

	@NotEmpty(message = "Please enter current password")
	private String currentPassword;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRetypeNewPassword() {
		return retypeNewPassword;
	}

	public void setRetypeNewPassword(String retypeNewPassword) {
		this.retypeNewPassword = retypeNewPassword;
	}

}
