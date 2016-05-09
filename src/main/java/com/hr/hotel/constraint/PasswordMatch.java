package com.hr.hotel.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hr.hotel.constraint.impl.PasswordMatchImpl;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchImpl.class)
public @interface PasswordMatch {

	String message() default "{notmatch.password}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * First field
	 */
	String password();

	String passwordproperty();

	String emptyPasswordMessage();

	int min();

	int max();

	String passwordLengthMessage();

	/**
	 * Second field
	 */
	String repassword();

	String repasswordproperty();

	String emptyRepasswordMessage();
}
