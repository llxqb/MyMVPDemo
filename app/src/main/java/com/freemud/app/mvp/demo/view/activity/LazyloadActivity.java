package com.freemud.app.mvp.demo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.freemud.app.mvp.demo.mymvpdemo3.R;
import com.freemud.app.mvp.demo.view.fragment.LazyLoadOneFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LazyloadActivity extends BaseActivity {

    @BindView(R.id.lazylaod_tablayout)
    TabLayout mLazylaodTablayout;
    @BindView(R.id.lazylaod_viewpager)
    ViewPager mLazylaodViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazyload);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mLazylaodViewpager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        //表示缓存页面
//        orderViewpager.setOffscreenPageLimit(6);

        mLazylaodTablayout.setupWithViewPager(mLazylaodViewpager);
        mLazylaodTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);//MODE_SCROLLABLE
//        orderViewpager.setCurrentItem(currentItem);
    }

    @Override
    public void initData() {
    }

    private class MyPageAdapter extends FragmentPagerAdapter {

        private final String[] titles = {"全部", "待自提", "待付款", "待发货", "待评价", "配送中", "退款/售后"};
        private List<Fragment> frag = new ArrayList<>();

        private MyPageAdapter(FragmentManager fm) {
            super(fm);
            frag.add(LazyLoadOneFragment.newInstance(0,titles[0]));
            frag.add(LazyLoadOneFragment.newInstance(1,titles[1]));
            frag.add(LazyLoadOneFragment.newInstance(2,titles[2]));
            frag.add(LazyLoadOneFragment.newInstance(3,titles[3]));
            frag.add(LazyLoadOneFragment.newInstance(4,titles[4]));
            frag.add(LazyLoadOneFragment.newInstance(5,titles[5]));
            frag.add(LazyLoadOneFragment.newInstance(6,titles[6]));
        }

        @Override
        public Fragment getItem(int position) {
            return frag.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    public static void start(Context context){
        Intent intent = new Intent(context,LazyloadActivity.class);
        context.startActivity(intent);
    }
}
