package com.megaflashgames.budgethelper.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vanyamihova on 20/06/2015.
 */
public class DateAndTimeUtil {

    public static String FULL_PATTERN = "EEE, d MMM yyyy, HH:mm";
    public static String DATE_PATTERN = "dd.MM.yyyy";

    private static DateAndTimeUtil Instance;
    private Calendar calendar;

    public static DateAndTimeUtil GetInstance() {
        if(Instance == null) {
            Instance = new DateAndTimeUtil();
        }
        return Instance;
    }


    private DateAndTimeUtil() { this.calendar = Calendar.getInstance(); }

    public void set(long timestamp) { this.calendar.setTimeInMillis(timestamp * 1000); }

    public void setTimeInMillis(long millis) {
        this.calendar.setTimeInMillis(millis);
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        return dateFormat.format(calendar.getTime());
    }

    public long getTimestamp() {
        return this.calendar.getTimeInMillis() / 1000;
    }

    public long setTimestamp(String timeString) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        Date date = new Date();
        try {
            date = dateFormat.parse(timeString);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return getTimestamp();
    }

}
