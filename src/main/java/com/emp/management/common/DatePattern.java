package com.emp.management.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePattern {

    public static String formatDate(Date date) throws ParseException {
        if (date == null) {
            return null;
        }
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateInString = targetFormat.format(date);
        return formattedDateInString;
    }


    public static Date formatInDate(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date formatedDate = targetFormat.parse(date);
        return formatedDate;
    }

    public static String timeFormat(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat target = new SimpleDateFormat("h:mm:ss a");
        String formattedTimeInString = target.format(date);
        return formattedTimeInString;
    }

    public static Date timeFormatInDate(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        DateFormat target = new SimpleDateFormat("h:mm:ss a");
        // String formatted = target.format(date);
        Date formattedTime = target.parse(date);
        return formattedTime;
    }

    public static String getMonthYearFormat(Date date) throws ParseException {

        if (date == null) {
            return null;
        }
        DateFormat targetFormat = new SimpleDateFormat("MM-yyyy");
        String formattedMonthYearInString = targetFormat.format(date);
        return formattedMonthYearInString;
    }

    public static Date getMonthYearFormatInDate(String date) throws ParseException {

        if (date == null) {
            return null;
        }
        DateFormat targetFormat = new SimpleDateFormat("MM-yyyy");
        Date formattedMonthYear = targetFormat.parse(date);
        return formattedMonthYear;
    }

    public static String getMonth(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat targetFormat = new SimpleDateFormat("MM");
        String formattedMonth = targetFormat.format(date);
        return formattedMonth;
    }
}
