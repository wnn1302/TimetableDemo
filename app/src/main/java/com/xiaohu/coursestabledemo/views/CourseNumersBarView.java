package com.xiaohu.coursestabledemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaohu.coursestabledemo.R;

/**
 * Created by WNN-PC on 2016/5/29.
 */
public class CourseNumersBarView extends LinearLayout {


    public CourseNumersBarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setOrientation(HORIZONTAL);

        LinearLayout ll = new LinearLayout(context);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        ll.setOrientation(VERTICAL);
        this.addView(ll,llParams);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CourseNumbersBarView);
        float textSzie = ta.getDimension(R.styleable.CourseNumbersBarView_numTextSize, 0);
        int textViewWitdh = (int) ta.getDimension(R.styleable.CourseNumbersBarView_numTextWidth, 0);
        int textViewHeight = (int) ta.getDimension(R.styleable.CourseNumbersBarView_numTextHeight, 0);
        int textColor = ta.getColor(R.styleable.CourseNumbersBarView_numTextColor, 0);
//        int colorDarkBlue = getResources().getColor(R.color.colorDarkBlue);
        int colorGray = getResources().getColor(R.color.colorGray);

        LayoutParams tvParams = new LayoutParams(textViewWitdh, textViewHeight);
        LayoutParams tvRowLineParams = new LayoutParams(textViewWitdh, 1);

        for (int i = 0; i < 12; i++) {

            TextView tv = new TextView(context);
            TextView tvLine = new TextView(context);

            tv.setTextSize(textSzie);
            tv.setTextColor(textColor);
            tv.setGravity(Gravity.CENTER);
            tv.setText("" + (++i));
            i--;

            tvLine.setBackgroundColor(colorGray);

            ll.addView(tv, tvParams);
            ll.addView(tvLine, tvRowLineParams);
        }

        View culomnLine = new View(context);
        culomnLine.setBackgroundColor(colorGray);
        LayoutParams tclParams = new LayoutParams(1,LayoutParams.MATCH_PARENT);
        this.addView(culomnLine,tclParams);
    }

}
