package com.github.fjtorres.validationService.service.constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DateMatchValidator.class)
@Documented
public @interface DateMatch {
	String message() default "{datematch}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	/**
	 * @return The first date field
	 */
	String first();

	/**
	 * @return The second date field
	 */
	String second();
	
	boolean ignoreNullValues() default true;
	
	DateMatchMode mode() default DateMatchMode.AFTER;
	
	public static enum DateMatchMode {
		AFTER, BEFORE
	}
}
