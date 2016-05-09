package com.hr.hotel.constraint.impl;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import com.hr.hotel.constraint.DateValid;
import com.hr.hotel.util.DateUtil;

public class DateValidImpl implements ConstraintValidator<DateValid, Object> {

	private String checkInDate;

	private String checkOutDate;

	private String checkInPropertyName;

	private String checkOutPropertyName;

	private String dateFormat;

	private String checkInErrorMessage;

	private String checkOutErrorMessage;

	private String wrongPatternErrorMessage;

	private String lessCheckInErrorMessage;

	private String lessCheckOutErrorMessage;

	@Override
	public void initialize(DateValid dateValid) {
		checkInDate = dateValid.checkInDate();
		checkOutDate = dateValid.checkOutDate();
		checkInPropertyName = dateValid.checkInPropertyName();
		checkOutPropertyName = dateValid.checkOutPropertyName();
		dateFormat = dateValid.dateFormat();
		checkInErrorMessage = dateValid.checkInErrorMessage();
		checkOutErrorMessage = dateValid.checkOutErrorMessage();
		wrongPatternErrorMessage = dateValid.wrongPatternErrorMessage();
		lessCheckInErrorMessage = dateValid.lessCheckInErrorMessage();
		lessCheckOutErrorMessage = dateValid.lessCheckOutErrorMessage();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		Boolean isValid = Boolean.TRUE;
		Date objStartDate = null;
		Date objEndDate = null;
		Date currentDateWithoutTime = null;
		try {
			// get field value
			String startDate = BeanUtils.getProperty(obj, this.checkInDate);
			String endDate = BeanUtils.getProperty(obj, this.checkOutDate);

			if (!StringUtils.hasText(startDate)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						checkInErrorMessage)
						.addPropertyNode(checkInPropertyName)
						.addConstraintViolation();
				isValid = false;
			} else {
				objStartDate = DateUtil.getDate(startDate, dateFormat);
				if (objStartDate != null) {
					currentDateWithoutTime = DateUtil.currentDateWithoutTime();
					if (!objStartDate.equals(currentDateWithoutTime)
							&& objStartDate.before(currentDateWithoutTime)) {
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate(
								lessCheckInErrorMessage)
								.addPropertyNode(checkInPropertyName)
								.addConstraintViolation();
						isValid = false;
					}
				} else {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(
							wrongPatternErrorMessage)
							.addPropertyNode(checkInPropertyName)
							.addConstraintViolation();
					isValid = false;
				}
			}
			if (!StringUtils.hasText(endDate)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						checkOutErrorMessage)
						.addPropertyNode(checkOutPropertyName)
						.addConstraintViolation();
				isValid = false;
			} else {
				objEndDate = DateUtil.getDate(endDate, dateFormat);
				if (objEndDate != null) {
					if (objStartDate != null
							&& (objEndDate.before(objStartDate) || objEndDate
									.equals(objStartDate))) {
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate(
								lessCheckOutErrorMessage)
								.addPropertyNode(checkOutPropertyName)
								.addConstraintViolation();
						isValid = false;
					}
				} else {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(
							wrongPatternErrorMessage)
							.addPropertyNode(checkOutPropertyName)
							.addConstraintViolation();
					isValid = false;
				}
			}
		} catch (Exception ex) {
			return isValid;
		}
		return isValid;
	}
}