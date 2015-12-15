package com.rdt.coin.coin.presenters;


import com.rdt.coin.coin.views.IBaseView;

import java.lang.ref.SoftReference;

public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {
    private SoftReference<T> mSoftReferenceView;

    public BasePresenter(T view) {
        mSoftReferenceView = new SoftReference<>(view);
    }

    @Override
    public T getView() {
        return mSoftReferenceView.get();
    }
}