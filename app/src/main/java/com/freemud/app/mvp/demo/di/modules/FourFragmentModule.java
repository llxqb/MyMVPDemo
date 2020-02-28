package com.freemud.app.mvp.demo.di.modules;

import com.freemud.app.mvp.demo.di.scopes.PerActivity;
import com.freemud.app.mvp.demo.presenter.PresenterControl.FourFragmentControl;
import com.freemud.app.mvp.demo.presenter.PresenterControl.LoadDataView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class FourFragmentModule {
    private FourFragmentControl.FourFragmentView mFourFragmentView;

    public FourFragmentModule(LoadDataView view) {
        if (view instanceof FourFragmentControl.FourFragmentView) {
            mFourFragmentView = (FourFragmentControl.FourFragmentView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     FourFragmentControl.FourFragmentPresenter mPresenter;
     对应起来
     */
    @Provides
    @PerActivity
    FourFragmentControl.FourFragmentView fourFragmentView() {
        return mFourFragmentView;
    }


}
