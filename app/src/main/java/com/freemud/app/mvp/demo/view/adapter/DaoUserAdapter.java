package com.freemud.app.mvp.demo.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freemud.app.mvp.demo.database.User;
import com.freemud.app.mvp.demo.mymvpdemo3.R;

import java.util.List;

/**
 * Created by li.liu on 2018/4/10.
 * greenDao测试adapter加载数据
 */

public class DaoUserAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public DaoUserAdapter(@Nullable List<User> data) {
        super(R.layout.adapter_user_item, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, User item) {
        if (item == null) return;
        holder.setText(R.id.user_item_id, String.valueOf(item.getId()));
        holder.setText(R.id.user_item_name, item.getUsername());
        holder.setText(R.id.user_item_sex, item.getSex());
        holder.setText(R.id.user_item_year, item.getYear()+"");
        holder.setText(R.id.user_item_duty, item.getDuty());
    }
}
