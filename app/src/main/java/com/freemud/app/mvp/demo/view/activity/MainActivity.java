package com.freemud.app.mvp.demo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.freemud.app.mvp.demo.App;
import com.freemud.app.mvp.demo.di.components.DaggerMainComponent;
import com.freemud.app.mvp.demo.di.components.MainComponent;
import com.freemud.app.mvp.demo.di.modules.ActivityModule;
import com.freemud.app.mvp.demo.di.modules.MainModule;
import com.freemud.app.mvp.demo.entity.IntentConstant;
import com.freemud.app.mvp.demo.entity.response.MainBannerResponse;
import com.freemud.app.mvp.demo.help.BottomNavigationViewHelper;
import com.freemud.app.mvp.demo.mymvpdemo3.R;
import com.freemud.app.mvp.demo.presenter.PresenterControl.MainControl;
import com.freemud.app.mvp.demo.util.AndroidBug54971Workaround;
import com.freemud.app.mvp.demo.util.LogUtils;
import com.freemud.app.mvp.demo.util.SystemStatusManager;
import com.freemud.app.mvp.demo.view.adapter.MyFragmentAdapter;
import com.freemud.app.mvp.demo.view.fragment.FourFragment;
import com.freemud.app.mvp.demo.view.fragment.OneFragment;
import com.freemud.app.mvp.demo.view.fragment.ThreeFragment;
import com.freemud.app.mvp.demo.view.fragment.TwoFragment;
import com.freemud.app.mvp.demo.view.views.MyNoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainControl.MainView, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.main_viewpager)
    MyNoScrollViewPager mMyNoScrollViewPager;
    @BindView(R.id.view_bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    public static final int SWITCH_FIRST_PAGE = 0;
    public static final int SWITCH_SECOND_PAGE = 1;
    public static final int SWITCH_THIRD_PAGE = 2;
    public static final int SWITCH_FORTH_PAGE = 3;

    @Inject
    AMapLocationClient mAMapLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SystemStatusManager.setbannerStatus(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidBug54971Workaround.assistActivity(findViewById(android.R.id.content));//解决华为手机虚拟键冲突
        ButterKnife.bind(this);
        initInjectData();
        initView();
        initData();
    }

    @Override
    public void initView() {
        //默认停用滑动效果
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        List<Fragment> fragments = new ArrayList<>();
        OneFragment oneFragment = OneFragment.newInstance();
        TwoFragment twoFragment = TwoFragment.newInstance();
        ThreeFragment threeFragment = ThreeFragment.newInstance();
        FourFragment fourFragment = FourFragment.newInstance();

        fragments.add(oneFragment);
        fragments.add(twoFragment);
        fragments.add(threeFragment);
        fragments.add(fourFragment);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments);
        mMyNoScrollViewPager.setOffscreenPageLimit(fragments.size());
        mMyNoScrollViewPager.setAdapter(adapter);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void initData() {
        String phone = mBuProcessor.getLoginUser().getUserPhone();
        String token = mBuProcessor.getLoginUser().getUserToken();
        LogUtils.i("phone=" + phone + "  token=" + token);
    }

    @Override
    void onReceivePro(Context context, Intent intent) {
        if (intent.getAction()!=null && intent.getAction().equals(IntentConstant.UPDATE_PERSON_LOCATION)) {
            showToast("更新定位信息");
        }
        super.onReceivePro(context, intent);
    }

    @Override
    void addFilter() {
        mFilter.addAction(IntentConstant.UPDATE_PERSON_LOCATION);
        super.addFilter();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_one:
                mMyNoScrollViewPager.setCurrentItem(SWITCH_FIRST_PAGE, false);
                break;
            case R.id.action_two:
                mMyNoScrollViewPager.setCurrentItem(SWITCH_SECOND_PAGE, false);
                break;
            case R.id.action_three:
                mMyNoScrollViewPager.setCurrentItem(SWITCH_THIRD_PAGE, false);
                break;
            case R.id.action_four:
                mMyNoScrollViewPager.setCurrentItem(SWITCH_FORTH_PAGE, false);
                break;
        }
        return true;
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static Intent getMainIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private void initInjectData() {
        MainComponent mMainComponent = DaggerMainComponent.builder().appComponent(getAppComponent())
                .mainModule(new MainModule(MainActivity.this, this))
                .activityModule(new ActivityModule(this)).build();
        mMainComponent.inject(this);
    }

    @Override
    public void bannerSuccess(MainBannerResponse response) {
    }

    /**
     *  1.广播传值
     *   LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(IntentConstant.LOGON_TO_MAIN));
     *  2.startctivityForResult返回值
     *  注意 setResult(RESULT_OK);必须写在super.onBackPressed()上面  否则回返回resultCode =0 而不是 -1
     */
    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        AMapLocation mLocationInfo = ((App)getApplicationContext()).getMapLocation();
        if (mLocationInfo != null) {//停止定位
            mAMapLocationClient.onDestroy();
        }
        super.onDestroy();
    }
}
