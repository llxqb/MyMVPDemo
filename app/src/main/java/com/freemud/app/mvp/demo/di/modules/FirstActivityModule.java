package com.freemud.app.mvp.demo.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.freemud.app.mvp.demo.di.scopes.PerActivity;
import com.freemud.app.mvp.demo.entity.Constant;
import com.freemud.app.mvp.demo.mymvpdemo3.BuildConfig;
import com.freemud.app.mvp.demo.network.RetrofitUtil;
import com.freemud.app.mvp.demo.network.networkapi.FirstApi;
import com.freemud.app.mvp.demo.network.networkapi.LoginApi;
import com.freemud.app.mvp.demo.presenter.PresenterControl.FirstActivityControl;
import com.freemud.app.mvp.demo.presenter.PresenterControl.LoginControl;
import com.freemud.app.mvp.demo.presenter.PresenterImpl.PresenterFirstActivityImpl;
import com.freemud.app.mvp.demo.presenter.PresenterImpl.PresenterLoginImpl;
import com.freemud.app.mvp.demo.view.model.FirstModel;
import com.freemud.app.mvp.demo.view.model.LoginModel;
import com.freemud.app.mvp.demo.view.model.ModelTransform;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@Module
public class FirstActivityModule {
    private final AppCompatActivity activity;
    private final FirstActivityControl.FirstActivityView view;

    public FirstActivityModule(AppCompatActivity activity, FirstActivityControl.FirstActivityView view) {
        this.activity = activity;
        this.view = view;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    FirstActivityControl.FirstActivityView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    FirstModel provideFirstModel(Gson gson, ModelTransform modelTransform ) {
        return new FirstModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(Constant.DISPATCH_SERVICE3)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(FirstApi.class), gson, modelTransform);
    }

    @Provides
    @PerActivity
    FirstActivityControl.PresenterFirstActivity providePresenterLogin(PresenterFirstActivityImpl presenterLogin) {
        return presenterLogin;
    }
}
