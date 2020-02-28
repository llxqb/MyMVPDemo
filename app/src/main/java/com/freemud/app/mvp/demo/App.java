package com.freemud.app.mvp.demo;

import android.app.Application;
import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.freemud.app.mvp.demo.di.components.AppComponent;
import com.freemud.app.mvp.demo.di.components.DaggerAppComponent;
import com.freemud.app.mvp.demo.di.modules.AppModule;
import com.freemud.app.mvp.demo.entity.base.BuProcessor;
import com.google.gson.Gson;

import org.greenrobot.greendao.query.QueryBuilder;

import javax.inject.Inject;

/**
 * Created by li.liu on 18/1/8.
 * Application
 */
public class App extends Application {

    private AppComponent mAppComponent;
    public Context mContext;
    @Inject
    Gson mGson;
    @Inject
    BuProcessor mBuProcessor;
    @Inject
    AMapLocationClient mAMapLocationClient;
    @Inject
    AMapLocationClientOption mAMapLocationClientOption;
    private AMapLocation aMapLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        QueryBuilder.LOG_SQL=true;
        QueryBuilder.LOG_VALUES=true;
        mContext = this.getApplicationContext();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);//必须有
        mBuProcessor.reSetUserData();
        if (aMapLocation == null) {
            mAMapLocationClient.setLocationOption(mAMapLocationClientOption);
            mAMapLocationClient.startLocation();
        }
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public void transformLocation(AMapLocation aMapLocation) {
        this.aMapLocation = aMapLocation;
    }

    public AMapLocation getMapLocation() {
        return aMapLocation;
    }

    public void setMapLocation(AMapLocation location) {
        this.aMapLocation = location;
    }

}
