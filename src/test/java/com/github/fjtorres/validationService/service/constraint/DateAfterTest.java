package com.github.fjtorres.validationService.service.constraint;

import java.util.Date;

@DateMatch(first = "date1", second = "date2")
public class DateAfterTest {
	private Date date1;

	private Date date2;

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}
}