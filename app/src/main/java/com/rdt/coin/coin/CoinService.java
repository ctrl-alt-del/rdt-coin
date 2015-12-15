package com.rdt.coin.coin;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface CoinService {
    @GET("/data/cnbs.btc.usd?n={points}")
    Call<List<Point>> getPoints(@Path("points") String points);

    @GET("/data/cnbs.btc.usd?n={points}&t1={timestamp1}&t2={timestamp2}")
    Call<List<Point>> getPointsWithTimestamps(@Path("points") String points,
                                              @Path("timestamp1") String timestamp1,
                                              @Path("timestamp2") String timestamp2);
}
