package com.freemud.app.mvp.demo.presenter.PresenterImpl;

import android.content.Context;

import com.freemud.app.mvp.demo.presenter.PresenterControl.FourFragmentControl;
import com.freemud.app.mvp.demo.view.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2017/4/27.
 * PresenterLoginImpl
 */

public class FourFragmentImpl implements FourFragmentControl.FourFragmentPresenter{

    private FourFragmentControl.FourFragmentView mMainView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public FourFragmentImpl(Context context, MainModel model, FourFragmentControl.FourFragmentView mainView) {
        mContext = context;
        mMainModel = model;
        mMainView = mainView;
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
}
