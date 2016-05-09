package com.hr.hotel.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hr.hotel.constraint.impl.UserExistImpl;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserExistImpl.class)
public @interface UserExist {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String email();

	String userName();
	
	String fName();

	String lName();

	String emailProperty();

	String userNameProperty();

	String fNameProperty();

	String lNameProperty();

	String emailExistError();

	String invalidEmailError();

	String userNameExistError();

	String fNameEmptyError();

	String lNameEmptyError();

	String emailEmptyError();

	String userNameEmptyError();
}
