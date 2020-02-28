package com.freemud.app.mvp.demo.view.model;

import com.freemud.app.mvp.demo.network.networkapi.FirstApi;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by helei on 2017/4/28.
 * LoginModel
 */

public class FirstModel {
    private final FirstApi mFirstApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public FirstModel(FirstApi api, Gson gson, ModelTransform transform) {
        mFirstApi = api;
        mGson = gson;
        mTransform = transform;
    }


    public Observable<ResponseData> lookNumRequest(String userId, String shopId) {
        return mFirstApi.lookNum(userId, shopId).map(mTransform::transformTypeTwo);
    }

}
