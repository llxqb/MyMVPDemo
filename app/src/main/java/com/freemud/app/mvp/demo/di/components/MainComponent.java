package com.freemud.app.mvp.demo.di.components;


import android.support.v7.app.AppCompatActivity;

import com.freemud.app.mvp.demo.di.modules.ActivityModule;
import com.freemud.app.mvp.demo.di.modules.MainModule;
import com.freemud.app.mvp.demo.di.scopes.PerActivity;
import com.freemud.app.mvp.demo.presenter.PresenterControl.LoginControl;
import com.freemud.app.mvp.demo.presenter.PresenterControl.MainControl;
import com.freemud.app.mvp.demo.presenter.PresenterControl.OneFragmentControl;
import com.freemud.app.mvp.demo.view.activity.MainActivity;
import com.freemud.app.mvp.demo.view.fragment.OneFragment;

import dagger.Component;

/**
 * MainComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = {MainModule.class, ActivityModule.class})
public interface MainComponent extends ActivityComponent{
    //对MainActivity进行依赖注入
    void inject(MainActivity mainActivity);
    AppCompatActivity activity();
    MainControl.MainView view();

//    OneFragmentComponent oneFragmentComponent();

}
