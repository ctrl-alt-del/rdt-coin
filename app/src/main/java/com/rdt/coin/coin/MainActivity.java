package com.rdt.coin.coin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.rdt.coin.coin.models.Point;
import com.rdt.coin.coin.presenters.impl.MainPresenter;
import com.rdt.coin.coin.utils.InputUtils;
import com.rdt.coin.coin.utils.JodaTimeUtils;
import com.rdt.coin.coin.views.IMainView;
import com.rdt.coin.coin.views.impl.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends BaseActivity implements IMainView {

    private MainPresenter mMainPresenter;
    private LineChart mChartView;
    private Calendar mStartDateCalendar;
    private EditText mStartDateEditText;
    private EditText mEndDateEditText;
    private Calendar mEndDateCalendar;
    private Snackbar mDataLoadedSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mChartView = (LineChart) findViewById(R.id.chart);
        mStartDateEditText = (EditText) findViewById(R.id.start_date);
        mEndDateEditText = (EditText) findViewById(R.id.end_date);
        setSupportActionBar(toolbar);
        mMainPresenter = new MainPresenter(this);

        mStartDateCalendar = Calendar.getInstance();
        mEndDateCalendar = Calendar.getInstance();

        mStartDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * passing the EditText, mStartDateCalendar, is not my preferred way,
                 * but I don't have much time, so I will just leave it alone.
                 * Ideally, I would create a custom DatePickerDialog class
                 */
                JodaTimeUtils.createDatePickerDialog(getActivity(), mStartDateCalendar, (EditText) v).show();
            }
        });

        mEndDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // same note as the other one in above
                JodaTimeUtils.createDatePickerDialog(getActivity(), mEndDateCalendar, (EditText) v).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        mDataLoadedSnackbar = Snackbar.make(fab, "", Snackbar.LENGTH_SHORT);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String points = InputUtils.getPoints(getActivity(), R.id.data_points, 10);
                String startTimestampText = InputUtils.getEditTextContent(mStartDateEditText);
                String endTimestampText = InputUtils.getEditTextContent(mEndDateEditText);

                if (!TextUtils.isEmpty(startTimestampText) && !TextUtils.isEmpty(endTimestampText)) {
                    // convert millisecond used in Java to second used server
                    long startTimestamp = JodaTimeUtils.convertMillisecondToSecond(mStartDateCalendar.getTimeInMillis());
                    long endTimestamp = JodaTimeUtils.convertMillisecondToSecond(mEndDateCalendar.getTimeInMillis());

                    if (endTimestamp < startTimestamp) {
                        // negative time range
                        mDataLoadedSnackbar.setText(R.string.negative_time_range).show();
                    } else {
                        mMainPresenter.getPoints(points, String.valueOf(startTimestamp), String.valueOf(endTimestamp));
                    }
                } else {
                    mMainPresenter.getPoints(points);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_clear_chart:
                mChartView.clear();
                mChartView.invalidate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onReceivedDataSucceed(List<Point> points) {

        List<Entry> prices = new ArrayList<>();
        List<String> timestamps = new ArrayList<>();
        // Again, this can be avoid if data from server is object oriented
        Point point;
        for (int i = 0; i < points.size(); i++) {
            point = points.get(i);
            timestamps.add(JodaTimeUtils.getReadableTime(point.getTime()));
            prices.add(new Entry(point.getPrice(), i));
        }
        // ---------
        mChartView.setData(new LineData(timestamps, new LineDataSet(prices, getString(R.string.price))));
        mChartView.invalidate();
        mDataLoadedSnackbar.setText(R.string.data_loaded).show();
    }

    @Override
    public void onReceivedDataFailed(String errorMessage) {
        mDataLoadedSnackbar.setText(errorMessage).show();
    }
}
