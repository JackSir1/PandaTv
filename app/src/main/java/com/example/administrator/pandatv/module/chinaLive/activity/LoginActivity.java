package com.example.administrator.pandatv.module.chinaLive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_return_imageView)
    ImageView loginReturnImageView;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_radiobutton_wenxin)
    RadioButton loginRadiobuttonWenxin;
    @BindView(R.id.login_radiobutton_qq)
    RadioButton loginRadiobuttonQq;
    @BindView(R.id.login_radiobutton_sina)
    RadioButton loginRadiobuttonSina;
    @BindView(R.id.login_radiogroup)
    RadioGroup loginRadiogroup;
    @BindView(R.id.login_edit_number)
    EditText loginEditNumber;
    @BindView(R.id.login_edit_password)
    EditText loginEditPassword;
    @BindView(R.id.livechina_wangjimima)
    TextView livechinaWangjimima;
    @BindView(R.id.login_button)
    Button loginButton;

    @Override
    protected int getViewID() {
        return R.layout.livechina_login;
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

    @OnClick({R.id.login_return_imageView, R.id.login_register, R.id.login_edit_number, R.id.login_edit_password, R.id.livechina_wangjimima, R.id.login_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_return_imageView:
                finish();
                break;
            case R.id.login_register:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_edit_number:
                break;
            case R.id.login_edit_password:
                break;
            case R.id.livechina_wangjimima:
                Intent intent1=new Intent(this,WJMMActivity.class);
                startActivity(intent1);
                break;
            case R.id.login_button:
                break;
        }
    }
}
