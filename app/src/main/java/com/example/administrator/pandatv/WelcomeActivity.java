package com.example.administrator.pandatv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/12.
 */

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.welcome_image)
    ImageView welcomeImage;
    private Boolean isFirst = true;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_maian);
        ButterKnife.bind(this);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        sharedPreferences = getSharedPreferences("start", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        isFirst = sharedPreferences.getBoolean("isFirst", true);

        init();

    }

    public void init() {
        if (isFirst) {

            edit.putBoolean("isFirst", false);
            edit.commit();

            handler.sendEmptyMessageDelayed(100, 2000);

        } else {
            handler.sendEmptyMessageDelayed(200, 2000);
        }


    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    Intent intent1 = new Intent(WelcomeActivity.this, StartActivity.class);
                    startActivity(intent1);
                    break;
                case 200:
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(200);
        handler.removeMessages(100);
    }
}
