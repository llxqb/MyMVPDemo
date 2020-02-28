package com.freemud.app.mvp.demo.presenter.PresenterImpl;

import android.content.Context;


import com.freemud.app.mvp.demo.entity.request.LoginRequest;
import com.freemud.app.mvp.demo.entity.response.LoginResponse;
import com.freemud.app.mvp.demo.help.RetryWithDelay;
import com.freemud.app.mvp.demo.presenter.PresenterControl.LoginControl;
import com.freemud.app.mvp.demo.util.LogUtils;
import com.freemud.app.mvp.demo.view.model.LoginModel;
import com.freemud.app.mvp.demo.view.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by helei on 2017/4/27.
 * PresenterLoginImpl
 */

public class PresenterLoginImpl implements LoginControl.PresenterLogin {

    private LoginControl.LoginView mLoginView;
    private final LoginModel mLoginModel;
    private final Context mContext;

    @Inject
    public PresenterLoginImpl(Context context, LoginModel model, LoginControl.LoginView loginView) {
        mContext = context;
        mLoginModel = model;
        mLoginView = loginView;
    }

    /**
     *new RetryWithDelay(3, 3000) 总共重试3次，重试间隔3000毫秒
     * subscribe订阅
     * mLoginView.showErrMessage(throwable)加载出错 ，若加载集合数据用 mLoginView.loadFail(throwable)
     * ::全局作用域符号,修饰方法而不是变量
     */
    @Override
    public void onRequestLogin(LoginRequest request) {
        mLoginView.showLoading("加载中...");
        Disposable disposable = mLoginModel.LoginRequest(request).compose(mLoginView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestDataSuccess, throwable -> mLoginView.showErrMessage(throwable),
                        () -> mLoginView.dismissLoading());
        mLoginView.addSubscription(disposable);
    }

    private void requestDataSuccess(ResponseData responseData) {
        if (responseData.resultCode == 200) {
            responseData.parseData(LoginResponse.class);
            LoginResponse response = (LoginResponse) responseData.parsedData;
            mLoginView.loginSuccess(response);
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


}
