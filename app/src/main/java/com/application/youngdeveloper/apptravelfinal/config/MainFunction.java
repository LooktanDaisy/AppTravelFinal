package com.application.youngdeveloper.apptravelfinal.config;

import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListDao;

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



    public static ArrayList<AccommodationListDao> SortByCostAccom(ArrayList<AccommodationListDao> AccomByCost){
        Collections.sort(AccomByCost, new Comparator<AccommodationListDao>() {
            @Override
            public int compare(AccommodationListDao accom1, AccommodationListDao accom2) {
                /**
                 * Less to More
                 */
                return Double.compare(accom1.getPrice(), accom2.getPrice());
                /**
                * More to Less
                 */
//                return Double.compare(accom1.getPrice(), accom2.getPrice());
            }
        });

        return AccomByCost;
    }

    public static ArrayList<RestaurantListDao> SortByCostRestaurant(ArrayList<RestaurantListDao> RestaurantByCost){
        Collections.sort(RestaurantByCost, new Comparator<RestaurantListDao>() {
            @Override
            public int compare(RestaurantListDao rest1, RestaurantListDao rest2) {
                return Double.compare(rest1.getPrice(), rest2.getPrice());
            }
        });

        return RestaurantByCost;
    }

    public static ArrayList<RestaurantListDao> SortByDistanceRestaurant(ArrayList<RestaurantListDao> RestaurantByCost){
        Collections.sort(RestaurantByCost, new Comparator<RestaurantListDao>() {
            @Override
            public int compare(RestaurantListDao rest1, RestaurantListDao rest2) {
                return Double.compare(rest1.getHowfarToAccom(), rest2.getHowfarToAccom());
            }
        });

        return RestaurantByCost;
    }


    public static ArrayList<PlaceListDao> SortByDistancePlace(ArrayList<PlaceListDao> PlaceByType){
        Collections.sort(PlaceByType, new Comparator<PlaceListDao>() {
            @Override
            public int compare(PlaceListDao place1, PlaceListDao place2) {
                return Double.compare(place1.getHowfarToAccom(), place2.getHowfarToAccom());
            }
        });

        return PlaceByType;
    }

    public static double distance(double lat1, double lat2, double lng1,
                                  double lng2) {

        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);
        dist = dist/1000;

        /**
         * return Kilo Meters
         */
        return dist;
    }

}
