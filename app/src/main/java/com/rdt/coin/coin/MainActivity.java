package com.rdt.coin.coin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mChartView = (LineChart) findViewById(R.id.chart);
        setSupportActionBar(toolbar);
        mMainPresenter = new MainPresenter(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mMainPresenter.getPoints("10");
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
        List<Entry> x = new ArrayList<>();
        List<String> y = new ArrayList<>();
        int i = 0;
        for (Point point : points) {
            y.add(point.getTime());
            x.add(new Entry(Float.valueOf(point.getPrice()), i));
            i++;
            LogUtils.debug("==> " + point.getPrice());
        }
        mChartView.setData(new LineData(y, new LineDataSet(x, "Price")));
        mChartView.invalidate();
    }

    @Override
    public void onReceivedDataFailed(String errorMessage) {

    }
}
