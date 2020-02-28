package com.freemud.app.mvp.demo.view.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.freemud.app.mvp.demo.App;
import com.freemud.app.mvp.demo.di.components.AppComponent;
import com.freemud.app.mvp.demo.entity.base.BuProcessor;
import com.freemud.app.mvp.demo.help.DialogFactory;
import com.freemud.app.mvp.demo.help.GlideHelper.ImageLoaderHelper;
import com.freemud.app.mvp.demo.mymvpdemo3.R;
import com.freemud.app.mvp.demo.util.LogUtils;
import com.freemud.app.mvp.demo.util.ToastUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by li.liu on 17/12/20.
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    protected ImageLoaderHelper mImageLoaderHelper;
    @Inject
    BuProcessor mBuProcessor;

    private CompositeDisposable mDisposable;
    private Dialog mProgressDialog;
    protected final IntentFilter mFilter = new IntentFilter();

    public AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFilter();
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, mFilter);
    }


    public abstract void initView();

    public abstract void initData();


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

    void supportActionBar(Toolbar toolbar, boolean isShowIcon) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (isShowIcon) {
            toolbar.setNavigationIcon(R.drawable.vector_arrow_left);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
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

    public void showLoading() {
    }

    public void showLoading(String msg) {
        dismissLoading();
        mProgressDialog = DialogFactory.showLoadingDialog(this, msg);
        mProgressDialog.show();
    }

    public void dismissLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = null;
    }

    public Context getContext() {
        return this;
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
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.clear();
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }
}
