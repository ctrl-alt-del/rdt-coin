package com.rdt.coin.coin.presenters.impl;


import com.rdt.coin.coin.CoinClient;
import com.rdt.coin.coin.LogUtils;
import com.rdt.coin.coin.Point;
import com.rdt.coin.coin.presenters.BasePresenter;
import com.rdt.coin.coin.presenters.IMainPresenter;
import com.rdt.coin.coin.views.IMainView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {

    public MainPresenter(IMainView view) {
        super(view);
    }

    @Override
    public void getPoints(String points) {
        Call<List<String[]>> call = CoinClient.getCoinService().getPoints(points);
        handleAsyncCall(call);
    }

    @Override
    public void getPointsWithTimestamps(String points, String timestamp1, String timestamp2) {
        Call<List<String[]>> call = CoinClient.getCoinService().getPointsWithinTimeRange(points, timestamp1, timestamp2);
        handleAsyncCall(call);
    }

    private void handleAsyncCall(Call<List<String[]>> call) {
        call.enqueue(new Callback<List<String[]>>() {
            @Override
            public void onResponse(Response<List<String[]>> response, Retrofit retrofit) {

                // This parse could be avoid if the API side return data in an object-oriented form
                List<String[]> rawData = response.body();
                List<Point> points = new ArrayList<>(rawData.size());
                for (String[] point : rawData) {
                    points.add(new Point(point));
                }
                // ----------

                getView().onReceivedDataSucceed(points);
            }

            @Override
            public void onFailure(Throwable t) {
                LogUtils.debug(t.getMessage());
            }
        });
    }
}
