package com.rdt.coin.coin;

import retrofit.Retrofit;

public class CoinClient {

    private static CoinService sCoinService;

    public CoinService getCoinService() {
        if (sCoinService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://coin.melikesit.com")
                    .build();
            sCoinService = retrofit.create(CoinService.class);
        }
        return sCoinService;
    }
}
