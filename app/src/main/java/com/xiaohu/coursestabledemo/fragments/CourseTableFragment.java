package com.xiaohu.coursestabledemo.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaohu.coursestabledemo.R;
import com.xiaohu.coursestabledemo.adapters.WeeksListAdapter;
import com.xiaohu.coursestabledemo.views.CourseContentView;


/**
 * Created by WNN-PC on 2016/5/17.
 */
public class CourseTableFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private TextView tvShowWeeksList;
    private PopupWindow weeksListPpw;

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
        View view = inflater.inflate(R.layout.course_table_fragment, null);

        /**
         * 添加courseView
         *
         * 设置courseView宽高（根据节数）
         */
//        CourseContentView crl = (CourseContentView) view.findViewById(R.id.course_content_rl);
        CourseContentView ccv = (CourseContentView) view.findViewById(R.id.add_course_rl);
        int[] bgIds = {R.drawable.ic_course_bg_bohelv, R.drawable.ic_course_bg_lan, R.drawable.ic_course_bg_tao, R.drawable.ic_course_bg_zi, R.drawable.ic_course_bg_tuhuang, R.drawable.ic_course_bg_bohelv};
        for (int i = 0; i < 6; i++) {
            TextView courseView = new TextView(getActivity());
//            courseView.setText("电机及应用技术@C3-503");
//            courseView.setSingleLine(true);
//            courseView.setEllipsize(TextUtils.TruncateAt.END);
            courseView.setBackgroundResource(bgIds[i]);
            courseView.setGravity(Gravity.CENTER);
            courseView.setTextColor(Color.WHITE);
            courseView.setTextSize(12);
//        crl.setRowAndCulomnNum(1, 2, 8);
            ccv.addView(courseView);
            final int finalI = i;
            courseView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), finalI + "click", Toast.LENGTH_SHORT).show();
                }
            });
        }
        //参数：周数、开始节数、结束节数
        int[][] crNums = {{1, 1, 2}, {2, 1, 2}, {3, 1, 2},
                {4, 1, 2}, {5, 1, 2}, {2, 3, 4}};
        String[] courseTexts = {"数字信号处理@C3-503", "电机及应用技术@C3-503", "新技术专题II@C3-502",
                "自动控制原理@北教4#-503", "电力电子技术@C3-402", "自动控制原理@北教4#-503"};
        ccv.setRowAndCulomnNum(crNums);
        ccv.setCourseText(courseTexts);
        /**
         * popupWindow部分
         */
        View contentView = View.inflate(getActivity(), R.layout.week_list_dropwindow, null);
        weeksListPpw = new PopupWindow(contentView);
        weeksListPpw.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        weeksListPpw.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        weeksListPpw.setFocusable(true);
        weeksListPpw.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸

        // 这句话必须有，否则
        // 按返回键
        // popwindow不能消失
        weeksListPpw.setBackgroundDrawable(new BitmapDrawable(getResources()));
        tvShowWeeksList = (TextView) view.findViewById(R.id.title_select_week);
        tvShowWeeksList.setOnClickListener(this);
        contentView.setOnTouchListener(this);

        //ppw里的ListView
        final ListView ppwListView = (ListView) contentView.findViewById(R.id.weeks_list);
        ppwListView.setDividerHeight(0);//隐藏分隔线
        WeeksListAdapter wlAdapter = new WeeksListAdapter(getActivity());
        ppwListView.setAdapter(wlAdapter);
        ppwListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }

    /**
     * 用于数据的获取和初始化
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate");
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }

    //    @Override
//    public void onStart() {
//        System.out.println("onStart");
//        super.onStart();
//    }
//
//    @Override
//    public void onResume() {
//        System.out.println("onResume");
//        super.onResume();
//    }

    @Override
    public void onPause() {
        System.out.println("onPause");
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_select_week:
                if (weeksListPpw.isShowing()) {
                    weeksListPpw.dismiss();
                } else {
                    weeksListPpw.showAsDropDown(tvShowWeeksList);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            //设置外部点击关闭window
            case R.id.ppw_layout:
                if (weeksListPpw.isShowing()) {
                    weeksListPpw.dismiss();
                }
                break;
        }
        return false;
    }


//    @Override
//    public void onStop() {
//        System.out.println("onStop");
//        super.onStop();
//    }
//
//    @Override
//    public void onDestroy() {
//        System.out.println("onDestroy");
//        super.onDestroy();
//    }
}
