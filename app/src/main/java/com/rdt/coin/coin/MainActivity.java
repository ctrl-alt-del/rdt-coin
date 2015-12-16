package com.rdt.coin.coin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.rdt.coin.coin.presenters.impl.MainPresenter;
import com.rdt.coin.coin.views.IMainView;
import com.rdt.coin.coin.views.impl.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IMainView {

    private MainPresenter mMainPresenter;
    private LineChart mChartView;
    private EditText mNumberOfPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mChartView = (LineChart) findViewById(R.id.chart);
        mNumberOfPoints = (EditText) findViewById(R.id.data_points);
        setSupportActionBar(toolbar);
        mMainPresenter = new MainPresenter(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                String points = InputUtils.getPoints(getActivity(), R.id.data_points, 10);
                mMainPresenter.getPoints(points);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onReceivedDataSucceed(List<Point> points) {

        List<Entry> prices = new ArrayList<>();
        List<String> timestamps = new ArrayList<>();
        Point point;
        for (int i = 0; i < points.size(); i++) {
            point = points.get(i);
            timestamps.add(JodaTimeUtils.getTime(point.getReadableTime()));
            prices.add(new Entry(point.getPrice(), i));
        }
        mChartView.setData(new LineData(timestamps, new LineDataSet(prices, getString(R.string.price))));
        mChartView.invalidate();
    }

    @Override
    public void onReceivedDataFailed(String errorMessage) {

    }
}
