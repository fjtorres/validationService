package com.github.fjtorres.validationService.service.constraint;

import java.util.Date;

import com.github.fjtorres.validationService.service.constraint.DateMatch.DateMatchMode;

@DateMatch(first = "date1", second = "date2", mode = DateMatchMode.BEFORE)
public class DateBeforeTest {
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