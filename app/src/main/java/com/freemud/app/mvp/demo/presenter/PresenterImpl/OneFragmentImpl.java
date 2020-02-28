package com.freemud.app.mvp.demo.presenter.PresenterImpl;

import android.content.Context;

import com.freemud.app.mvp.demo.entity.request.MainBannerRequest;
import com.freemud.app.mvp.demo.entity.response.MainBannerResponse;
import com.freemud.app.mvp.demo.presenter.PresenterControl.OneFragmentControl;
import com.freemud.app.mvp.demo.view.model.MainModel;
import com.freemud.app.mvp.demo.view.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by li.liu on 2017/4/27.
 * PresenterLoginImpl
 */

public class OneFragmentImpl implements OneFragmentControl.OneFragmentPresenter{

    private OneFragmentControl.OneFragmentView mMainView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public OneFragmentImpl(Context context, MainModel model, OneFragmentControl.OneFragmentView mainView) {
        mContext = context;
        mMainModel = model;
        mMainView = mainView;
    }

    @Override
    public void onRequestBanner(MainBannerRequest request) {
        mMainView.showLoading("加载中...");
        Disposable disposable = mMainModel.bannerRequest(request).compose(mMainView.applySchedulers())
                .subscribe(this::requestBannerSuccess, throwable -> mMainView.showErrMessage(throwable),
                        () -> mMainView.dismissLoading());
        mMainView.addSubscription(disposable);
    }

    private void requestBannerSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(MainBannerResponse.class);
            MainBannerResponse response = (MainBannerResponse) responseData.parsedData;
            mMainView.bannerSuccess(response);
        } else {
            mMainView.showToast(responseData.errorDesc);
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        mMainView = null;
    }
}
