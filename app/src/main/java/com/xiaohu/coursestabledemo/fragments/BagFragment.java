package com.xiaohu.coursestabledemo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaohu.coursestabledemo.R;

/**
 * Created by WNN-PC on 2016/5/17.
 */
public class BagFragment extends Fragment {
    /**
     * 拿到碎片的布局
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bag_fragment, null);
        return view;
    }

}
