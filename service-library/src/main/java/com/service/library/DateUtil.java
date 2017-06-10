package com.service.library;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtil implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3865736292493750177L;

    /** The Constant MILLISECONDS_IN_A_DAY. */
    public static final int MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;

    /** The Constant number of months in a year. */
    public static final int MONTHS_IN_A_YEAR = 12;

    /** The Constant number of days in a month. */
    public static final int DAYS_IN_A_MONTH = 30;

    /** The Constant DATE_PATTERN. */
    public static final String DATE_PATTERN = "dd-MM-yyyy";

    protected static final String SHORT_DATE_FORMAT_STRING = "dd/MM/yyyy";
    protected static final String LONG_DATE_FORMAT_STRING = "dd MMMMM yyyy";

    public static final DateFormat SHORT_DATE_FORMAT = new SimpleDateFormat(SHORT_DATE_FORMAT_STRING);
    public static final DateFormat LONG_DATE_FORMAT = new SimpleDateFormat(LONG_DATE_FORMAT_STRING);

    /** The Constant DATE_FORMAT. */
    private static final Format DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    private DateUtil() {
	// To prevent instantiation of this class.
    }

    public Date getBeginningOfTheMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_MONTH) > 1) {
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
	}
        return calendar.getTime();
    }

    public static int getAge(Date birthDate) {
	return getAge(birthDate, new Date());
    }

    public static int getAge(Date birthDate, Date aDate) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(aDate);
	Calendar dob = Calendar.getInstance();
	dob.setTime(birthDate);
	if (dob.after(cal)) {
            throw new IllegalArgumentException("Date not valid");
	}
	int year1 = cal.get(Calendar.YEAR);
	int year2 = dob.get(Calendar.YEAR);
	int age = year1 - year2;
	int month1 = cal.get(Calendar.MONTH);
	int month2 = dob.get(Calendar.MONTH);
	if (month2 > month1) {
            age--;
	} else if (month1 == month2) {
            int day1 = cal.get(Calendar.DAY_OF_MONTH);
            int day2 = dob.get(Calendar.DAY_OF_MONTH);
            if (day2 > day1) {
		age--;
            }
	}
	return age;
    }

    public static int getMonthOf2Date(Date date1, Date date2) {
	Calendar cal1 = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();
	cal1.setTime(date1);
	cal2.setTime(date2);
	if (cal2.after(cal1)) {
            throw new IllegalArgumentException("Date not valid");
	}
	long diff = cal1.getTimeInMillis() - cal2.getTimeInMillis();
	int days = (int) (diff / MILLISECONDS_IN_A_DAY);
	int years = (int) Math.floor(days / 365.24);
	int month = (int) Math.round((days - (years * 365.24)) / 30.4375);
	if (month == 12) {
            month = 0;
            years += 1;
	}
	return (years * 12) + month;
    }

    public static int getDaysBetweenDates(Date date1, Date date2) {
	if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("One or both dates are null.");
	}
	Calendar cal1 = Calendar.getInstance();
	cal1.setTime(date1);
	cal1.set(Calendar.HOUR_OF_DAY, 0);
	cal1.set(Calendar.MINUTE, 0);
	cal1.set(Calendar.SECOND, 0);
	cal1.set(Calendar.MILLISECOND, 0);
	Calendar cal2 = Calendar.getInstance();
	cal2.setTime(date2);
	cal2.set(Calendar.HOUR_OF_DAY, 0);
	cal2.set(Calendar.MINUTE, 0);
	cal2.set(Calendar.SECOND, 0);
	cal2.set(Calendar.MILLISECOND, 0);
	long days = Math.abs(cal2.getTimeInMillis() - cal1.getTimeInMillis());
	days /= MILLISECONDS_IN_A_DAY;
	return (int) days;
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
	}
	return DATE_FORMAT.format(date);
    }

    public static String formatDate(Date date, String datePattern) {
	Format dateFormat = new SimpleDateFormat(datePattern);
	return dateFormat.format(date);
    }

    public static String formatDate(Date date, String datePattern, Locale locale) {
        Format dateFormat = new SimpleDateFormat(datePattern, locale);
	return dateFormat.format(date);
    }

    /**
     * Get the current month (1-based, 1 is January).
     * 
     * @return Current month
     */
    public static int currentMonth() {
        Calendar now = Calendar.getInstance();
	// the month in Java is 0-based, but we use 1-based in the application
	return now.get(Calendar.MONTH) + 1;
    }

    public static int currentYear() {
	Calendar now = Calendar.getInstance();
	return now.get(Calendar.YEAR);
    }

    public static final synchronized Integer getYearFromDate(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	int year = cal.get(Calendar.YEAR);
        return year;
    }

    public static final synchronized Integer getMonthFromDate(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	int year = cal.get(Calendar.MONTH);
        return year + 1;
    }

    public static int getYear(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	return cal.get(Calendar.YEAR);
    }

    public static String formatShortDate(Date date) {
	if (date == null) {
            return "";
	} else {
            return SHORT_DATE_FORMAT.format(date);
	}
    }

    public static String formatLongDate(Date date) {
	if (date == null) {
            return "";
	} else {
            return LONG_DATE_FORMAT.format(date);
	}
    }

    public static Date parseShortDateString(String dateString) throws ParseException {
        return SHORT_DATE_FORMAT.parse(dateString);
    }

    public static Date parseLongDateString(String dateString) throws ParseException {
	return LONG_DATE_FORMAT.parse(dateString);
    }

    /** Map of Indonesian Month **/
    public static final String[] INDONESIAN_MONTH = new String[] { "Januari", "Februari", "Maret", "April", "Mei",
			"Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" };

    public static String getIndonesianStringDate(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	return cal.get(Calendar.DAY_OF_MONTH) + " " + INDONESIAN_MONTH[cal.get(Calendar.MONTH)] + " "			+ cal.get(Calendar.YEAR);
    }
	
    public static synchronized Date now(){
        return new Date();
    }
	
    public static synchronized Date toDate(String dateString){
	Date result = null;

	if (dateString != null && !dateString.equals("")){
            try {
		if(dateString.indexOf(":")>0)
                    result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
                else{
                    if(dateString.indexOf("-")>0)
                        result = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                    else
                        result = new SimpleDateFormat("yyyyMMddHHmmss").parse(dateString);
                }			
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
	
    public static synchronized Date getShortDate(){
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String datenow=simpleDateFormat.format(new Date());
        Date tglReg=null;
        try {
            tglReg=simpleDateFormat.parse(datenow);
	} catch (ParseException e) {
            e.printStackTrace();
	}
        return tglReg;
    }

    public static Date fromExcelDateToDate(String monthDashYear) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");
	Date result = new Date();
	try {
            result = formatter.parse(monthDashYear);
	} catch(ParseException pe) {
            System.out.println("Error parsing date: " + pe.getMessage());
	}
	return result;
    }

    public static Date getFirstLastDateOfMonth(Date date, boolean first) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	if (first) {
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
	} else {
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
	}
	return cal.getTime();
    }

}
