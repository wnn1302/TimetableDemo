package com.xiaohu.coursestabledemo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaohu.coursestabledemo.R;

/**
 * Created by WNN-PC on 2016/6/9.
 */
public class WeeksListAdapter extends BaseAdapter {
    private String[] weeks = {"第一周", "第二周", "第三周", "第四周", "第五周", "第六周", "第七周"};
    private Context context;

    public WeeksListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return weeks.length;
    }

    @Override
    public Object getItem(int position) {
        return weeks[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvWeeks;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.weeks_listview_item, null);
            tvWeeks = (TextView) convertView.findViewById(R.id.tv_weeks);
            convertView.setTag(tvWeeks);
        } else {
            tvWeeks = (TextView) convertView.getTag();
        }
        tvWeeks.setText(weeks[position]);
        if (position == 2) {
            tvWeeks.setBackgroundResource(R.drawable.ic_dropdown_week_cur_select_bg);
            tvWeeks.setTextColor(Color.WHITE);
            tvWeeks.setText(weeks[position] + "（本周）");
        } else {
            tvWeeks.setBackgroundResource(R.drawable.btn_chooce_week_bg);
        }
        return convertView;
    }
}
