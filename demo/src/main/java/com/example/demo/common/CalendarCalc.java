package com.example.demo.common;

import java.util.Calendar;
import java.util.Date;

public class CalendarCalc {
	
	public Date getDateYesterday(Date date) {
		
		Calendar cal = Calendar.getInstance();	
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		
		return cal.getTime();
		
	}
	
}