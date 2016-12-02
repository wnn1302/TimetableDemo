package com.xiaohu.coursestabledemo.beans;


import android.app.Fragment;

/**
 * Created by moon.zhong on 2015/3/9.
 */
public class DynamicPagerItem {

    /*item的 title*/
    private String mTitle;
    private Fragment mFragment;

    public DynamicPagerItem(String mTitle, Fragment mFragment) {
        this.mTitle = mTitle;
        this.mFragment = mFragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public String getTitle() {
        return mTitle;
    }
}
