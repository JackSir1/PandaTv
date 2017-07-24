package com.example.administrator.pandatv.module.chinaLive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class SettingActivity extends BaseActivity {


    @BindView(R.id.livechina_setting_return)
    ImageView livechinaSettingReturn;
    @BindView(R.id.livechina_setting_msg)
    ImageView livechinaSettingMsg;
    @BindView(R.id.livechina_setting_bofang)
    ImageView livechinaSettingBofang;
    @BindView(R.id.livechina_setting_clear)
    LinearLayout livechinaSettingClear;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.livechina_setting_help)
    LinearLayout livechinaSettingHelp;
    @BindView(R.id.livechina_setting_check)
    LinearLayout livechinaSettingCheck;
    @BindView(R.id.livechina_setting_likeours)
    ImageView livechinaSettingLikeours;
    @BindView(R.id.livechina_setting_about)
    LinearLayout livechinaSettingAbout;

    @Override
    protected int getViewID() {
        return R.layout.livechina_setting;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setIntent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.livechina_setting_return,R.id.livechina_setting_msg,R.id.livechina_setting_bofang, R.id.livechina_setting_clear, R.id.livechina_setting_help, R.id.livechina_setting_check, R.id.livechina_setting_likeours, R.id.livechina_setting_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.livechina_setting_return:
                finish();
                break;
            case R.id.livechina_setting_msg:
                finish();
                break;
            case R.id.livechina_setting_bofang:
                finish();
                break;
            case R.id.livechina_setting_clear:
                textView2.setText("0MB");
                break;
            case R.id.livechina_setting_help:
                Intent intent = new Intent(this, FankuiANDHelpActivity.class);
                startActivity(intent);
                break;
            case R.id.livechina_setting_check:

                break;
            case R.id.livechina_setting_likeours:
                Intent intent2 = new Intent(this, LikeoursActivity.class);
                startActivity(intent2);
                break;
            case R.id.livechina_setting_about:
                Intent intent3 = new Intent(this, AboutActivity.class);
                startActivity(intent3);
                break;
        }
    }

}
