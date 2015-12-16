package com.rdt.coin.coin.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;

import com.rdt.coin.coin.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class JodaTimeUtils {
    private static final String DEFAULT_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DEFAULT_TIME_FORMAT);

    public static String getReadableTime(long timestamp) {
        // convert second to millisecond because Java is looking for millisecond
        DateTime dateTime = new DateTime(TimeUnit.MILLISECONDS.convert(timestamp, TimeUnit.SECONDS));
        return dateTime.toString(DEFAULT_DATE_TIME_FORMATTER);
    }

    public static DatePickerDialog createDatePickerDialog(final Activity activity, final Calendar calendar, final EditText datePickerDisplayView) {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                datePickerDisplayView.setText(getReadableCalendarDate(activity, calendar));
            }

        };
        return new DatePickerDialog(activity, callback, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String getReadableCalendarDate(Context context, Calendar calendar) {
        return context.getString(R.string.date_picker_format, calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR));
    }
}
