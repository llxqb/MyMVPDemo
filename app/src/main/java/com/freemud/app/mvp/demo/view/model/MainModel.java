package com.freemud.app.mvp.demo.view.model;

import com.freemud.app.mvp.demo.entity.request.MainBannerRequest;
import com.freemud.app.mvp.demo.network.networkapi.MainApi;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by helei on 2017/4/28.
 * LoginModel
 */

public class MainModel {
    private final MainApi mMainApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public MainModel(MainApi api, Gson gson, ModelTransform transform) {
        mMainApi = api;
        mGson = gson;
        mTransform = transform;
    }


    public Observable<ResponseData> bannerRequest(MainBannerRequest request) {
        return mMainApi.bannerRequest(mGson.toJson(request)).map(mTransform::transformTypeTwo);//会员接口
    }

}
