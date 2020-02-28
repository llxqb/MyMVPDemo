package com.freemud.app.mvp.demo.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by li.liu on 2018/3/23.
 * 测试懒加载中适配器加载数据
 */

public class FruitMult implements Parcelable, MultiItemEntity {
    private String name;
    private double price;
    private String desc;
    private int url;
    private int type;

    public static final int LAYOUT_ONE = 1;
    public static final int LAYOUT_TWO = 2;


    @Override
    public int getItemType() {
        if (type == 1) {
            return LAYOUT_ONE;
        } else if (type == 2) {
            return LAYOUT_TWO;
        } else {
            return -1;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static final Creator<FruitMult> CREATOR = new Creator<FruitMult>() {
        @Override
        public FruitMult createFromParcel(Parcel in) {
            return new FruitMult(in);
        }

        @Override
        public FruitMult[] newArray(int size) {
            return new FruitMult[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(desc);
        dest.writeInt(url);
    }

    public FruitMult(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        desc = in.readString();
        url = in.readInt();
    }

    public FruitMult() {
    }
}
