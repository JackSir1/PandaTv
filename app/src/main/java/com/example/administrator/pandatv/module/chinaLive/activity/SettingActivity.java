package com.example.administrator.pandatv.module.chinaLive.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.livechina_setting_return)
    ImageView livechinaSettingReturn;
    @BindView(R.id.livechina_setting_msg)
    CheckBox livechinaSettingMsg;
    @BindView(R.id.livechina_setting_bofang)
    CheckBox livechinaSettingBofang;
    @BindView(R.id.livechina_setting_clear)
    LinearLayout livechinaSettingClear;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.livechina_setting_fankuiandhelp)
    LinearLayout livechinaSettingFankuiandhelp;
    @BindView(R.id.livechina_setting_check)
    LinearLayout livechinaSettingCheck;
    @BindView(R.id.livechina_setting_likeours)
    LinearLayout livechinaSettingLikeours;
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
        livechinaSettingMsg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        livechinaSettingBofang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    protected void setIntent() {

    }



    @OnClick({R.id.livechina_setting_return, R.id.livechina_setting_clear, R.id.livechina_setting_fankuiandhelp, R.id.livechina_setting_check, R.id.livechina_setting_likeours, R.id.livechina_setting_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.livechina_setting_return:
                finish();
                break;
            case R.id.livechina_setting_clear:
                break;
            case R.id.livechina_setting_fankuiandhelp:
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
