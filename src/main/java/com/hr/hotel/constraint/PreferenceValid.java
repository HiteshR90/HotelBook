package com.hr.hotel.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hr.hotel.constraint.impl.PreferenceValidImpl;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PreferenceValidImpl.class)
public @interface PreferenceValid {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String roomType();

	String roomTypeProperty();

	String roomTypeError();

	String hotelBrand();

	String hotelBrandProperty();

	String hotelBrandError();
}
