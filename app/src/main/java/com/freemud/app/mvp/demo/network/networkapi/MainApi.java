package com.freemud.app.mvp.demo.network.networkapi;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by helei on 2017/4/27.
 * MainApi
 */

public interface MainApi {

    @POST("service/restful/basis")
    Observable<String> bannerRequest(@Body String request);

}
