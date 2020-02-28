package com.freemud.app.mvp.demo.entity.base;

import android.content.Context;
import android.text.TextUtils;

import com.freemud.app.mvp.demo.entity.Constant;
import com.freemud.app.mvp.demo.util.SharePreferenceUtil;

import javax.inject.Inject;

/**
 * BuProcessor
 */
public class BuProcessor {

    private LoginUser mLoginUser = new LoginUser();
    private final SharePreferenceUtil mSharePreferenceUtil;

    @Inject
    public BuProcessor(Context context, SharePreferenceUtil mSharePreferenceUtil) {
        this.mSharePreferenceUtil = mSharePreferenceUtil;
    }

    public boolean isValidLogin() {
        return mLoginUser != null && !TextUtils.isEmpty(mLoginUser.getUserToken()) && !TextUtils.isEmpty(mLoginUser.getUserPhone());

    }

    public String getUserToken() {
        if (!isValidLogin()) {
            return "";
        }
        return mLoginUser.getUserToken();
    }

    public String getUserPhone() {
        if (!isValidLogin()) {
            return "";
        }
        return mLoginUser.getUserPhone();
    }

    public LoginUser getLoginUser() {
        return mLoginUser;
    }

    public void setLoginUser(String userPhone, String userToken) {
        mLoginUser.setUserPhone(userPhone);
        mLoginUser.setUserToken(userToken);
        mSharePreferenceUtil.saveObjData("loginUser",mLoginUser);
    }

    public LoginUser reSetUserData() {
        // 恢复用户相关
        Object o1 = mSharePreferenceUtil.readObjData(Constant.LOGIN_USER);
        if (o1 != null && o1 instanceof LoginUser) {
            mLoginUser = (LoginUser) o1;
            return mLoginUser;
        }
        return null;
    }

    //退出登录清除数据
    public void clearLoginUser() {
        // 清空用户
        if (mLoginUser != null) {
            mLoginUser.clear();
        }
        mSharePreferenceUtil.saveObjData(Constant.LOGIN_USER, "");
    }
}
