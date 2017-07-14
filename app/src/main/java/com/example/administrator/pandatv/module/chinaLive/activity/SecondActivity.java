package com.example.administrator.pandatv.module.chinaLive.activity;

import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.entity.LiveBDLBean;
import com.example.administrator.pandatv.module.chinaLive.bdl.ChinaLiveContract;

/**
 * Created by lizhuofang on 2017/7/13.
 */

public class SecondActivity extends BaseActivity implements ChinaLiveContract.View {
    private RelativeLayout mll_pop;
    private LinearLayout mll_time_layout;
    private boolean time_flag;


    @Override
    protected int getViewID() {
        return R.layout.secondactivity;
    }

    @Override
    protected void initView() {
        mll_pop = (RelativeLayout) findViewById(R.id.ll_pop);
        mll_time_layout = (LinearLayout) findViewById(R.id.time_layout);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setIntent() {

    }

    @Override
    public void onShowDialog() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void setResult(LiveBDLBean tablistBean) {

    }


    @Override
    public void setPresenter(ChinaLiveContract.Presenter presenter) {

    }

    public void by_time(View view) {
        mll_pop.setVisibility(View.VISIBLE);
        if (time_flag) {
            time_flag = !time_flag; // true
            TranslateAnimation ta = new TranslateAnimation(0, 0, 0, -94);// 设置动画的偏移位移
            ta.setDuration(500);// 设置动画的时长
            ta.setFillAfter(true);// 设置动画结束后停留在该位置
            mll_pop.startAnimation(ta);

        } else {
            if (!time_flag) { //true
                time_flag = !time_flag;
                mll_pop.setVisibility(View.VISIBLE);
                TranslateAnimation ta = new TranslateAnimation(0, 0, -211, 0);// 设置动画的偏移位移
                ta.setDuration(710);// 设置动画的时长
                ta.setFillAfter(true);// 设置动画结束后停留在该位置
                mll_pop.startAnimation(ta);
            } else {
                time_flag = !time_flag;
                TranslateAnimation ta = new TranslateAnimation(0, 0, 0, -211);// 设置动画的偏移位移
                ta.setDuration(710);
                ta.setFillAfter(true);
                mll_pop.startAnimation(ta);
            }

        }
    }
}
