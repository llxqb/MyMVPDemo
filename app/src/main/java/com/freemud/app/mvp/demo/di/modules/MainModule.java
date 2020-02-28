package com.freemud.app.mvp.demo.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.freemud.app.mvp.demo.di.scopes.PerActivity;
import com.freemud.app.mvp.demo.entity.Constant;
import com.freemud.app.mvp.demo.mymvpdemo3.BuildConfig;
import com.freemud.app.mvp.demo.network.RetrofitUtil;
import com.freemud.app.mvp.demo.network.networkapi.MainApi;
import com.freemud.app.mvp.demo.presenter.PresenterControl.FourFragmentControl;
import com.freemud.app.mvp.demo.presenter.PresenterControl.MainControl;
import com.freemud.app.mvp.demo.presenter.PresenterControl.OneFragmentControl;
import com.freemud.app.mvp.demo.presenter.PresenterImpl.FourFragmentImpl;
import com.freemud.app.mvp.demo.presenter.PresenterImpl.OneFragmentImpl;
import com.freemud.app.mvp.demo.presenter.PresenterImpl.PresenterMainImpl;
import com.freemud.app.mvp.demo.view.model.MainModel;
import com.freemud.app.mvp.demo.view.model.ModelTransform;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class MainModule {
    private final AppCompatActivity activity;
    private  MainControl.MainView view;

    public MainModule(AppCompatActivity activity, MainControl.MainView view) {
        this.activity = activity;
        this.view = view;
    }

    public MainModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    MainControl.MainView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    MainControl.PresenterMain providePresenterMain(PresenterMainImpl presenterMain) {
        return presenterMain;
    }

    @Provides
    @PerActivity
    MainModel provideMainModel(Gson gson, ModelTransform modelTransform) {
        return new MainModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(Constant.DISPATCH_SERVICE2)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(MainApi.class), gson, modelTransform);
    }


    //add
    @Provides
    @PerActivity
    OneFragmentControl.OneFragmentPresenter providePresenterOneFragment(OneFragmentImpl oneFragment) {
        return oneFragment;
    }

    @Provides
    @PerActivity
    FourFragmentControl.FourFragmentPresenter providePresenterFourFragment(FourFragmentImpl fourFragment) {
        return fourFragment;
    }

}
