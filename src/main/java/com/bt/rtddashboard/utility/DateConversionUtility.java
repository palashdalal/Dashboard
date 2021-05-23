/**
 * 
 */
package com.bt.rtddashboard.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author 609669080
 *
 */
public class DateConversionUtility {
	
	public static final String ORACLETIMESTAMPFORMAT="yyyy-MM-dd hh:mm:ss";
	public static final String FORMAT1="dd-MMM-yyyy hh:mm:ss";
	public static final String FORMAT2="dd-MMM-yyyy hh-mm-ss";
	public static final String DATE_FORMAT_NOW = "E MMM dd HH:mm:ss Z yyyy";
	
	public static String convertDateToString(Date inputDate){
		String convertedDate=null;
		try {
			System.out.println(inputDate);
			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			String date = formatter.format(inputDate);
			System.out.println(date);        
			convertedDate=convertDateToAnotherFormat(date,DATE_FORMAT_NOW, FORMAT1);
		//	System.out.println(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertedDate;
	}
	
	public static String convertDateToFormat1(String inputDate){
		String convertedDate=inputDate;
		SimpleDateFormat format1 = new SimpleDateFormat(ORACLETIMESTAMPFORMAT);
		SimpleDateFormat format2 = new SimpleDateFormat(FORMAT2);
		Date date;
		try {
			date = format1.parse(inputDate);
			convertedDate=date.toString();
			System.out.println(format2.format(date));
			System.out.println(convertedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return convertedDate;
	}
	
	public static String convertDateToAnotherFormat(String inputDate,String format1, String format2){
		DateFormat fromFormat = new SimpleDateFormat(format1);
		fromFormat.setLenient(false);
		DateFormat toFormat = new SimpleDateFormat(format2);
		toFormat.setLenient(false);
		String convertedDate;
		try {
			Date sDate = fromFormat.parse(inputDate);
			convertedDate=toFormat.format(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		return convertedDate;
	}
	
	
	public static String getCurrentDateTimeinString(String format){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date=sdf.format(cal.getTime());
		System.out.println(date);
		return date;
		
	}
	
	public static void getCurrentDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Date date = new Date();
		 System.out.println(date);;
	}
	
	public static String incrementDateBy1(String inputDate,String format) throws ParseException{
		
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(inputDate));
		c.add(Calendar.DATE, 1);  // number of days to add
		inputDate = sdf.format(c.getTime()); 
		return inputDate;
	}
	
	public static Integer getHMSFromDate(String inDate,String Format,String retFlag){
		int retData=0;
	//	String string = "2016-8-19 10:55:0. 0";"yyyy-MM-dd hh:mm:ss"
		DateFormat format = new SimpleDateFormat(Format, Locale.ENGLISH);
		try {
			Date date = format.parse(inDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(retFlag.equalsIgnoreCase("HOUR")){
				retData = calendar.get(Calendar.HOUR_OF_DAY);
			}else if(retFlag.equalsIgnoreCase("MIN")){
				retData = calendar.get(Calendar.MINUTE);
			}else if(retFlag.equalsIgnoreCase("MIN")){
				retData = calendar.get(Calendar.SECOND);
			}
		//	hours = calendar.get(Calendar.HOUR_OF_DAY);
			//int minutes = calendar.get(Calendar.MINUTE);
			//int seconds = calendar.get(Calendar.SECOND);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retData;
	}
	public static String getTimeFromDate(String inDate,String Format){
		 String convertedDate=convertDateToAnotherFormat(inDate,Format,"hh:mm:ss");
		 return convertedDate;
	}
	public static String getHourFromDate(String inDate,String Format){
		 String convertedDate=convertDateToAnotherFormat(inDate,Format,"hh:00");
		 return convertedDate;
	}
	
	
	public static void main (String a[]) throws ParseException{
		//Date dateStr = new Date("Mon Jun 18 00:00:00 IST 2012");
		/*DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		String date = formatter.format(new Date());*/
		//System.out.println(getCurrentDateTimeinString()); 
		getCurrentDateTime();
		 System.out.println(getHourFromDate("2016-8-19 12:55:0. 0","yyyy-MM-dd hh:mm:ss"));
		 //System.out.println(getHMSFromDate("2016-8-19 10:55:0. 0","yyyy-MM-dd hh:mm:ss", "MIN"));
		// System.out.println(getHMSFromDate("2016-8-19 10:55:0. 0","yyyy-MM-dd hh:mm:ss", "SEC"));
	}
}
