package com.freemud.app.mvp.demo.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.freemud.app.mvp.demo.di.components.DaggerLoginComponent;
import com.freemud.app.mvp.demo.di.components.LoginComponent;
import com.freemud.app.mvp.demo.di.modules.ActivityModule;
import com.freemud.app.mvp.demo.di.modules.LoginModule;
import com.freemud.app.mvp.demo.entity.IntentConstant;
import com.freemud.app.mvp.demo.entity.response.LoginResponse;
import com.freemud.app.mvp.demo.mymvpdemo3.R;
import com.freemud.app.mvp.demo.presenter.PresenterControl.LoginControl;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginControl.LoginView {

    @BindView(R.id.login_user)
    EditText loginUser;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    private String user;
    private String pwd;
    @Inject
    LoginControl.PresenterLogin mPresenterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initializeInjector();
        initView();
        initData();
    }

    @Override
    public void initView() {
        if (mBuProcessor.isValidLogin()) {
            MainActivity.start(this);
            finish();
        } else {
            String userInitValue = "13262253731";
            String passwordInitValue = "1234";
            loginUser.setText(userInitValue);
            loginPwd.setText(passwordInitValue);
        }
    }

    @Override
    public void initData() {
    }

    @Override
    void onReceivePro(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(IntentConstant.LOGON_TO_MAIN)) {
            showToast("我是广播");
        }
        super.onReceivePro(context, intent);
    }

    @Override
    void addFilter() {
        super.addFilter();
        mFilter.addAction(IntentConstant.LOGON_TO_MAIN);
    }

    public void loginBtn(View view) {
        if (!validate()) return;
        getAppPermission();
    }

    private boolean validate() {
        user = loginUser.getText().toString();
        pwd = loginPwd.getText().toString();
        if (TextUtils.isEmpty(user)) {
            loginUser.setError("此为必填项");
            showToast("用户名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            loginPwd.setError("此为必填项");
            showToast("密码不能为空");
            return false;
        }
        return true;
    }

    private void getAppPermission() {
        RxPermissions mRxPermissions = new RxPermissions(this);
        mRxPermissions.request(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CAMERA).subscribe(permission -> {
            if (permission) {
                //当所有权限都允许之后，返回true
//                LoginRequest loginRequest = new LoginRequest();
//                loginRequest.mobile = user;
//                loginRequest.password = pwd;
//                mPresenterLogin.onRequestLogin(loginRequest);
                startActivityForResult(MainActivity.getMainIntent(this), IntentConstant.TEST_FORRESULT);
                mBuProcessor.setLoginUser(user, "123456");
                finish();
            } else {
                //只要有一个权限禁止，返回false，
                //下一次申请只申请没通过申请的权限
                showToast("请允许所有权限");
            }
        });
    }

    @Override
    public void loginSuccess(LoginResponse response) {
        mBuProcessor.setLoginUser(user, response.token);
        startActivityForResult(MainActivity.getMainIntent(this), IntentConstant.TEST_FORRESULT);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstant.TEST_FORRESULT && resultCode == RESULT_OK) {
            showToast("我是登陆");
        }
    }


    private void initializeInjector() {
        LoginComponent mLoginComponent = DaggerLoginComponent.builder().appComponent(getAppComponent())
                .loginModule(new LoginModule(LoginActivity.this, this))
                .activityModule(new ActivityModule(this)).build();
        mLoginComponent.inject(this);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
