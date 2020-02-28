package com.freemud.app.mvp.demo.di.components;

import android.app.Activity;


import com.freemud.app.mvp.demo.di.modules.ActivityModule;
import com.freemud.app.mvp.demo.di.scopes.PerActivity;

import dagger.Component;

/**
 *
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();
}
