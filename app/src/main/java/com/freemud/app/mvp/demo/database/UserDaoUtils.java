package com.freemud.app.mvp.demo.database;

import com.freemud.app.mvp.demo.gen.OrderDao;
import com.freemud.app.mvp.demo.gen.UserDao;

import java.util.List;

import rx.Observable;

/**
 * Created by li.liu on 2018/10/30.
 */

public class UserDaoUtils {
    private UserDao mUserDao;
    private OrderDao mOrderDao;

    public UserDaoUtils(UserDao userDao ,OrderDao orderDao) {

        mUserDao = userDao;
        mOrderDao = orderDao;
    }

    public Observable<List<User>> lookAllUser() {
//       return mUserDao.queryBuilder().where(UserDao.Properties.Id.gt(1)).rx().list();
        return mUserDao.rx().loadAll();
//        mOrderList = mOrderDao.queryBuilder().build().list();
//        for (User user : mUserList) {
//            mOrderList.addAll(user.getOrderList());
//        }
//        LogUtils.e("mUserList:" + new Gson().toJson(mUserList));
    }

    public Observable<User> queryOrderByUserId(Long userId) {
        return mUserDao.rx().load(userId);
    }

    /**
     * 插入用户表
     */
    public Observable<User> insertUser(User user) {
        return mUserDao.rx().insert(user);
    }

    /**
     * 插入订单表   需要指定其父表
     */
    public Observable<Iterable<Order>> insertOrder(User user) {
        if (user.orderList != null) {
            for (Order order : user.orderList){
                order.userId = user.userId;
            }
            return mOrderDao.rx().insertInTx(user.orderList);
        }
        return null;
    }
}
