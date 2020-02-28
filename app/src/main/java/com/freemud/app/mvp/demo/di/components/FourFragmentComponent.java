package com.freemud.app.mvp.demo.di.components;


import android.support.v7.app.AppCompatActivity;

import com.freemud.app.mvp.demo.di.modules.FourFragmentModule;
import com.freemud.app.mvp.demo.di.modules.MainFragmentModule;
import com.freemud.app.mvp.demo.di.modules.MainModule;
import com.freemud.app.mvp.demo.di.scopes.PerActivity;
import com.freemud.app.mvp.demo.view.fragment.FourFragment;
import com.freemud.app.mvp.demo.view.fragment.OneFragment;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainModule.class, FourFragmentModule.class})
public interface FourFragmentComponent {
    AppCompatActivity activity();
    void inject(FourFragment fragment);
}
