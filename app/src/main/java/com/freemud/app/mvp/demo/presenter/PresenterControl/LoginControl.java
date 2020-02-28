package com.freemud.app.mvp.demo.presenter.PresenterControl;


import com.freemud.app.mvp.demo.entity.request.LoginRequest;
import com.freemud.app.mvp.demo.entity.response.LoginResponse;

/**
 * Created by li.liu on 2017/12/13.
 */

public class LoginControl {
    public interface LoginView extends LoadDataView {
        void loginSuccess(LoginResponse response);

    }

    public interface PresenterLogin extends Presenter<LoginView> {
        void onRequestLogin(LoginRequest loginRequest);
    }


}
