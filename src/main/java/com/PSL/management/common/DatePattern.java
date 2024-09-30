package com.PSL.management.common;

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
        String formattedDate = targetFormat.format(date);

        return formattedDate;

    }


    public static Date formatInDate(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        System.out.println("date----: " + date);
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = targetFormat.parse(date);
        System.out.println(date1);
        return date1;

    }


    public static String timeFormat(Date date){
        if(date ==null){
            return null;
        }
        DateFormat target = new SimpleDateFormat("h:mm:ss a");
        String formatted = target.format(date);
        return formatted;
    }

    public static Date timeFormatInDate(String date) throws ParseException {
        if(date ==null){
            return null;
        }
        DateFormat target = new SimpleDateFormat("h:mm:ss a");
       // String formatted = target.format(date);
        Date date1 = target.parse(date);
        return date1;
    }
}
