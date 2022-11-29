package com.example.demo.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import static com.example.demo.common.WebConst.SECONDS;
import static com.example.demo.common.WebConst.MINUTES;
import static com.example.demo.common.WebConst.HOUR;
import static com.example.demo.common.WebConst.HOURS24;
import static com.example.demo.common.WebConst.DAY;
import static com.example.demo.common.WebConst.DAYS;
import static com.example.demo.common.WebConst.DATE;
import static com.example.demo.common.WebConst.MONTH;
import static com.example.demo.common.WebConst.POSTGRES_FORMAT_SECONDS;
import static com.example.demo.common.WebConst.POSTGRES_FORMAT_MINUTES;
import static com.example.demo.common.WebConst.POSTGRES_FORMAT_HOUR;
import static com.example.demo.common.WebConst.POSTGRES_FORMAT_HOURS24;
import static com.example.demo.common.WebConst.POSTGRES_FORMAT_HOURS00;
import static com.example.demo.common.WebConst.POSTGRES_FORMAT_DAY;
import static com.example.demo.common.WebConst.POSTGRES_FORMAT_DAYS;
import static com.example.demo.common.WebConst.POSTGRES_FORMAT_DATE;
import static com.example.demo.common.WebConst.POSTGRES_FORMAT_MONTH;
import static com.example.demo.common.WebConst.MYSQL_FORMAT_SECONDS;
import static com.example.demo.common.WebConst.MYSQL_FORMAT_MINUTES;
import static com.example.demo.common.WebConst.MYSQL_FORMAT_HOUR;
import static com.example.demo.common.WebConst.MYSQL_FORMAT_DATE;
import static com.example.demo.common.WebConst.MYSQL_FORMAT_MONTH;
import static com.example.demo.common.WebConst.STRING_ZERO;
import static com.example.demo.common.WebConst.STRING_ONE;
import static com.example.demo.common.WebConst.STRING_TWO;
import static com.example.demo.common.WebConst.STRING_THREE;
import static com.example.demo.common.WebConst.STRING_FOUR;
import static com.example.demo.common.WebConst.STRING_FIVE;
import static com.example.demo.common.WebConst.STRING_SIX;
import static com.example.demo.common.WebConst.SUNDAY;
import static com.example.demo.common.WebConst.MONDAY;
import static com.example.demo.common.WebConst.TUESDAY;
import static com.example.demo.common.WebConst.WEDNESDAY;
import static com.example.demo.common.WebConst.THURSDAY;
import static com.example.demo.common.WebConst.FRIDAY;
import static com.example.demo.common.WebConst.SATURDAY;

public class Formatter {
	
    public String getFormatPostgreSql(String dataOption) {
    	
    	String format = "";
    	
    	switch (dataOption) {

		  case SECONDS:
			  format = POSTGRES_FORMAT_SECONDS;
			  break;
		  case MINUTES:
			  format = POSTGRES_FORMAT_MINUTES;
			  break;
    	  case HOUR:
			  format = POSTGRES_FORMAT_HOUR;
			  break;
    	  case HOURS24:
			  format = POSTGRES_FORMAT_HOURS24;
			  break;
    	  case DAY:
			  format = POSTGRES_FORMAT_DAY;
			  break;
    	  case DAYS:
			  format = POSTGRES_FORMAT_DAYS;
			  break;
    	  case DATE:
			  format = POSTGRES_FORMAT_DATE;
			  break;
    	  case MONTH:
			  format = POSTGRES_FORMAT_MONTH;
			  break;
    	  }
    	
    	return format;
    	
    }
    
    public String getFormatMySql(String dateOption) {
    	
    	String dateFormat = "";
    	
    	switch (dateOption) {

		  case SECONDS:
			  dateFormat = MYSQL_FORMAT_SECONDS;
		    break;
		  case MINUTES:
			  dateFormat = MYSQL_FORMAT_MINUTES;
		    break;
    	  case HOUR:
    		  dateFormat = MYSQL_FORMAT_HOUR;
    	    break;
    	  case DATE:
    		  dateFormat = MYSQL_FORMAT_DATE;
    	    break;
    	  case MONTH:
    		  dateFormat = MYSQL_FORMAT_MONTH;
      	    break;
    	  }
    	;
    	
    	return dateFormat;
    	
    }
    
    public String convertDateFormat(Date packet_ts, String format) {
    	
    	SimpleDateFormat dateFormat;
    	
    	switch (format) {

		  case SECONDS:
			  dateFormat = new SimpleDateFormat(POSTGRES_FORMAT_SECONDS);
		    break;
		  case MINUTES:
			  dateFormat = new SimpleDateFormat(POSTGRES_FORMAT_MINUTES);
		    break;
    	  case HOUR:
    		  dateFormat = new SimpleDateFormat(POSTGRES_FORMAT_HOUR);
    	    break;
    	  case DAYS:
    		  dateFormat = new SimpleDateFormat(POSTGRES_FORMAT_DAYS);
    	    break;
    	  case DATE:
    		  dateFormat = new SimpleDateFormat(POSTGRES_FORMAT_DATE);
    	    break;
    	  case MONTH:
    		  dateFormat = new SimpleDateFormat(POSTGRES_FORMAT_MONTH);
      	    break;
    	  default:
    		  dateFormat = new SimpleDateFormat(POSTGRES_FORMAT_HOURS00);
    	  }

    	return dateFormat.format(packet_ts);
    	
    }
    
    public String getDay(String dayNum) {
    	
    	String day = "";
    	
    	switch (dayNum) {

		  case STRING_ZERO:
			  day = SUNDAY;
		    break;
		  case STRING_ONE:
			  day = MONDAY;
		    break;
    	  case STRING_TWO:
			  day = TUESDAY;
    	    break;
    	  case STRING_THREE:
			  day = WEDNESDAY;
    	    break;
    	  case STRING_FOUR:
			  day = THURSDAY;
    	    break;
    	  case STRING_FIVE:
			  day = FRIDAY;
      	    break;
    	  case STRING_SIX:
			  day = SATURDAY;
      	    break;
    	  }

    	return day;
    
    }
    
    
	
}