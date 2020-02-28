package com.freemud.app.mvp.demo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.freemud.app.mvp.demo.App;
import com.freemud.app.mvp.demo.di.components.DaggerMainFragmentComponent;
import com.freemud.app.mvp.demo.di.modules.MainFragmentModule;
import com.freemud.app.mvp.demo.di.modules.MainModule;
import com.freemud.app.mvp.demo.entity.base.BuProcessor;
import com.freemud.app.mvp.demo.entity.request.MainBannerRequest;
import com.freemud.app.mvp.demo.entity.response.MainBannerResponse;
import com.freemud.app.mvp.demo.help.GlideHelper.BannerImageLoader;
import com.freemud.app.mvp.demo.mymvpdemo3.R;
import com.freemud.app.mvp.demo.presenter.PresenterControl.OneFragmentControl;
import com.freemud.app.mvp.demo.util.LogUtils;
import com.freemud.app.mvp.demo.view.activity.FirstActivity;
import com.freemud.app.mvp.demo.view.activity.LazyloadActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by li.liu on 2018/1/12.
 * 首页OneFragment
 */
public class OneFragment extends BaseFragment implements OneFragmentControl.OneFragmentView {

    public static OneFragment newInstance() {
        return new OneFragment();
    }

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.onefragment_left_btn)
    Button mLeftBtn;
    @BindView(R.id.novel_tv)
    Button mNovelTv;
    @BindView(R.id.onefragment_image)
    ImageView onefragmentImage;
    @BindView(R.id.go_lazyfragment)
    Button mLazyFragment;
    @Inject
    OneFragmentControl.OneFragmentPresenter mPresenter;
    @Inject
    BuProcessor mBuProcessor;

    private List<String> mImageList = new ArrayList<>();
    private List<String> mBannerTitle = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_one, container, false);
        initInjectData();
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    public void initView() {
        RxView.clicks(mLeftBtn).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> startToFirstActivity());
        RxView.clicks(mLazyFragment).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> startToLazyFragment());
        RxView.clicks(mNovelTv).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> startNovel());
        banner.setOnBannerListener(position -> showToast("点击位置：" + position));
    }

    @Override
    public void initData() {
        MainBannerRequest request = new MainBannerRequest();
        request.setOp("activityList");
        request.setAccess_token("12345");
        request.setPartnerId("178a14ba-85a8-40c7-9ff4-6418418f5a0c");
        request.setPageNo(1);
        request.setPageSize(10);
        request.setType("3");
        mPresenter.onRequestBanner(request);
//        new BannerImageLoader(mImageLoaderHelper).displayImage(getActivity(),"http://7xrib0.com2.z0.glb.qiniucdn.com/201801/1516859105435.jpg",onefragmentImage);
        mBuProcessor.setLoginUser("13262253731", "012344");

        AMapLocation mLocationInfo = ((App) getActivity().getApplicationContext()).getMapLocation();
        if (mLocationInfo != null) {
            double longitude = mLocationInfo.getLongitude();
            double latitude = mLocationInfo.getLatitude();
            LogUtils.i("longitude=" + longitude + " latitude=" + latitude);
        }

    }

    @Override
    public void bannerSuccess(MainBannerResponse response) {
        List<MainBannerResponse.RecordsBean> mList = response.getRecords();
        for (MainBannerResponse.RecordsBean recordsBean : mList) {
            mImageList.add(recordsBean.getImageUrl());
            mBannerTitle.add(recordsBean.getTitle());
        }
        initBanner();
    }

    //[http://7xrib0.com2.z0.glb.qiniucdn.com/201801/1516859105435.jpg, http://7xrib0.com2.z0.glb.qiniucdn.com/201803/1520582020546.jpg, http://7xrib0.com2.z0.glb.qiniucdn.com/201803/1520075097394.jpg, http://7xrib0.com2.z0.glb.qiniucdn.com/201803/1520412432991.png, http://7xrib0.com2.z0.glb.qiniucdn.com/201803/1520412488211.jpg]
    private void initBanner() {
        LogUtils.i("--" + mImageList.toString());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);//设置圆形指示器与标题
        banner.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器位置
        banner.setDelayTime(5000);//设置轮播时间
        banner.setImages(mImageList);//设置图片源
        banner.setBannerTitles(mBannerTitle);//设置标题源
        banner.setImageLoader(new BannerImageLoader(mImageLoaderHelper));
        banner.start();
    }

    private void startToFirstActivity() {
        FirstActivity.start(getActivity());
    }

    /**
     * go懒加载页面
     */
    private void startToLazyFragment() {
        LazyloadActivity.start(getActivity());
    }

    private void startNovel(){

    }

    private void initInjectData() {
        DaggerMainFragmentComponent.builder().appComponent(((App) getActivity().getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .mainFragmentModule(new MainFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public void onDestroyView() {
//        getActivity().setResult(Activity.RESULT_OK);
        super.onDestroyView();
    }
}
