package com.freemud.app.mvp.demo.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.freemud.app.mvp.demo.di.scopes.PerActivity;
import com.freemud.app.mvp.demo.entity.Constant;
import com.freemud.app.mvp.demo.mymvpdemo3.BuildConfig;
import com.freemud.app.mvp.demo.network.RetrofitUtil;
import com.freemud.app.mvp.demo.network.networkapi.LoginApi;
import com.freemud.app.mvp.demo.presenter.PresenterControl.LoginControl;
import com.freemud.app.mvp.demo.presenter.PresenterImpl.PresenterLoginImpl;
import com.freemud.app.mvp.demo.view.model.LoginModel;
import com.freemud.app.mvp.demo.view.model.ModelTransform;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@Module
public class LoginModule {
    private final AppCompatActivity activity;
    private final LoginControl.LoginView view;

    public LoginModule(AppCompatActivity activity, LoginControl.LoginView view) {
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
    LoginControl.LoginView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    LoginModel provideLoginModel(Gson gson, ModelTransform modelTransform ) {
        return new LoginModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(Constant.DISPATCH_SERVICE)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(LoginApi.class), gson, modelTransform);
    }

    @Provides
    @PerActivity
    LoginControl.PresenterLogin providePresenterLogin(PresenterLoginImpl presenterLogin) {
        return presenterLogin;
    }
}
