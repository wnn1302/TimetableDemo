package com.xiaohu.coursestabledemo.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaohu.coursestabledemo.R;

import java.util.List;


/**
 * Created by WNN-PC on 2016/6/6.
 */
public class ImgsDialog extends Dialog {
    private Context context;
    private List<View> mList;
    private TextView tvImgNum;

    public ImgsDialog(Context context, int themeResId, List<View> mList) {
        super(context, themeResId);
        this.context = context;
        this.mList = mList;
    }

//    protected ImgsDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//    }
//
//    public ImgsDialog(Context context) {
//        super(context);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgs_dialog);
        //初始化dialog里的ViewPage
        ViewPager vp = (ViewPager) findViewById(R.id.img_viewpager);//getContext
        vp.setAdapter(new MyPagerAdapter());
        vp.setCurrentItem(0);
        vp.setOnPageChangeListener(new MyOnPageChangeListener());
        //初始化底部的功能栏
        tvImgNum = (TextView) findViewById(R.id.tv_img_num);
        tvImgNum.setText(1 + "/" + mList.size() + "页");//默认提示为 “ 1/..页”
        //点击关闭自己
        this.setCanceledOnTouchOutside(true);
        LinearLayout contentView = (LinearLayout) findViewById(R.id.imgdialog_content);
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cancel();
                return false;
            }
        });
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tvImgNum.setText((++position) + "/" + mList.size() + "页");
            Toast.makeText(context, "page" + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class MyPagerAdapter extends PagerAdapter {
        /**
         * 获取页卡总数
         *
         * @return
         */
        @Override
        public int getCount() {
            return mList.size();
        }

        /**
         * 删除页卡
         *
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        /**
         * 实例化、添加页卡
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position), 0);
            return mList.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}