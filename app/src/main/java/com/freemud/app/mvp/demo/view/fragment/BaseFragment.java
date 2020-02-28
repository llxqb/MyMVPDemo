package com.freemud.app.mvp.demo.view.fragment;


import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import com.freemud.app.mvp.demo.entity.base.BuProcessor;
import com.freemud.app.mvp.demo.help.DialogFactory;
import com.freemud.app.mvp.demo.help.GlideHelper.ImageLoaderHelper;
import com.freemud.app.mvp.demo.mymvpdemo3.R;
import com.freemud.app.mvp.demo.util.LogUtils;
import com.freemud.app.mvp.demo.util.ToastUtil;
import com.freemud.app.mvp.demo.view.activity.LoginActivity;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * BaseFragment
 * 基类Fragement
 */
public abstract class BaseFragment extends Fragment {
    @Inject
    protected ImageLoaderHelper mImageLoaderHelper;
    @Inject
    protected BuProcessor mBuProcessor;

    private CompositeDisposable mDisposable;
    private Dialog mProgressDialog;
    final IntentFilter mFilter = new IntentFilter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFilter();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReceiver, mFilter);
    }

    public abstract void initView();

    public abstract void initData();

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onReceivePro(context, intent);
        }
    };

    void onReceivePro(Context context, Intent intent) {
    }

    void addFilter() {
    }

    public void showErrMessage(Throwable e) {
        LogUtils.i("e=" + e.toString());
        dismissLoading();
        String mErrMessage;
        if (e instanceof HttpException || e instanceof ConnectException) {
            mErrMessage = getString(R.string.text_check_internet);
        } else {
            mErrMessage = getString(R.string.text_wait_try);
        }
        showToast(mErrMessage);
    }

    public void showToast(String message) {
        ToastUtil.showToast(getContext(), message);
    }

    public void showLoading(String msg) {
        dismissLoading();
        mProgressDialog = DialogFactory.showLoadingDialog(getActivity(), msg);
        mProgressDialog.show();
    }

    public void dismissLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = null;
    }

    public Context getContext() {
        return getActivity();
    }

    public void judgeToken(Integer code) {
        if (code == 100401 || code == 100107) {
            showToast("登入过期,请重新登入");
            clearSwitchToLogin();
        }
    }

    public void clearSwitchToLogin() {
        mBuProcessor.clearLoginUser();
        LoginActivity.start(getContext());
    }

    /**
     * @param subscription RxJava取消订阅
     */
    public void addSubscription(Disposable subscription) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(subscription);
    }

    public <T> ObservableTransformer<T, T> applySchedulers() {
        //noinspection unchecked
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }

    private final ObservableTransformer schedulersTransformer = (observable) -> (
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()));

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.clear();
        }
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mReceiver);
    }
}
