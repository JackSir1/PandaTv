package com.example.administrator.pandatv.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.net.HttpFactory;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/7/11.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewID());
        ButterKnife.bind(this);
        App.iHttp = new HttpFactory().create();
        initView();
        setListener();
        setIntent();
    }

    protected abstract int getViewID();

    protected abstract void initView();

    protected abstract void setListener();

    protected abstract void setIntent();


}
