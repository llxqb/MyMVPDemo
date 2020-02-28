package com.freemud.app.mvp.demo.view.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freemud.app.mvp.demo.mymvpdemo3.R;


/**
 * Created by li.liu on 2018/2/7.
 * 公共topBar
 */

public class MyTopBarView extends RelativeLayout {
    private String TAG ="MyTopBarView";
    private Context mContext;
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;

    private LayoutParams mLeftParams = null;
    private LayoutParams mRightParams = null;
    private LayoutParams mTitleParams = null;

    private topbarClickListener mTopbarClickListener;


    public MyTopBarView(Context context) {
        super(context);
        mContext = context;
    }

    public MyTopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        //把 attrs.xml 中 MyTopBar 所有属性值存储到TypeArray中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTopBar);
        //从 TypeArray中取出对应的值来为要设置的属性赋值
        mLeftText = ta.getString(R.styleable.MyTopBar_leftText);
        mLeftTextColor = ta.getColor(R.styleable.MyTopBar_leftTextColor,0);
        mLeftBackground = ta.getDrawable(R.styleable.MyTopBar_leftBackground);
        mRightText = ta.getString(R.styleable.MyTopBar_rightText);
        mRightTextColor = ta.getColor(R.styleable.MyTopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.MyTopBar_rightBackground);
        mTitle = ta.getString(R.styleable.MyTopBar_title);
        mTitleTextColor = ta.getColor(R.styleable.MyTopBar_titleTextColor, 0);
        mTitleTextSize = ta.getDimension(R.styleable.MyTopBar_titleTextSize, 10);
        //获取完之后 调用recycle() 避免重新创建的时候的错误  资源回收
        ta.recycle();
        initView();
    }

    private void initView(){
        mLeftButton = new Button(mContext);
        mRightButton = new Button(mContext);
        mTitleView = new TextView(mContext);

        mLeftButton.setText(mLeftText);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setAllCaps(false);//设置文本为小写

        mRightButton.setText(mRightText);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setAllCaps(false);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        //为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        //添加ViewGroup
        addView(mLeftButton,mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mRightButton,mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleView,mTitleParams);

        initClickListener();
    }

    private void initClickListener() {
        mLeftButton.setOnClickListener(v -> mTopbarClickListener.leftClick());

        mRightButton.setOnClickListener(v -> mTopbarClickListener.rightClick());
    }

    public interface topbarClickListener{
        void leftClick();
        void rightClick();
    }

    public void setOnTopBarClickListener(topbarClickListener topBarClickListener){
        this.mTopbarClickListener = topBarClickListener;
    }

}
