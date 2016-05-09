package com.hr.hotel.constraint.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import com.hr.hotel.constraint.PasswordMatch;

public class PasswordMatchImpl implements
		ConstraintValidator<PasswordMatch, Object> {

	private String password;
	private String emptyPasswordMessage;
	private String passwordProperty;
	private int min;
	private int max;
	private String passwordLengthMessage;

	private String repassword;
	private String repasswordProperty;
	private String emptyRepasswordMessage;

	@Override
	public void initialize(PasswordMatch pm) {
		password = pm.password();
		emptyPasswordMessage = pm.emptyPasswordMessage();
		passwordProperty = pm.passwordproperty();
		min = pm.min();
		max = pm.max();
		passwordLengthMessage = pm.passwordLengthMessage();

		repassword = pm.repassword();
		repasswordProperty = pm.repasswordproperty();
		emptyRepasswordMessage = pm.emptyRepasswordMessage();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {

		try {
			boolean isValid = true;
			// get field value
			String password = BeanUtils.getProperty(obj, this.password);
			String repassword = BeanUtils.getProperty(obj, this.repassword);

			if (!StringUtils.hasText(password)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						emptyPasswordMessage).addPropertyNode(passwordProperty)
						.addConstraintViolation();
				isValid = false;
			} else if (!(password.length() >= min)
					|| !(password.length() <= max)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						passwordLengthMessage)
						.addPropertyNode(passwordProperty)
						.addConstraintViolation();
				isValid = false;
			}

			if (!StringUtils.hasText(repassword)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						emptyRepasswordMessage)
						.addPropertyNode(repasswordProperty)
						.addConstraintViolation();
				isValid = false;
			}

			if (isValid) {
				if (!password.equals(repassword)) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(
							context.getDefaultConstraintMessageTemplate())
							.addPropertyNode(repasswordProperty)
							.addConstraintViolation();
					isValid = false;
				}
			}

			return isValid;
		} catch (Exception ex) {
			return false;
		}
	}
}
