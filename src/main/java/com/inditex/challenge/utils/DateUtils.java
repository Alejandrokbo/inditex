package com.inditex.challenge.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date stringToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        java.util.Date dateFormatted;
        try {
            dateFormatted = formatter.parse(date);
        } catch (Exception e) {
            throw new ParseException("Invalid date format", 0);
        }
        return dateFormatted;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        return formatter.format(date);
    }

}
