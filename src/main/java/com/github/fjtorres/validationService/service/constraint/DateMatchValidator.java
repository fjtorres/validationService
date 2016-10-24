package com.github.fjtorres.validationService.service.constraint;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;

import com.github.fjtorres.validationService.service.constraint.DateMatch.DateMatchMode;

public class DateMatchValidator implements ConstraintValidator<DateMatch, Object> {

	/**
	 * first field
	 */
	private String firstFieldName;

	/**
	 * Second field
	 */
	private String secondFieldName;

	private boolean ignoreNullValues;
	
	private DateMatchMode mode;

	@Override
	public void initialize(DateMatch constraintAnnotation) {
		this.firstFieldName = constraintAnnotation.first();
		this.secondFieldName = constraintAnnotation.second();
		this.ignoreNullValues = constraintAnnotation.ignoreNullValues();
		this.mode = constraintAnnotation.mode();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean result = true;
		try {
			final Object firstValue = PropertyUtils.getProperty(value, firstFieldName);
			final Object secondValue = PropertyUtils.getProperty(value, secondFieldName);

			if (firstValue == null || secondValue == null) {
				result = ignoreNullValues;
			} else if (firstValue instanceof Date && secondValue instanceof Date) {
				final Date firstDate = (Date) firstValue;
				final Date secondDate = (Date) secondValue;
				
				switch (mode) {
				case AFTER:
					result = firstDate.after(secondDate);
					break;
				case BEFORE:
					result = firstDate.before(secondDate);
					break;
				}
			} else {
				result = false;
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// FIXME Add logger error message
			result = false;
		}

		return result;
	}

}
