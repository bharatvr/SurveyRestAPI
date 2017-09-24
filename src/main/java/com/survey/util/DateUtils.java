package com.survey.util;

import java.sql.Date;

public class DateUtils {

	
	 public static Date getCurrentDate(){
			
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			return sqlDate;
		}
}
