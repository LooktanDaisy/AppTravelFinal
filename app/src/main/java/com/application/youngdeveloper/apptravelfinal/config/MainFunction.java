package com.application.youngdeveloper.apptravelfinal.config;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by theerawat on 2/23/2017 AD.
 */

public class MainFunction {
    public static String comma(double agr1) {
        if (agr1 != 0) {

            DecimalFormat formatter = new DecimalFormat("#,###");
            return formatter.format(agr1);
        }
        return "0";
    }

    public static String commaDouble(double agr1) {
        if (agr1 != 0) {

            DecimalFormat formatter = new DecimalFormat("#,##0.00");
            return formatter.format(agr1);
        }
        return "0.00";
    }


    public static String commaTwoDigit(double agr1) {
        if (agr1 != 0) {

            DecimalFormat formatter = new DecimalFormat("#,##0.##");
            return formatter.format(agr1);
        }
        return "0.00";
    }

    public static String deleteComma(String agr1) {
        if (agr1 != null) {
            return agr1.replace(",", "");
        }
        return agr1;
    }





    public static Date convertDateWithoutTime(Date lastUpdateInterest) {
        Date date = removeTime(lastUpdateInterest);

        return date;
    }



    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }



    public static int daysBetween(long t1, long t2) {
        return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
    }


    public static String getMonthFormInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }


    public static String getDateString(Date startdate) {

        int year=0,month=0,day=0;
        Calendar c = Calendar.getInstance();
        c.setTime(startdate);
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DATE);

        return String.format("%s %s %s", day, Provinces.months[month],year+543);

    }
}
