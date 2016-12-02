package com.xiaohu.coursestabledemo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xiaohu.coursestabledemo.R;

/**
 * Created by WNN-PC on 2016/5/30.
 */
public class AddCourseGvAdapter extends BaseAdapter {

    private Context context;
    private GridView.LayoutParams params;
    private boolean[] bgVisibale = new boolean[]{};
    public AddCourseGvAdapter(Context context, boolean[] bgVisibale, GridView.LayoutParams params){
        this.bgVisibale = bgVisibale;
        this.context = context;
        this.params =params;
    }

    @Override
    public int getCount() {
        return bgVisibale.length;
    }

    @Override
    public Object getItem(int position) {
        return bgVisibale[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.course_grid_add_item,null);
            convertView.setLayoutParams(params);
            holder.rl = (RelativeLayout) convertView.findViewById(R.id.add_course_rl);
//            iv = (ImageView) convertView.findViewById(R.id.btn_add_course);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
//      iv.setBackgroundResource(R.mipmap.ic_main_course_add_course);
        if(bgVisibale[position]){
            holder.rl.setVisibility(View.VISIBLE);
        }else{
            holder.rl.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }
    private final class ViewHolder{
        ImageView iv;
        RelativeLayout rl;
    }
}
