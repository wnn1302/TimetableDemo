package com.xiaohu.coursestabledemo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaohu.coursestabledemo.R;
import com.xiaohu.coursestabledemo.adapters.DynamicFriendsListAdapter;
import com.xiaohu.coursestabledemo.adapters.DynamicViewPagerAdapter;
import com.xiaohu.coursestabledemo.views.SlidingTabView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WNN-PC on 2016/5/17.
 */
public class DynamicFragment extends Fragment implements View.OnClickListener {
    private ViewPager viewPager;//页卡内容
    private ImageView ivCourosr;// 动画图片
    private TextView tabFiends, tabHeightSchool, tabMe;
    private List<View> views;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View pageFriends, pageHeightSchool, pageMe;//各个页卡


    /*每个 tab 的 item*/
    private SlidingTabView slidingTabView;

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
        View view = inflater.inflate(R.layout.dynamic_fragment, null);

        initTabs(view);//初始化Tabs
        initViewPager(view);//初始化ViewPager

        return view;
    }

    /**
     * 初始化非布局的一些数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager(View v) {
        //viewpager初始化
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        views = new ArrayList<View>();
        pageFriends = View.inflate(getActivity(), R.layout.dynamic_page_friends, null);
        pageHeightSchool = View.inflate(getActivity(), R.layout.dynamic_page_height_school, null);
        pageMe = View.inflate(getActivity(), R.layout.dynamic_page_me, null);
        views.add(pageFriends);
        views.add(pageHeightSchool);
        views.add(pageMe);
        viewPager.setAdapter(new DynamicViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        //pageFriend初始化
//        final ImageView imgShowDialog = (ImageView) pageFriends.findViewById(R.id.img_show_dialog);
//        imgShowDialog.setOnClickListener(new View.OnClickListener() {
//            public ImgsDialog imgDialog;
//
//            @Override
//            public void onClick(View v) {
//                List<View> mList = new ArrayList<View>();
//                //添加page
//                for (int i = 0; i < 5; i++) {
//                    LinearLayout ll = new LinearLayout(getActivity());
//                    //开源库，可手势放大ImageView
//                    SubsamplingScaleImageView ssiv = new SubsamplingScaleImageView(getActivity());
//                    ssiv.setImage(ImageSource.resource(R.mipmap.test));
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//                    ll.addView(ssiv, params);
//                    mList.add(ll);
//                }
//                //创建全屏dialog显示大图
//                imgDialog = new ImgsDialog(getActivity(), R.style.ImgDialogFullScreenStyle, mList);
//                imgDialog.show();
//            }
//        });

        slidingTabView = (SlidingTabView) v.findViewById(R.id.sliding_tab_view);
        ListView lv = (ListView) pageFriends.findViewById(R.id.dynamic_lv_friends);
        DynamicFriendsListAdapter aflAdapter = new DynamicFriendsListAdapter(getActivity());
        lv.setAdapter(aflAdapter);
    }

    /**
     * 初始化头标
     */

    private void initTabs(View v) {
    }

    /**
     * 导航tab事件监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//            case R.id.tab_text_friends:
//                viewPager.setCurrentItem(0);
//
//                break;
//            case R.id.tab_text_heigth_school:
//                viewPager.setCurrentItem(1);
//
//                break;
//            case R.id.tab_me:
//                viewPager.setCurrentItem(2);
//
//                break;
            default:
                break;
        }
    }

    /**
     * 页面切换事件监听
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //向SlidingTabView传递值
            slidingTabView.setPositionAndOffset(position, positionOffset);
            System.out.println("positionOffset" + positionOffset);
            System.out.println("position" + position);
        }

        @Override
        public void onPageSelected(int position) {
            Toast.makeText(getActivity(), "page" + position, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
