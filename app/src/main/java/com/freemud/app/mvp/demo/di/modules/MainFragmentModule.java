package com.freemud.app.mvp.demo.di.modules;


import com.freemud.app.mvp.demo.di.scopes.PerActivity;
import com.freemud.app.mvp.demo.presenter.PresenterControl.LoadDataView;
import com.freemud.app.mvp.demo.presenter.PresenterControl.OneFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class MainFragmentModule {
    private OneFragmentControl.OneFragmentView mOneFragmentView;

    public MainFragmentModule(LoadDataView view) {
        if (view instanceof OneFragmentControl.OneFragmentView) {
            mOneFragmentView = (OneFragmentControl.OneFragmentView) view;
        }
    }

    @Provides
    @PerActivity
    OneFragmentControl.OneFragmentView oneFragmentView() {
        return mOneFragmentView;
    }


}
