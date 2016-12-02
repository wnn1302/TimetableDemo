package com.xiaohu.coursestabledemo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xiaohu.coursestabledemo.R;

/**
 * Created by WNN-PC on 2016/6/11.
 */
public class DynamicFriendsListAdapter extends BaseAdapter {
    private Context context;

    public DynamicFriendsListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.dynamic_friends_list_item, null);
        } else {
        }
        return convertView;
    }
}
