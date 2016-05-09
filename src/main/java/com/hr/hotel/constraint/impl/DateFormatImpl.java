package com.hr.hotel.constraint.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

import com.hr.hotel.constraint.DateFormat;

public class DateFormatImpl implements ConstraintValidator<DateFormat, String> {

	private String pattern;
	private String emptyString;
	private String wrongPattern;
	private boolean isRequire;

	@Override
	public void initialize(DateFormat constraintAnnotation) {
		pattern = constraintAnnotation.pattern();
		emptyString = constraintAnnotation.emptyString();
		wrongPattern = constraintAnnotation.wrongPattern();
		isRequire = constraintAnnotation.isRequire();
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {
		DateTimeFormatter objDateFormat = null;
		try {

			if (isRequire && !StringUtils.hasText(date)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(emptyString)
						.addConstraintViolation();
				return false;
			} else if (StringUtils.hasText(date)) {
				objDateFormat = DateTimeFormat.forPattern(pattern);
				LocalDate.parse(date, objDateFormat);
				return true;
			} else if (!isRequire) {
				return true;
			}
		} catch (Exception exception) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(wrongPattern)
					.addConstraintViolation();
			return false;
		} finally {
			objDateFormat = null;
		}
		return false;
	}
}
