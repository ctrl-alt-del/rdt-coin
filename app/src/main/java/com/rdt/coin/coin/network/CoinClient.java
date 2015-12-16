package com.rdt.coin.coin.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class CoinClient {

    private static CoinService sCoinService;
    private static final String SERVER_DOMAIN = "http://coin.melikesit.com/data/";

    public static CoinService getCoinService() {
        if (sCoinService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sCoinService = retrofit.create(CoinService.class);
        }
        return sCoinService;
    }
}
