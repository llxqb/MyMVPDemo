package com.freemud.app.mvp.demo.di.components;


import android.support.v7.app.AppCompatActivity;

import com.freemud.app.mvp.demo.di.modules.MainFragmentModule;
import com.freemud.app.mvp.demo.di.modules.MainModule;
import com.freemud.app.mvp.demo.di.scopes.PerActivity;
import com.freemud.app.mvp.demo.view.fragment.OneFragment;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainModule.class, MainFragmentModule.class})
public interface MainFragmentComponent{
    AppCompatActivity activity();
    void inject(OneFragment fragment);
//    void inject(PayCompleteOrderFragment fragment);
//    void inject(OrderCompleteFragment fragment);
//    void inject(AllOrderFragment fragment);
}
