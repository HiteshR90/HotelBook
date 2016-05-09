package com.hr.hotel.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hr.hotel.common.Constant;
import com.hr.hotel.constraint.impl.DateValidImpl;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidImpl.class)
public @interface DateValid {

	String message() default "";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String checkInDate();

	String checkOutDate();

	String checkInPropertyName();

	String checkOutPropertyName();

	String dateFormat() default Constant.DATE_FORMAT;

	String checkInErrorMessage() default "Please Enter CheckIn Date";

	String checkOutErrorMessage() default "Please Enter CheckOut Date";

	String wrongPatternErrorMessage() default "Date must be in dd/MM/yyyy in format";

	String lessCheckInErrorMessage() default "Date Must Be Greate or equal Today's Date";

	String lessCheckOutErrorMessage() default "Date must be greater than CheckIn date";
}
