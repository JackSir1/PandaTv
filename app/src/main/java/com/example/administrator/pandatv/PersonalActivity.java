package com.example.administrator.pandatv;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LoginEntity;
import com.example.administrator.pandatv.model.util.ACache;
import com.example.administrator.pandatv.module.chinaLive.activity.CollectionActivity;
import com.example.administrator.pandatv.module.chinaLive.activity.HistoryActivity;
import com.example.administrator.pandatv.module.chinaLive.activity.LoginActivity;
import com.example.administrator.pandatv.module.chinaLive.activity.SettingActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/13.
 */
public class PersonalActivity extends BaseActivity {

    @BindView(R.id.personal_return_iv)
    ImageView personalReturnIv;
    @BindView(R.id.personal_login)
    ImageView personalLogin;
    @BindView(R.id.personal_login_text)
    TextView personalLoginText;
    @BindView(R.id.personal_linear_login)
    LinearLayout personalLinearLogin;
    @BindView(R.id.personal_linear_watch_history)
    LinearLayout personalLinearWatchHistory;
    @BindView(R.id.personal_linear_mineshoucang)
    LinearLayout personalLinearMineshoucang;
    @BindView(R.id.personal_linear_setting)
    LinearLayout personalLinearSetting;

    private String name;
    private String uer;
    private ACache aCache;

    @Override
    protected int getViewID() {
        return R.layout.livechina_personal;
    }

    @Override
    protected void initView() {
//        LoginEntity loginentity = (LoginEntity) aCache.getAsObject("loginentity");
//        if(loginentity!=null) {
//            personalLoginText.setText("央视网"+user_seq_id);
//        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setIntent() {

    }

    @OnClick({R.id.personal_return_iv, R.id.personal_linear_login, R.id.personal_linear_watch_history, R.id.personal_linear_mineshoucang, R.id.personal_linear_setting})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.personal_return_iv:
                finish();
                break;
            case R.id.personal_linear_login:
                aCache = ACache.get(this);
                String wbname = (String) aCache.getAsObject("wbname");
                LoginEntity loginEntity= (LoginEntity) aCache.getAsObject("loginentity");
                if(loginEntity==null&&wbname==null) {
                    personalLoginText.setText("点击登录");
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent, 100);
                }else{
                    Intent inten=new Intent(this,SuccessActivity.class);
                    inten.putExtra("nameme",loginEntity.getUser_seq_id());
//                    inten.putExtra("image",);
                    startActivityForResult(inten,150);
                }

                break;
            case R.id.personal_linear_watch_history:
                Intent intent1 = new Intent(this, HistoryActivity.class);
                startActivity(intent1);
                break;
            case R.id.personal_linear_mineshoucang:
                Intent intent2 = new Intent(this, CollectionActivity.class);
                startActivity(intent2);
                break;
            case R.id.personal_linear_setting:
                Intent intent3 = new Intent(this, SettingActivity.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 50:
                //手动登录
                uer = data.getStringExtra("names");
                personalLoginText.setText(uer);
                break;
            case 200:
                //第三方登录
//                aCache.put("wbname",name);
                name = data.getStringExtra("na");
                personalLoginText.setText(name);
                break;
            case 300:
                personalLoginText.setText("点击登录");
                Glide.with(this).load("").placeholder(R.mipmap.ic_launcher).into(personalLogin);
                break;

        }

    }

}
