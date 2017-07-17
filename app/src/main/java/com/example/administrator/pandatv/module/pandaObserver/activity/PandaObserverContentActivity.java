package com.example.administrator.pandatv.module.pandaObserver.activity;

import android.content.Intent;
import android.view.KeyEvent;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;

/**
 * Created by Administrator on 2017/7/17.
 */

public class PandaObserverContentActivity extends BaseActivity {
    @Override
    protected int getViewID() {
        return R.layout.pandaobservercontent;
    }

    @Override
    protected void initView() {
        Intent intent=getIntent();
        String url = intent.getStringExtra("url");
        String id = intent.getStringExtra("id");
        String type = intent.getStringExtra("type");
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setIntent() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            PandaObserverContentActivity.this.finish();
        }
        return false;
    }
}
