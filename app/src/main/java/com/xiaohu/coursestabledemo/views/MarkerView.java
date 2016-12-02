package com.xiaohu.coursestabledemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.xiaohu.coursestabledemo.R;

/**
 * Created by WNN-PC on 2016/5/29.
 * 十字小标记
 */
public class MarkerView extends View {

    private Paint mPaint;

    public MarkerView(Context context) {

        super(context);
        int colorGray = getResources().getColor(R.color.colorGray);
        mPaint = new Paint();
        mPaint.setColor(colorGray);
        mPaint.setStrokeWidth(2.5f);//设置画笔宽度

    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("=========>onDraw<===========");
        canvas.drawLine(8, 0, 8, 16, mPaint);
        canvas.drawLine(0, 8, 16, 8, mPaint);

    }
}
