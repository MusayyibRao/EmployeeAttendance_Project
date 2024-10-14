package com.emp.management.common;

public class CommonMonthsName {


    public static String getMonthName(String month) {
        int mon = Integer.parseInt(month);
        if (mon == 1) {
            return "JANUARY";
        }
        if (mon == 2) {
            return "FEBRUARY";
        }
        if (mon == 3) {
            return "MARCH";
        }
        if (mon == 4) {
            return "APRIL";
        }
        if (mon == 5) {
            return "MAY";
        }
        if (mon == 6) {
            return "JUNE";
        }
        if (mon == 7) {
            return "JULY";
        }
        if (mon == 8) {
            return "AUGUST";
        }
        if (mon == 9) {
            return "SEPTEMBER";
        }
        if (mon == 10) {
            return "OCTOBER";
        }
        if (mon == 11) {
            return "NOVEMBER";
        }
        if (mon == 12) {
            return "DECEMBER";
        }
        return null;
    }
}
