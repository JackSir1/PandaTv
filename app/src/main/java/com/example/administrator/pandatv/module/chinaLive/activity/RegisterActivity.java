package com.example.administrator.pandatv.module.chinaLive.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.module.chinaLive.adapter.MyLivechinaAdapter;
import com.example.administrator.pandatv.module.chinaLive.fragment.ShouJiRegisterFrag;
import com.example.administrator.pandatv.module.chinaLive.fragment.YouXiangRegiFrag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.login_return_imageView)
    ImageView loginReturnImageView;
    @BindView(R.id.userfankui_viewpager)
    ViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    MyLivechinaAdapter adapter;
    private List<String> mListName;
    private List<BaseFragment> fragments;



    @Override
    protected int getViewID() {
        return R.layout.livechina_register;
    }

    @Override
    protected void initView() {
        App.activity=this;
        mListName = new ArrayList<>();
        fragments = new ArrayList<>();

        mListName.add("手机注册");
        mListName.add("邮箱注册");
        fragments.add(new ShouJiRegisterFrag());
        fragments.add(new YouXiangRegiFrag());
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        adapter = new MyLivechinaAdapter(getSupportFragmentManager(), mListName,fragments);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);

    }
    @Override
    protected void setListener() {

    }

    @Override
    protected void setIntent() {

    }

    @OnClick(R.id.login_return_imageView)
    public void onClick() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
