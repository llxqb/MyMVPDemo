package com.freemud.app.mvp.demo.view.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by helei on 2017/4/28.
 * ModelTransform
 */

public class ModelTransform {

    public ResponseData transformCommon(String response) {
        ResponseData responseData;
        try {
            JSONObject jsonObject = new JSONObject(response);
            responseData = new ResponseData(jsonObject);
        } catch (JSONException e) {
            responseData = new ResponseData();
        }
        return responseData;
    }

    //会员接口
    public ResponseData transformTypeTwo(String response) {
        ResponseData responseData;
        try {
            JSONObject jsonObject = new JSONObject(response);
            responseData = new ResponseData(jsonObject, 1);
        } catch (JSONException e) {
            responseData = new ResponseData();
        }
        return responseData;
    }
}
