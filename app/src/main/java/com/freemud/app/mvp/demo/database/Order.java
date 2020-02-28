package com.freemud.app.mvp.demo.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by li.liu on 2018/10/26.
 */
@Entity
public class Order {
    @Id
    public Long id;
    @Property(nameInDb = "userId")
    public String userId;
    public Long orderId;
    public String orderName;
    @Generated(hash = 398986897)
    public Order(Long id, String userId, Long orderId, String orderName) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.orderName = orderName;
    }
    @Generated(hash = 1105174599)
    public Order() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Long getOrderId() {
        return this.orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getOrderName() {
        return this.orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }


}
