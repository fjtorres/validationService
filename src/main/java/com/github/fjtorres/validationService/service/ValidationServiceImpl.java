package es.fjtorres.service.validation;

import java.util.Set;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Named
@Singleton
public class ValidationServiceImpl implements IValidationService {

    private ValidatorFactory validatorFactory;

    public ValidationServiceImpl () {
    	validatorFactory = Validation.buildDefaultValidatorFactory();
    }
    
    public ValidationServiceImpl (final ValidatorFactory pValidatorFactory) {
    	this.validatorFactory = pValidatorFactory;
    }   
    
    private Validator getValidator() {
		if (validatorFactory == null) {
			throw new IllegalStateException("validator factory is null");
		}
        return validatorFactory.getValidator();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validate(final T pObjectToValidate,
            final Class<?>... pGroups) {
        return getValidator().validate(pObjectToValidate, pGroups);
    }

    @Override
    public <T> boolean isValid(final T pObjectToValidate, final Class<?>... pGroups) {
        return validate(pObjectToValidate, pGroups).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(final T pObjectToValidate,
            final String pPropertyName, final Class<?>... pGroups) {
        return getValidator().validateProperty(pObjectToValidate, pPropertyName, pGroups);
    }

    @Override
    public <T> boolean isValid(final T pObjectToValidate, final String pPropertyName,
            final Class<?>... pGroups) {
        return validateProperty(pObjectToValidate, pPropertyName, pGroups).isEmpty();
    }

    @Override
    public <T> boolean isValidValue(final Class<T> pType, final String pPropertyName,
            final Object pValue, final Class<?>... pGroups) {
        return validateValue(pType, pPropertyName, pValue, pGroups).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(final Class<T> pType,
            final String pPropertyName, final Object pValue, final Class<?>... pGroups) {
        return getValidator().validateValue(pType, pPropertyName, pValue, pGroups);
    }
}
