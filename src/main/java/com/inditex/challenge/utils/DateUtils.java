package com.inditex.challenge.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public Date stringToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        java.util.Date dateFormated = null;
        Date sqlDate;
        try {
            dateFormated = formatter.parse(date);
        } catch (Exception e) {
            throw new ParseException("Invalid date format", 0);
        }
        return dateFormated;
    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        return formatter.format(date);
    }

}
