package com.rdt.coin.coin;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.TimeUnit;

public class JodaTimeUtils {
    private static final String DEFAULT_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
    private static final DateTimeFormatter dtf = DateTimeFormat.forPattern(DEFAULT_TIME_FORMAT);

    public static String getTime(long timestamp) {
        // convert second to millisecond because Java is looking for millisecond
        DateTime dateTime = new DateTime(TimeUnit.MILLISECONDS.convert(timestamp, TimeUnit.SECONDS));
        return dateTime.toString(dtf);
    }
}
