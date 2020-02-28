package com.freemud.app.mvp.demo.presenter.PresenterImpl;

import android.content.Context;

import com.freemud.app.mvp.demo.entity.request.LoginRequest;
import com.freemud.app.mvp.demo.entity.response.CartGoodsJson;
import com.freemud.app.mvp.demo.entity.response.LoginResponse;
import com.freemud.app.mvp.demo.help.RetryWithDelay;
import com.freemud.app.mvp.demo.presenter.PresenterControl.FirstActivityControl;
import com.freemud.app.mvp.demo.presenter.PresenterControl.LoginControl;
import com.freemud.app.mvp.demo.util.LogUtils;
import com.freemud.app.mvp.demo.view.model.FirstModel;
import com.freemud.app.mvp.demo.view.model.LoginModel;
import com.freemud.app.mvp.demo.view.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by helei on 2017/4/27.
 * PresenterLoginImpl
 */

public class PresenterFirstActivityImpl implements FirstActivityControl.PresenterFirstActivity {

    private FirstActivityControl.FirstActivityView mLoginView;
    private final FirstModel mLoginModel;
    private final Context mContext;

    @Inject
    public PresenterFirstActivityImpl(Context context, FirstModel model, FirstActivityControl.FirstActivityView loginView) {
        mContext = context;
        mLoginModel = model;
        mLoginView = loginView;
    }


    private void requestDataSuccess(ResponseData responseData) {
        if (responseData.resultCode == 100) {
//            mLoginView.judgeToken(100401);
            CartGoodsJson response = responseData.parseData(CartGoodsJson.class);
            mLoginView.lookSuccess(response.getCount());
        } else {
            mLoginView.showToast(responseData.errorDesc);
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }


    @Override
    public void onRequestLookNum(String userId, String shopId) {
        mLoginView.showLoading("加载中...");
        Disposable disposable = mLoginModel.lookNumRequest(userId,shopId).retryWhen(new RetryWithDelay(3, 3000)).compose(mLoginView.applySchedulers())
                .subscribe(this::requestDataSuccess, throwable -> mLoginView.showErrMessage(throwable),
                        () -> mLoginView.dismissLoading());
        mLoginView.addSubscription(disposable);
    }
}
