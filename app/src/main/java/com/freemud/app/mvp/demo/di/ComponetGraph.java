package com.freemud.app.mvp.demo.di;


import com.freemud.app.mvp.demo.App;
import com.freemud.app.mvp.demo.view.activity.BaseActivity;
import com.freemud.app.mvp.demo.view.fragment.BaseFragment;

/**
 * Created by wxl on 16/3/30.
 *
 */
public interface ComponetGraph {

    void inject(App application);

    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseFragment);

//    void inject(CustomerService service);

}
