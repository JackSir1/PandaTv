package com.example.administrator.pandatv.module.chinaLive.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.administrator.pandatv.model.util.ACache;
import com.example.administrator.pandatv.model.util.ShowPopuUtils;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

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
    private OkHttpClient client=new OkHttpClient.Builder().build();
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.SINA};
    private ShowPopuUtils dialog;
    private String usrid;
    private ACache aCache;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livechina_login);
        ButterKnife.bind(this);
        aCache = ACache.get(this);
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        loginRadiogroup = (RadioGroup) findViewById(R.id.login_radiogroup);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorBlue));

        }
        initPlatforms();
        loginRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.login_radiobutton_wenxin:
                        break;
                    case R.id.login_radiobutton_qq:
                        dialog = ShowPopuUtils.getInsent().create(LoginActivity.this);
                        UMShareAPI.get(LoginActivity.this).doOauthVerify(LoginActivity.this, platforms.get(0).mPlatform, authListener);
                        finish();
                        break;
                    case R.id.login_radiobutton_sina:
                        dialog = ShowPopuUtils.getInsent().create(LoginActivity.this);
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
                final Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
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
                            ACache aCache=ACache.get(LoginActivity.this);
                            aCache.put("loginentity",  loginEntity);
                            String errMsg = loginEntity.getErrMsg();
                            if(errMsg.equals("成功")) {
                                usrid = loginEntity.getUser_seq_id();
                               Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            }else{
                               Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                           }
                            Intent intent2=getIntent();
                            intent2.putExtra("names","央视网"+usrid);
                            setResult(50,intent2);
                            finish();
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
            ShowPopuUtils.getInsent().create(LoginActivity.this);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
           dialog.popuUtilsDismiss();
//            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Set<String> keyset = data.keySet();
            String str=null;
            for (String string:keyset){
                str=data.get(string);
            }
            String name=data.get("name");
            Intent intent=getIntent();
            intent.putExtra("na",name);
            aCache.put("wbname",name);
            setResult(200,intent);
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            dialog.popuUtilsDismiss();
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            dialog.popuUtilsDismiss();
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @OnClick(R.id.livechina_wangjimimaa)
    public void onClick() {
        Intent intent=new Intent(this,WJMMActivity.class);
        startActivity(intent);
    }

}
