package com.xiaohu.coursestabledemo.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by WNN-PC on 2016/6/4.
 */
public class DynamicViewPagerAdapter extends PagerAdapter {
    private List<View> mList;//页卡容器

    public DynamicViewPagerAdapter(List<View> mList) {
        this.mList = mList;
    }

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
        //官方提示这样写
        return view == object;
    }

}
