package es.fjtorres.service.validation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ValidationServiceImplTest {

	public static class ValidationInternalBean {
		@NotNull
		private Integer number;

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}

	}

	public static class ValidationBean {
		@NotNull
		private String code;

		@NotNull
		private Integer number;

		@Valid
		private ValidationInternalBean internalBean;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public ValidationInternalBean getInternalBean() {
			return internalBean;
		}

		public void setInternalBean(ValidationInternalBean internalBean) {
			this.internalBean = internalBean;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}

	}

	private static final String ERROR_INVALID_FIELDS = "This object has invalid fields";
	private static final String ERROR_VALID_FIELDS = "This object is valid";
	private static final String STR_TEST = "TEST";

	private ValidationServiceImpl testInstance;

	private ValidationBean TEST_OBJECT;

	@BeforeMethod
	public void beforeMethod() {
		this.testInstance = new ValidationServiceImpl();

		final ValidationInternalBean internalBean = new ValidationInternalBean();
		internalBean.setNumber(1);

		TEST_OBJECT = new ValidationBean();
		TEST_OBJECT.setCode(STR_TEST);
		TEST_OBJECT.setNumber(1);
		TEST_OBJECT.setInternalBean(internalBean);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void isValidWithNullObject() {
		testInstance.isValid(null);
	}

	@Test
	public void isValidTestWithAllInvalidFields() {
		assertEquals(testInstance.isValid(new ValidationBean()), false, ERROR_INVALID_FIELDS);
	}

	@Test
	public void isValidWithValidFields() {
		assertEquals(testInstance.isValid(TEST_OBJECT), true, ERROR_VALID_FIELDS);
	}

	@Test
	public void validateWithAllInvalidFields() {
		checkConstraintResult(testInstance.validate(new ValidationBean()), 2);
	}

	@Test
	public void validateWithValidFields() {
		checkConstraintResult(testInstance.validate(TEST_OBJECT), 0);
	}

	private void checkConstraintResult(final Set<ConstraintViolation<ValidationBean>> result,
			final int expectedErrors) {
		assertNotNull(result,
				expectedErrors == 0 ? "This object doesn't have errors." : "This object has invalid fields.");
		assertEquals(result.size(), expectedErrors, "Expected " + expectedErrors + " errors.");
	}

	@Test
	public void validatePropertyValid() {
		checkConstraintResult(testInstance.validateProperty(TEST_OBJECT, "code"), 0);
	}

	@Test
	public void validatePropertyInvalid() {
		TEST_OBJECT.setCode(null);
		checkConstraintResult(testInstance.validateProperty(TEST_OBJECT, "code"), 1);
	}

	@Test
	public void isValidWithValidProperty() {
		assertEquals(testInstance.isValid(TEST_OBJECT, "code"), true);
	}

	@Test
	public void isValidWithInvalidProperty() {
		TEST_OBJECT.setCode(null);
		assertEquals(testInstance.isValid(TEST_OBJECT, "code"), false);
	}

	@Test
	public void validateValueValid() {
		checkConstraintResult(testInstance.validateValue(ValidationBean.class, "code", STR_TEST), 0);
	}

	@Test
	public void validateValueInvalid() {
		checkConstraintResult(testInstance.validateValue(ValidationBean.class, "code", null), 1);
	}

	@Test
	public void isValidWithValidValue() {
		assertEquals(testInstance.isValidValue(ValidationBean.class, "code", STR_TEST), true);
	}

	@Test
	public void isValidWithInvalidValue() {
		TEST_OBJECT.setCode(null);
		assertEquals(testInstance.isValidValue(ValidationBean.class, "code", null), false);
	}
}
