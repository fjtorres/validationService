package com.github.fjtorres.validationService.service.constraint;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	/**
	 * first field
	 */
	private String firstFieldName;

	/**
	 * Second field
	 */
	private String secondFieldName;

	@Override
	public void initialize(final FieldMatch constraintAnnotation) {
		this.firstFieldName = constraintAnnotation.first();
		this.secondFieldName = constraintAnnotation.second();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean result = true;
		try {
			final Object firstValue = BeanUtils.getProperty(value, firstFieldName);
			final Object secondValue = BeanUtils.getProperty(value, secondFieldName);

			result = (firstValue == null && secondValue == null)
					|| (firstValue != null && firstValue.equals(secondValue));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// FIXME Add logger error message
			result = false;
		}

		return result;
	}

}
