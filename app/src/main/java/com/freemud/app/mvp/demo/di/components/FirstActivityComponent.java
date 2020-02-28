package com.freemud.app.mvp.demo.di.components;


import android.support.v7.app.AppCompatActivity;

import com.freemud.app.mvp.demo.di.modules.ActivityModule;
import com.freemud.app.mvp.demo.di.modules.FirstActivityModule;
import com.freemud.app.mvp.demo.di.modules.LoginModule;
import com.freemud.app.mvp.demo.di.scopes.PerActivity;
import com.freemud.app.mvp.demo.presenter.PresenterControl.FirstActivityControl;
import com.freemud.app.mvp.demo.presenter.PresenterControl.LoginControl;
import com.freemud.app.mvp.demo.view.activity.FirstActivity;
import com.freemud.app.mvp.demo.view.activity.LoginActivity;

import dagger.Component;

/**
 * LoginComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by li.liu on 18/1/19.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = {FirstActivityModule.class, ActivityModule.class})
public interface FirstActivityComponent extends ActivityComponent{
    //对LoginActivity进行依赖注入
    void inject(FirstActivity firstActivity);

    AppCompatActivity activity();
    FirstActivityControl.FirstActivityView view();

}
