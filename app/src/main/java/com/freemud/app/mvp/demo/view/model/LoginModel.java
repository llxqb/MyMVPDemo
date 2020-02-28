package com.freemud.app.mvp.demo.view.model;

import com.freemud.app.mvp.demo.entity.request.LoginRequest;
import com.freemud.app.mvp.demo.network.networkapi.LoginApi;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by helei on 2017/4/28.
 * LoginModel
 */

public class LoginModel {
    private final LoginApi mLoginApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public LoginModel(LoginApi api, Gson gson, ModelTransform transform) {
        mLoginApi = api;
        mGson = gson;
        mTransform = transform;
    }


    public Observable<ResponseData> LoginRequest(LoginRequest request) {
        return mLoginApi.loginRequest(mGson.toJson(request)).map(mTransform::transformCommon);
    }

}
