package com.xiaohu.coursestabledemo.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xiaohu.coursestabledemo.R;
import com.xiaohu.coursestabledemo.fragments.BagFragment;
import com.xiaohu.coursestabledemo.fragments.CourseTableFragment;
import com.xiaohu.coursestabledemo.fragments.DynamicFragment;
import com.xiaohu.coursestabledemo.fragments.MeFragment;


public class MainActivity extends Activity implements View.OnClickListener {
    //view 相关
    private Button btnCourse;
    private Button btnDynamic;
    private Button btnBag;
    private Button btnMe;
    //fragment 相关
    private FragmentTransaction fmTransaction;//事务
    private FragmentManager fmManager;//fragment管理器
    private Fragment courseFragm;
    private Fragment dynamicFragm;
    private Fragment bagFragm;
    private Fragment meFragm;
    private int fmState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 拿到FragmentManager
        fmManager = getFragmentManager();
        //初始化布局：
        initView();
        //默认显示课表为第一个
        showFragment(1);

    }

    /**
     * 初始化布局
     */
    private void initView() {

        btnCourse = (Button) findViewById(R.id.btn_table);
        btnDynamic = (Button) findViewById(R.id.btn_news);
        btnBag = (Button) findViewById(R.id.btn_bag);
        btnMe = (Button) findViewById(R.id.btn_me);

        btnCourse.setOnClickListener(this);
        btnDynamic.setOnClickListener(this);
        btnBag.setOnClickListener(this);
        btnMe.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_table:
//                showToast("click table");
                showFragment(1);
                break;
            case R.id.btn_news:
//                showToast("click news");
                showFragment(2);
                break;
            case R.id.btn_bag:
//                showToast("click bag");
                showFragment(3);
                break;
            case R.id.btn_me:
//                showToast("click me");
                showFragment(4);
                break;
            default:
                break;
        }
    }

    private void showToast(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 切换fragment
     *
     * @param index
     */

    public void showFragment(int index) {
        //记录当前显示的fragment
        fmState = index;

        //事务要在用到的时候开启
        fmTransaction = fmManager.beginTransaction();

        // 想要显示一个fragment,先隐藏所有fragment，防止重叠
        hideFragments(fmTransaction);

        switch (index) {
            case 1:
                // 如果fragment1已经存在则将其显示出来
                if (courseFragm != null)
                    fmTransaction.show(courseFragm);
                    // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                else {
                    courseFragm = new CourseTableFragment();
                    fmTransaction.add(R.id.container, courseFragm);
                }
                break;
            case 2:
                if (dynamicFragm != null)
                    fmTransaction.show(dynamicFragm);
                else {
                    dynamicFragm = new DynamicFragment();
                    fmTransaction.add(R.id.container, dynamicFragm);
                }
                break;
            case 3:
                if (bagFragm != null)
                    fmTransaction.show(bagFragm);
                else {
                    bagFragm = new BagFragment();
                    fmTransaction.add(R.id.container, bagFragm);
                }
                break;
            case 4:
                if (meFragm != null)
                    fmTransaction.show(meFragm);
                else {
                    meFragm = new MeFragment();
                    fmTransaction.add(R.id.container, meFragm);
                }
                break;
        }
        fmTransaction.commit();
    }

    /**
     * 当fragment已被实例化，就隐藏起来
     */
    public void hideFragments(FragmentTransaction ft) {
        if (courseFragm != null)
            ft.hide(courseFragm);
        if (dynamicFragm != null)
            ft.hide(dynamicFragm);
        if (bagFragm != null)
            ft.hide(bagFragm);
        if (meFragm != null)
            ft.hide(meFragm);
    }

    /**
     * 解决内存不足系统回收Activity之后，
     * fragment没有被销毁，当再次返回时发生重叠的问题
     * Activity中的onSaveInstanceState()里面有一句
     * super.onRestoreInstanceState(savedInstanceState)，
     * Google对于这句话的解释是
     * “Always call the superclass so it can save the view hierarchy state”，
     * 大概意思是“总是执行这句代码来调用父类去保存视图层的状态”。
     * 就是因为这句话导致了重影的出现，于是删除了这句话，
     * 然后onCreate()与onRestoreInstanceState()中创建Fragment，然后再通过保存切换的状态
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        fmState = savedInstanceState.getInt("fmState");
        showFragment(fmState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //记录当前的position
        outState.putInt("fmState", fmState);
    }
}
