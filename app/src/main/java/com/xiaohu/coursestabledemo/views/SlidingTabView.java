package com.xiaohu.coursestabledemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaohu.coursestabledemo.R;

/**
 * Created by WNN-PC on 2016/6/11.
 */
public class SlidingTabView extends LinearLayout {
    /*默认的页卡颜色*/
    private final int DEFAULT_INDICATOR_COLOR = 0xffff00ff;
    /*默认分割线的颜色*/
    private final int DEFAULT_DIVIDER_COLOR = 0xff000000;
    /*默认title字体的大小*/
    private final int DEFAULT_TEXT_SIZE = 16;
    /*默认padding*/
    private final int DEFAULT_TEXT_PADDING = 16;
    /*divider默认的宽度*/
    private final int DEFAULT_DIVIDER_WIDTH = 1;
    /*indicator 的高度*/
    private final int DEFAULT_INDICATOR_HEIGHT = 5;
    /*底部线条的高度默认值*/
    private final int DEFAULT_BOTTOM_LINE_HEIGHT = 2;
    /*分割线距离上下边缘的距离默认为8*/
    private final int DEFAULT_DIVIDER_MARGIN = 8;
    /*底部线条的颜色默认值*/
    private final int DEFAULT_BOTTOM_LINE_COLOR = 0xff000000;
    /*底部线条的颜色*/
    private int mBottomLineColor = DEFAULT_BOTTOM_LINE_COLOR;
    /*底部线条的高度*/
    private int mBottomLineHeight = DEFAULT_BOTTOM_LINE_HEIGHT;
    /*滑动指示器的高度*/
    private int mIndicatorHeight = DEFAULT_INDICATOR_HEIGHT;
    /*分割线的宽度*/
    private int mDividerWidth = DEFAULT_DIVIDER_WIDTH;
    /*页卡的颜色*/
    private int mIndicatorColor = DEFAULT_INDICATOR_COLOR;
    /*分割线的颜色*/
    private int mDividerColor = DEFAULT_DIVIDER_COLOR;
    /*分割线距离上线边距的距离*/
    private int mDividerMargin = DEFAULT_DIVIDER_MARGIN;
    /*当前位置*/
    private int position;
    /*偏移量*/
    private float offset;
    /*分割线画笔*/
    private Paint mDividerPaint;
    /*页卡画笔*/
    private Paint mIndicatorPaint;
    /*底部线条的画笔*/
    private Paint mBottomPaint;

    public SlidingTabView(Context context) {
        super(context);
    }

    public SlidingTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //添加tab
        String[] tabNames = {"朋友", "高校", "我"};
        for (int i = 0; i < 3; i++) {
            TextView tv = new TextView(context);
            LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
            tv.setText(tabNames[i]);
            tv.setTextSize(16);
            tv.setTextColor(Color.BLACK);
            tv.setGravity(Gravity.CENTER);
            this.addView(tv, params);
        }

        //画笔初始化
        initView();
    }

    public SlidingTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //水平布局
        this.setOrientation(HORIZONTAL);
//        /*获取TypedArray*/
//        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.SlidingTabView);
//        /*获取自定义属性的个数*/
//        int N = typedArray.getIndexCount();
//        Log.v("zgy", "=========getIndexCount=========" + N);
//        for (int i = 0; i < N; i++) {
//            int attr = typedArray.getIndex(i);
//            switch (attr) {
//                case R.styleable.SlidingTabView_indicatorColor:
//                    /*获取页卡颜色值*/
//                    mIndicatorColor = typedArray.getColor(attr, DEFAULT_INDICATOR_COLOR);
//                    break;
//                case R.styleable.SlidingTabView_dividerColor:
//                    /*获取分割线颜色的值*/
//                    mDividerColor = typedArray.getColor(attr, DEFAULT_DIVIDER_COLOR);
//                    break;
//                case R.styleable.SlidingTabView_bottomLineColor:
//                    /*获取底部线条颜色的值*/
//                    mBottomLineColor = typedArray.getColor(attr, DEFAULT_BOTTOM_LINE_COLOR);
//                    break;
//                case R.styleable.SlidingTabView_dividerMargin:
//                    /*获取分割线的距离上线边距的距离*/
//                    mDividerMargin = (int) typedArray.getDimension(attr, DEFAULT_DIVIDER_MARGIN * getResources().getDisplayMetrics().density);
//                    Log.v("zgy", "=========mDividerMargin=========" + mDividerMargin);
//                    break;
//                case R.styleable.SlidingTabView_indicatorHeight:
//                    /*获取页卡的高度*/
//                    mIndicatorHeight = (int) typedArray.getDimension(attr, DEFAULT_INDICATOR_HEIGHT * getResources().getDisplayMetrics().density);
//                    break;
//                case R.styleable.SlidingTabView_bottomLineHeight:
//                    /*获取底部线条的高度*/
//                    mBottomLineHeight = (int) typedArray.getDimension(attr, DEFAULT_BOTTOM_LINE_HEIGHT * getResources().getDisplayMetrics().density);
//                    break;
//                case R.styleable.SlidingTabView_dividerWidth:
//                    /*获取分割线的宽度*/
//                    mDividerWidth = (int) typedArray.getDimension(attr, DEFAULT_DIVIDER_WIDTH * getResources().getDisplayMetrics().density);
//                    break;
//            }
//        }
//        /*释放TypedArray*/
//        typedArray.recycle();


    }

    public void setPositionAndOffset(int position, float offset) {
        this.position = position;
        this.offset = offset;
        //无效视图，从触发onDraw回调，新绘制界面
        invalidate();
    }

    private void initView() {
        setWillNotDraw(false);

        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setColor(Color.GRAY);
        mDividerPaint.setStrokeWidth(mDividerWidth);

        int idcColor = getResources().getColor(R.color.colorDarkBlue);
        mIndicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIndicatorPaint.setColor(idcColor);

        mBottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBottomPaint.setColor(mBottomLineColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getChildCount() == 0) {
            return;
        }
        System.out.println("=========>onDraw");
        final int height = getHeight();
        /*当前页面的View tab*/
        View selectView = getChildAt(position);
        /*计算开始绘制的位置*/
        int left = selectView.getLeft();
        /*计算结束绘制的位置*/
        int right = selectView.getRight();
        if (offset > 0) {
            View nextView = getChildAt(position + 1);
            /*如果有偏移量，重新计算开始绘制的位置*/
            left = (int) (offset * nextView.getLeft() + (1.0f - offset) * left);
            /*如果有偏移量，重新计算结束绘制的位置*/
            right = (int) (offset * nextView.getRight() + (1.0f - offset) * right);
        }
        /*绘制滑动的页卡*/
        canvas.drawRect(left, height - 15, right, height, mIndicatorPaint);
        canvas.drawRect(0, height - 1, getWidth(), height, mBottomPaint);
        for (int i = 0; i < getChildCount() - 1; i++) {
            View child = getChildAt(i);
            canvas.drawLine(child.getRight(), 8,
                    child.getRight(), height - 8,
                    mDividerPaint);
        }
    }
}
