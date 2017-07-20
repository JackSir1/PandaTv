package com.example.administrator.pandatv.module.chinaLive.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LoginEntity;
import com.example.administrator.pandatv.module.chinaLive.adapter.AuthAdapter;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class LoginActivity extends Activity {
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
    RadioGroup loginRadiogroup;
    @BindView(R.id.login_edit_number)
    EditText loginEditNumber;
    @BindView(R.id.login_edit_password)
    EditText loginEditPassword;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.livechina_wangjimimaa)
    TextView livechinaWangjimima;
    private AuthAdapter shareAdapter;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.SINA};
    private ProgressDialog dialog;
//    private
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livechina_login);
        ButterKnife.bind(this);
        loginRadiogroup = (RadioGroup) findViewById(R.id.login_radiogroup);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorBlue));

        }
        dialog = new ProgressDialog(this);
        initPlatforms();
        loginRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.login_radiobutton_wenxin:
                        break;
                    case R.id.login_radiobutton_qq:
                        UMShareAPI.get(LoginActivity.this).doOauthVerify(LoginActivity.this, platforms.get(0).mPlatform, authListener);
                        finish();
                        break;
                    case R.id.login_radiobutton_sina:
                        UMShareAPI.get(LoginActivity.this).doOauthVerify(LoginActivity.this, platforms.get(1).mPlatform, authListener);
                        break;
                }
            }
        });

    }

    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }


    @OnClick({R.id.login_return_imageView, R.id.login_register,R.id.livechina_wangjimimaa, R.id.login_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_return_imageView:
                finish();
                break;
            case R.id.login_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.livechina_wangjimimaa:
                Intent intent1 = new Intent(this, WJMMActivity.class);
                startActivity(intent1);
                break;
            case R.id.login_button:
                String phone = loginEditNumber.getText().toString().trim();
                String pass = loginEditPassword.getText().toString().trim();
                if(!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(pass)) {
                    IChinaLiveModel inchina=new ChinaLiveModel();
                    inchina.getLogin(phone, pass, new MyNetCallBack<LoginEntity>() {
                        @Override
                        public void onSuccess(LoginEntity loginEntity) {
                            String errType =loginEntity.getErrType();
                            if(errType.equals(0)) {

                            }else{

                            }
                        }

                        @Override
                        public void onError(String error) {

                        }
                    });
                }
                
                
                break;
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
//            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Set<String> keyset = data.keySet();
            String str=null;
            for (String string:keyset){
                str=data.get(string);
            }
            String name=data.get("name");
            Intent intent=getIntent();
            intent.putExtra("na",name);
            setResult(50,intent);
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @OnClick(R.id.livechina_wangjimimaa)
    public void onClick() {
        Intent intent=new Intent(this,WJMMActivity.class);
        startActivity(intent);
    }
}
