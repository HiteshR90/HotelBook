package com.hr.hotel.constraint.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.hr.hotel.constraint.PatternMatch;

public class PatternMatchImpl implements
		ConstraintValidator<PatternMatch, String> {

	private String pattern;
	private String emptyString;
	private String wrongPattern;
	private boolean isRequire;

	@Override
	public void initialize(PatternMatch constraintAnnotation) {
		pattern = constraintAnnotation.pattern();
		emptyString = constraintAnnotation.emptyString();
		wrongPattern = constraintAnnotation.wrongPattern();
		isRequire = constraintAnnotation.isRequire();
	}

	@Override
	public boolean isValid(String input, ConstraintValidatorContext context) {
		if (isRequire && !StringUtils.hasText(input)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(emptyString)
					.addConstraintViolation();
			return false;
		} else if (StringUtils.hasText(input)) {
			if (match(pattern, input)) {
				return true;
			} else {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(wrongPattern)
						.addConstraintViolation();
				return false;
			}
		} else if (!isRequire) {
			return true;
		}

		return false;
	}

	private boolean match(String pattern, String input) {
		Pattern objPattern = Pattern.compile(pattern);
		Matcher objMatcher = objPattern.matcher(input);
		if (objMatcher.find()) {
			return true;
		} else {
			return false;
		}
	}
}
