package com.rdt.coin.coin.network;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface CoinService {
    @GET("cnbs.btc.usd")
    Call<List<String[]>> getPoints(@Query("n") String points);

    @GET("cnbs.btc.usd")
    Call<List<String[]>> getPointsWithinTimeRange(@Query("n") String points,
                                               @Query("t1") String t1, @Query("t2") String t2);
}
