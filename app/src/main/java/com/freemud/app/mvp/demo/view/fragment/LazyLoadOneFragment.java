package com.freemud.app.mvp.demo.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freemud.app.mvp.demo.entity.response.FruitMult;
import com.freemud.app.mvp.demo.mymvpdemo3.R;
import com.freemud.app.mvp.demo.util.LogUtils;
import com.freemud.app.mvp.demo.view.adapter.LazyMultFruitAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li.liu on 2018/1/12.
 * lazyOneFragment
 */

public class LazyLoadOneFragment extends LazyLoadFragment implements BaseQuickAdapter.RequestLoadMoreListener {
    private String mTitle = null;
    private int type;
    private LazyMultFruitAdapter lazyFruitAdapter;
    private List<FruitMult> fruitList;
    private int currentPos = 0;

    public static LazyLoadOneFragment newInstance(int type, String title) {
        LazyLoadOneFragment lazyLoadOneFragment = new LazyLoadOneFragment();
        Bundle bd = new Bundle();
        bd.putInt("type", type);
        lazyLoadOneFragment.mTitle = title;
        lazyLoadOneFragment.setArguments(bd);
        return lazyLoadOneFragment;
    }

    /**
     * 初始化数据到这里  不能用ButterKnife  因为继承于LazyLoadFragment
     */
    @Override
    public void initView() {
        TextView twofragmentTv = findViewById(R.id.twofragment_tv);
        twofragmentTv.setText(mTitle);
        RecyclerView mRecyclerview = findViewById(R.id.recyclerview);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        initData();
        lazyFruitAdapter = new LazyMultFruitAdapter(fruitList);
        lazyFruitAdapter.setOnLoadMoreListener(this, mRecyclerview);
        mRecyclerview.setAdapter(lazyFruitAdapter);
        lazyFruitAdapter.setOnItemClickListener((adapter, view, position) -> showToast("点击位置为 " + position));
//        lazyFruitAdapter.setNewData(fruitList);
    }

    @Override
    public void initData() {
        fruitList = new ArrayList<>();
        for (int i = currentPos; i < currentPos + 10; i++) {
            FruitMult fruit = new FruitMult();
            fruit.setName(i + " 好大好大的西瓜，我们都喜欢吃的西瓜~~~");
            fruit.setPrice(20.88);
            fruit.setDesc("来自湖南本地又大又甜的西瓜，来自湖南本地又大又甜的西瓜");
            fruit.setUrl(R.drawable.watermelon);
            if (i % 2 != 0) {
                fruit.setType(1);
            } else {
                fruit.setType(2);
            }
            fruitList.add(fruit);
        }
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        if (currentPos >= 39) {
            lazyFruitAdapter.loadMoreEnd();
        } else {
            currentPos += 10;
            initData();
            lazyFruitAdapter.addData(fruitList);
            lazyFruitAdapter.loadMoreComplete();
        }
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_two;
    }

    @Override
    protected void lazyLoad() {
        if (getArguments() != null) {
            this.type = getArguments().getInt("type");// 0 1 2
        }
        if (isInit) {
            initData();
            LogUtils.i("已经初始并已经显示给用户可以加载数据 " + this.type);
        } else {
            LogUtils.i("没有初始化不能加载数据 " + this.type);
        }
    }


    @Override
    protected void stopLoad() {
        super.stopLoad();
        LogUtils.i("已经对用户不可见，可以停止加载数据 " + this.type);
    }

}
