package com.rdt.coin.coin.presenters;


import com.rdt.coin.coin.views.IBaseView;

public interface IBasePresenter<T extends IBaseView> {
    T getView();
}
