package com.xiaohu.coursestabledemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaohu.coursestabledemo.adapters.AddCourseGvAdapter;

/**
 * Created by WNN-PC on 2016/5/30.
 */
public class CourseContentView extends RelativeLayout {
    private Context context;
    private int height;
    private int width;
    private int avgHeight;
    private int avgWidth;
    //MarkerView的宽高
    private int markerWidth;
    private int markerHeight;
    //GridView相关
    private AddCourseGvAdapter addGridAdapter;
    private GridView addCourseGV;
    private boolean[] visibilities;
    private int lastPosition;
    private int clickNums = 0;

    //课程View
    private int[][] crNums;
    private String[] courseText;
    private int culomnNum;
    private int startRowNum;
    private int endRowNum;

    public CourseContentView(Context context) {
        super(context);
    }

    public CourseContentView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CourseContentView);
        /**
         * 先添加子view:markerView
         */
        markerHeight = 16;
        markerWidth = 16;
        LayoutParams markerParams = new LayoutParams(markerWidth, markerHeight);
        visibilities = new boolean[84];
        //7*12 = 84
        for (int i = 0; i < 84; i++) {
            MarkerView mv = new MarkerView(context);
            this.addView(mv, markerParams);
            visibilities[i] = false;
        }
        /**
         * 添加一个空的GridView
         */
        RelativeLayout.LayoutParams gvParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        addCourseGV = new GridView(context);
        addCourseGV.setGravity(Gravity.CENTER);
        addCourseGV.setNumColumns(7);
        this.addView(addCourseGV, gvParams);
        addCourseGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置当前点击的按钮显示其他的隐藏
                for (int i = 0; i < 84; i++) {
                    if (i != position) {
                        visibilities[i] = false;
                    } else {
                        visibilities[position] = true;
                    }
                }

                updateGridViewLayout(visibilities);
                //点击次数自加
                clickNums++;
                if (clickNums == 1) {
                    //(1)记录第一次点击的按钮id
                    lastPosition = position;
                } else if (clickNums == 2) {
                    //(2)判断第二次点击的按钮是否是同一个按钮
                    if (lastPosition == position) {
                        //点击同一个按钮两次后键点击记录清零
                        clickNums = 0;
                        //第二次点击之后将背景隐藏并实现相应逻辑
                        visibilities[position] = false;
                        updateGridViewLayout(visibilities);
                        //TODO ...
                    } else {
                        //如果第二次点击的不是同一个按钮意味着点击了
                        //其它按钮，同时该按钮的点击次数记录是第一次
                        //并且记录下此时的按钮id,此时又回到了逻辑（2）
                        clickNums = 1;
                        lastPosition = position;
                    }
                }
            }
        });


    }

    public CourseContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 测量父布局容器
     * 默认是MATCH_PARENT模式下的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         *获取父容器的款和高、平均值：
         */
        width = this.getWidth();
        height = this.getHeight();
        avgWidth = width / 7;
        avgHeight = height / 12;
        //更新GrirdView布局，这个时候才有宽高
        updateGridViewLayout(visibilities);

    }

    /**
     * 更新GrirdView布局
     */
    private void updateGridViewLayout(boolean[] isVisibale) {
        GridView.LayoutParams itemParams = new GridView.LayoutParams(avgWidth, avgHeight);
        addGridAdapter = new AddCourseGvAdapter(context, isVisibale, itemParams);
        addCourseGV.setAdapter(addGridAdapter);
    }

    /**
     * 布局子view
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        int childCount = getChildCount();
        /**
         * markerView定位排列算法
         * 0  -- 6
         * 7  -- 13
         * 14 -- 20
         * 21 -- 27
         * ......
         */
        int left, top, right, bottom;//MarkerView坐标
        int columnNum = 0;//列号
        int rowNum = 0;//行号
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (i < 84) {

                //行号自加
                if (i % 7 == 0) {
                    columnNum++;
                }
                //列号满量程清零
                if (rowNum == 7) {
                    rowNum = 0;
                }
                //列号自加(1~7)
                rowNum++;
                //计算MarkerView位置坐标
                left = avgWidth * rowNum - markerWidth / 2;
                right = avgWidth * rowNum + markerWidth / 2;
                top = avgHeight * columnNum - markerHeight / 2;
                bottom = avgHeight * columnNum + markerHeight / 2;
                //布局MarkerView
                childView.layout(left, top, right, bottom);
            } else if (i == 84) {
                //布局GridView
                childView.layout(0, 0, width, height);
            } else if (i > 84) {
                LayoutParams params = new LayoutParams(avgWidth, (endRowNum - startRowNum) * avgHeight);
                TextView tv = (TextView) childView;
                tv.setLayoutParams(params);
                //文字内容在这里设置才会自动排序
                tv.setText(courseText[i - 85]);
                //最后+1dp/-1dp来设置间隔即间隔为2dp
                tv.layout(((crNums[i - 85][0] - 1) * avgWidth) + 1, ((crNums[i - 85][1] - 1) * avgHeight) + 1, (crNums[i - 85][0] * avgWidth) - 1, ((crNums[i - 85][2] * avgHeight)) - 1);

            }
        }


    }

    public void setRowAndCulomnNum(int[][] crNums) {
        this.crNums = crNums;
    }

    public void setCourseText(String[] args) {
        this.courseText = args;
    }

}
