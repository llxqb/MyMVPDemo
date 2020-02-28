package com.freemud.app.mvp.demo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freemud.app.mvp.demo.mymvpdemo3.R;

/**
 * Created by li.liu on 2018/1/12.
 */

public class ThreeFragment extends BaseFragment {
    public static ThreeFragment newInstance() {
        return new ThreeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_three, container, false);
        return view;
    }


}
