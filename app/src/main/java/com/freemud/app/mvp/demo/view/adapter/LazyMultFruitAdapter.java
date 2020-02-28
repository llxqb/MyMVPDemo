package com.freemud.app.mvp.demo.view.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freemud.app.mvp.demo.entity.response.FruitMult;
import com.freemud.app.mvp.demo.mymvpdemo3.R;

import java.util.List;

/**
 * Created by li.liu on 2018/3/23.
 * 加载水果adapter
 */

public class LazyMultFruitAdapter extends BaseMultiItemQuickAdapter<FruitMult, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public LazyMultFruitAdapter(List<FruitMult> data) {
        super(data);
        addItemType(FruitMult.LAYOUT_ONE, R.layout.adapter_fruit_layout);
        addItemType(FruitMult.LAYOUT_TWO, R.layout.adapter_fruit_layout);
    }

    @Override
    protected void convert(BaseViewHolder holder, FruitMult item) {
        if (item != null) {
            if (item.getType() == 1) {
                holder.setText(R.id.fruit_item_name, item.getName());
                holder.setText(R.id.fruit_item_price, "￥" + String.valueOf(item.getPrice()));
                holder.setText(R.id.fruit_item_desc, item.getDesc());
                holder.setImageResource(R.id.fruit_item_image, item.getUrl());
            } else {
                holder.setText(R.id.fruit_item_name, item.getName());
                holder.setText(R.id.fruit_item_price, "￥" + String.valueOf(item.getPrice()));
                holder.setVisible(R.id.fruit_item_desc, false);
                holder.setVisible(R.id.fruit_item_image, false);
                holder.setText(R.id.fruit_item_desc, item.getDesc());
                holder.setImageResource(R.id.fruit_item_image, item.getUrl());
            }

        }

    }
}
