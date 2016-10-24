package com.github.fjtorres.validationService.service.constraint;

import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DateMatchValidatorTest {

	private DateMatchValidator testInstance;
	
	@BeforeMethod
	public void before() {
		this.testInstance = new DateMatchValidator();
	}

	@Test
	public void testNullDates() {
		DateAfterTest test = new DateAfterTest();
		testInstance.initialize(test.getClass().getAnnotation(DateMatch.class));
		Assert.assertTrue(testInstance.isValid(test, null), "");
	}
	
	@Test
	public void testAfterDates() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		DateAfterTest test = new DateAfterTest();
		test.setDate1(cal.getTime());
		
		cal.roll(Calendar.DAY_OF_YEAR, -1);
		test.setDate2(cal.getTime());
		
		testInstance.initialize(test.getClass().getAnnotation(DateMatch.class));
		Assert.assertTrue(testInstance.isValid(test, null), "");
	}
	
	@Test
	public void testFalseAfterDates() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		DateAfterTest test = new DateAfterTest();
		test.setDate1(cal.getTime());
		
		cal.roll(Calendar.DAY_OF_YEAR, 1);
		test.setDate2(cal.getTime());
		
		testInstance.initialize(test.getClass().getAnnotation(DateMatch.class));
		Assert.assertFalse(testInstance.isValid(test, null), "");
	}
	
	@Test
	public void testAfterWithFirstDateNull() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		DateAfterTest test = new DateAfterTest();
		test.setDate2(cal.getTime());
		
		testInstance.initialize(test.getClass().getAnnotation(DateMatch.class));
		Assert.assertTrue(testInstance.isValid(test, null), "");
	}
	
	@Test
	public void testAfterWithSecondDateNull() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		DateAfterTest test = new DateAfterTest();
		test.setDate1(cal.getTime());
		
		testInstance.initialize(test.getClass().getAnnotation(DateMatch.class));
		Assert.assertTrue(testInstance.isValid(test, null), "");
	}
	
	@Test
	public void testBeforeDates() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		DateBeforeTest test = new DateBeforeTest();
		test.setDate1(cal.getTime());
		
		cal.roll(Calendar.DAY_OF_YEAR, 1);
		test.setDate2(cal.getTime());
		
		final DateMatch annotation = test.getClass().getAnnotation(DateMatch.class);
		testInstance.initialize(annotation);
		Assert.assertTrue(testInstance.isValid(test, null), "");
	}
	
	@Test
	public void testFalseBeforeDates() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		DateBeforeTest test = new DateBeforeTest();
		test.setDate1(cal.getTime());
		
		cal.roll(Calendar.DAY_OF_YEAR, -1);
		test.setDate2(cal.getTime());
		
		final DateMatch annotation = test.getClass().getAnnotation(DateMatch.class);
		testInstance.initialize(annotation);
		Assert.assertFalse(testInstance.isValid(test, null), "");
	}
	
	@Test
	public void testBeforeWithFirstDateNull() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		DateBeforeTest test = new DateBeforeTest();
		test.setDate2(cal.getTime());
		
		final DateMatch annotation = test.getClass().getAnnotation(DateMatch.class);
		testInstance.initialize(annotation);
		Assert.assertTrue(testInstance.isValid(test, null), "");
	}
	
	@Test
	public void testBeforeWithSecondDateNull() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		DateBeforeTest test = new DateBeforeTest();
		test.setDate1(cal.getTime());
		
		final DateMatch annotation = test.getClass().getAnnotation(DateMatch.class);
		testInstance.initialize(annotation);
		Assert.assertTrue(testInstance.isValid(test, null), "");
	}
}
