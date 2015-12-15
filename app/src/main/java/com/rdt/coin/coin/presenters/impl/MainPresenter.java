package com.rdt.coin.coin.presenters.impl;


import com.rdt.coin.coin.CoinClient;
import com.rdt.coin.coin.Point;
import com.rdt.coin.coin.presenters.BasePresenter;
import com.rdt.coin.coin.presenters.IMainPresenter;
import com.rdt.coin.coin.views.IMainView;

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
        Call<List<Point>> call = CoinClient.getCoinService().getPoints(points);
        handleAsyncCall(call);
    }

    @Override
    public void getPointsWithTimestamps(String points, String timestamp1, String timestamp2) {
        Call<List<Point>> call = CoinClient.getCoinService().getPointsWithTimestamps(points, timestamp1, timestamp2);
        handleAsyncCall(call);
    }

    private void handleAsyncCall(Call<List<Point>> call) {
        call.enqueue(new Callback<List<Point>>() {
            @Override
            public void onResponse(Response<List<Point>> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
