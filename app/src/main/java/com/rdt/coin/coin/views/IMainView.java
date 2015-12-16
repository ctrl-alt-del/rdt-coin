package com.rdt.coin.coin.views;


import com.rdt.coin.coin.models.Point;

import java.util.List;

public interface IMainView extends IBaseView {
    void onReceivedDataSucceed(List<Point> points);
    void onReceivedDataFailed(String errorMessage);
}
