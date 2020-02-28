package com.freemud.app.mvp.demo.view.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freemud.app.mvp.demo.entity.response.Fruit;
import com.freemud.app.mvp.demo.mymvpdemo3.R;

import java.util.List;

/**
 * Created by li.liu on 2018/3/23.
 * 加载水果adapter
 */

public class LazyFruitAdapter extends BaseQuickAdapter<Fruit, BaseViewHolder> {

    public LazyFruitAdapter(Context context, @Nullable List<Fruit> data) {
        super(R.layout.adapter_fruit_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Fruit item) {
        if (item != null) {
            holder.setText(R.id.fruit_item_name, item.getName());
            holder.setText(R.id.fruit_item_price, "￥"+String.valueOf(item.getPrice()));
            holder.setText(R.id.fruit_item_desc, item.getDesc());
            holder.setImageResource(R.id.fruit_item_image, item.getUrl());
        }

    }
}
