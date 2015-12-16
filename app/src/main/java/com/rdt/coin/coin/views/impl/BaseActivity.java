package com.rdt.coin.coin.views.impl;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.rdt.coin.coin.views.IBaseView;

public class BaseActivity extends AppCompatActivity implements IBaseView {
    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    public Activity getActivity() {
        return this;
    }
}
