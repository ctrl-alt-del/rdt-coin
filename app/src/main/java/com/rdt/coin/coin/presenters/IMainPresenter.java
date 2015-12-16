package com.rdt.coin.coin.presenters;


import com.rdt.coin.coin.views.IMainView;

public interface IMainPresenter extends IBasePresenter<IMainView> {
    void getPoints(String points);
    void getPoints(String points, String timestamp1, String timestamp2);
}
