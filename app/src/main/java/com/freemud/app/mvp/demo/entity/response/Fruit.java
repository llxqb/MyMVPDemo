package com.freemud.app.mvp.demo.entity.response;

/**
 * Created by li.liu on 2018/3/23.
 * 测试懒加载中适配器加载数据
 */

public class Fruit {
    private String name;
    private double price;
    private String desc;
    private int url;

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
}
