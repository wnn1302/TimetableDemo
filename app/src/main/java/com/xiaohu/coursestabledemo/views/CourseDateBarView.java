package com.xiaohu.coursestabledemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaohu.coursestabledemo.R;

/**
 * Created by WNN-PC on 2016/5/29.
 */
public class CourseDateBarView extends LinearLayout {

    private LayoutParams tvMonthsParams;
    private LayoutParams tvLineParams;
    private float mTextSize;
    private int mTextColor;
    private Drawable mTextBackground;

    private LayoutParams tvParams;

    public CourseDateBarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        int colorGray = getResources().getColor(R.color.colorGray);
        int colorLightBlue = getResources().getColor(R.color.colorLightBlue);

        //设置父布局为垂直布局
        this.setOrientation(this.VERTICAL);
        //上描线
        View culomnLine = new View(context);
        culomnLine.setBackgroundColor(colorGray);
        LayoutParams tclParams = new LayoutParams(LayoutParams.MATCH_PARENT,1);
        this.addView(culomnLine,tclParams);

        LinearLayout ll = new LinearLayout(context);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,0,1);
        ll.setOrientation(HORIZONTAL);
        this.addView(ll,llParams);

        /**
         * 获取从XML映射而来的属性
         */
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CourseDateBarView);
        mTextColor = ta.getColor(R.styleable.CourseDateBarView_textColor, 0);
        mTextSize = ta.getDimension(R.styleable.CourseDateBarView_textSize, 0);
        mTextBackground = ta.getDrawable(R.styleable.CourseDateBarView_textBackground);
        //释放TypedArray所占用的资源
        ta.recycle();

        /**
         * 添加七个textView用于显示星期一到星期天
         */
        //设置textView的布局横向权重(第三个参数),高度wrap_content
        tvParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, 2);
        tvLineParams = new LayoutParams(1, LayoutParams.MATCH_PARENT);
        tvMonthsParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);


        String[] week = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

        //添加月份栏
        TextView tvMonths = new TextView(context);
        tvMonths.setTextColor(mTextColor);
        tvMonths.setTextSize(mTextSize);
        tvMonths.setBackground(mTextBackground);
        tvMonths.setText("5月");
        ll.addView(tvMonths, tvMonthsParams);

        for (int i = 0; i < 7; i++) {

            TextView tv = new TextView(context);
            TextView tvLine = new TextView(context);

            tv.setTextSize(mTextSize);
            tv.setTextColor(mTextColor);
            //在API level16（4.1以上系统）以上才可使用
            tv.setBackground(mTextBackground);
            tv.setGravity(Gravity.CENTER);
            tv.setText(week[i]);
            tv.setPadding(0,0,0,0);

            tvLine.setBackgroundColor(colorGray);

            //向父布局添加子控件
            ll.addView(tvLine, tvLineParams);
            ll.addView(tv, tvParams);
        }
        //下描线
        View culomnLine2 = new View(context);
        culomnLine2.setBackgroundColor(colorGray);
        LayoutParams tclParams2 = new LayoutParams(LayoutParams.MATCH_PARENT,1);
        this.addView(culomnLine2,tclParams2);

        TextView choosedTv = (TextView) ll.getChildAt(4);
        choosedTv.setText("31\n周二");
        choosedTv.setBackgroundColor(colorLightBlue);
    }
}
