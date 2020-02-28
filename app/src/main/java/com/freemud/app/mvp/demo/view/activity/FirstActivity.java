package com.freemud.app.mvp.demo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.freemud.app.mvp.demo.database.Order;
import com.freemud.app.mvp.demo.database.User;
import com.freemud.app.mvp.demo.database.UserDaoUtils;
import com.freemud.app.mvp.demo.di.components.DaggerFirstActivityComponent;
import com.freemud.app.mvp.demo.di.components.FirstActivityComponent;
import com.freemud.app.mvp.demo.di.modules.ActivityModule;
import com.freemud.app.mvp.demo.di.modules.FirstActivityModule;
import com.freemud.app.mvp.demo.gen.DaoSession;
import com.freemud.app.mvp.demo.gen.OrderDao;
import com.freemud.app.mvp.demo.gen.UserDao;
import com.freemud.app.mvp.demo.mymvpdemo3.R;
import com.freemud.app.mvp.demo.presenter.PresenterControl.FirstActivityControl;
import com.freemud.app.mvp.demo.view.adapter.DaoUserAdapter;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class FirstActivity extends BaseActivity implements FirstActivityControl.FirstActivityView {
    @Inject
    FirstActivityControl.PresenterFirstActivity mPresenterLogin;
    @BindView(R.id.first_activity_content)
    TextView firstActivityContent;
    @BindView(R.id.middle_name)
    TextView middleName;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.add)
    Button mAdd;
    @BindView(R.id.delete)
    Button mDelete;
    @BindView(R.id.deleteall)
    Button mDeleteAll;
    @BindView(R.id.update)
    Button mUpdate;
    @BindView(R.id.read)
    Button mRead;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private UserDao mUserDao;
    private OrderDao mOrderDao;
    private List<User> mUserList = new ArrayList<>();
    private List<Order> mOrderList = new ArrayList<>();
    DaoUserAdapter mDaoUserAdapter;
    @Inject
    DaoSession mDaoSession;
    UserDaoUtils userDaoUtils;

    public static void start(Context context) {
        Intent intent = new Intent(context, FirstActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);
        supportActionBar(mToolbar, true);
        initializeInjector();
        initView();
        initData();
    }

    @Override
    public void initView() {
        middleName.setText("哈哈哈~~~");
        mUserDao = mDaoSession.getUserDao();
        mOrderDao = mDaoSession.getOrderDao();

        RxView.clicks(mAdd).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> add());
        RxView.clicks(mDelete).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> delete());
        RxView.clicks(mDeleteAll).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> deleteAll());
        RxView.clicks(mUpdate).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> update());
        RxView.clicks(mRead).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> query());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mDaoUserAdapter = new DaoUserAdapter(mUserList);
//        mRecyclerView.setAdapter(mDaoUserAdapter);
    }

    @Override
    public void initData() {
//{userId=6de4bcba-79a6-4bc5-94f0-d44a0a6bdf18, shopId=178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310022}
        userDaoUtils = new UserDaoUtils(mUserDao, mOrderDao);
        String userId = "6de4bcba-79a6-4bc5-94f0-d44a0a6bdf18";
        String shopId = "178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310022";
        mPresenterLogin.onRequestLookNum(userId, shopId);
    }

    private void initializeInjector() {
        FirstActivityComponent mfirstComponent = DaggerFirstActivityComponent.builder().appComponent(getAppComponent())
                .firstActivityModule(new FirstActivityModule(this, this))
                .activityModule(new ActivityModule(this)).build();
        mfirstComponent.inject(this);
    }

    @Override
    public void lookSuccess(int response) {
        firstActivityContent.setText(String.valueOf(response));
        mUserList = mUserDao.loadAll();
    }

    User user = null;

    private void add() {
        List<Order> orderList = null;
        for (int i = 0; i < 1; i++) {
            user = new User();
            user.username = "张三";
            user.userId = UUID.randomUUID().toString();
//            user.setSex("男");
//            user.setYear(22);
            user.setDuty("工程师" + i);
            orderList = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                Order order = new Order();
                order.setOrderName("我的订单333" + j);
                orderList.add(order);
            }
            user.orderList = orderList;
        }

        userDaoUtils.insertUser(user).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(User user) {
                Log.e("First", "user:" + new Gson().toJson(user));
                userDaoUtils.insertOrder(user).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Iterable<Order>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Iterable<Order> orders) {
                        Log.e("First", "orders:" + new Gson().toJson(orders));
                    }
                });
            }
        });
    }

    private void delete() {
        mUserDao.deleteByKey(1l);
//        look();
    }

    private void deleteAll() {
        mUserDao.deleteAll();
//        look();
    }

    private void update() {
        user.username = "李四";
        user.sex = "男";
        user.year = 33;
        mUserDao.updateInTx(user);
        mUserDao.insertInTx();
        userDaoUtils.lookAllUser();
    }

    private void  query() {
        userDaoUtils.lookAllUser().observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<User>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<User> users) {
                Log.e("First", "users:" + new Gson().toJson(users));
                for (User user : users){
                    Log.e("First", "order:" + new Gson().toJson(user.getOrderList()));
                }
            }
        });
    }


}
