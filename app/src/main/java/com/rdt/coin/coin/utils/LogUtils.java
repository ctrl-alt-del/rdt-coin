package com.rdt.coin.coin.utils;

import android.util.Log;

public class LogUtils {

    public static final String LOG_TAG = "RDT_COIN";

    public static void debug(String message) {
        if (message != null) {
            Log.d(LOG_TAG, message);
        }
    }
}
