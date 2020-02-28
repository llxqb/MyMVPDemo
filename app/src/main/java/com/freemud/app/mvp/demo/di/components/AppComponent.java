package com.freemud.app.mvp.demo.di.components;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.freemud.app.mvp.demo.di.ComponetGraph;
import com.freemud.app.mvp.demo.di.modules.AppModule;
import com.freemud.app.mvp.demo.entity.base.BuProcessor;
import com.freemud.app.mvp.demo.gen.DaoSession;
import com.freemud.app.mvp.demo.help.GlideHelper.ImageLoaderHelper;
import com.freemud.app.mvp.demo.view.model.ModelTransform;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 全局类实例都统一交给ApplicationComponent管理
 * Created by li.liuon 18/1/11.
 * Singleton  表示单例
 * dependencies   Component中的依赖
 * SubComponent   Component中的包含
 * Scope      在PerActivity中    都有必要用自定义的Scope注解标注这些Component
 * Provides最终解决第三方类库依赖注入问题
 * 第三方类到这里注册
 * AppComponent中方法必须到AppModule中实现
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent extends ComponetGraph {
    Context getContext();

    Gson gson();

    ModelTransform modeTransform();

    ImageLoaderHelper imageLoaderHelper();

    DaoSession daoSession();

    BuProcessor buProcessor();

    AMapLocationClient aMapLocationClient();//MainActivity  有用到  @Inject AMapLocationClient mAMapLocationClient;

    AMapLocationClientOption aMapLocationClientOption();
}
