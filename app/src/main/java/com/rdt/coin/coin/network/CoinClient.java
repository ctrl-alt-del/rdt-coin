package com.rdt.coin.coin.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class CoinClient {

    private static CoinService sCoinService;

    public static CoinService getCoinService() {
        if (sCoinService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://coin.melikesit.com/data/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sCoinService = retrofit.create(CoinService.class);
        }
        return sCoinService;
    }
}
