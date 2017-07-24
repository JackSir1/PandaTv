package com.example.administrator.pandatv.module.chinaLive.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class CollectionActivity extends BaseActivity {


    @BindView(R.id.collection_iv)
    ImageView collectionIv;
    @BindView(R.id.livechina_coll_tab)
    TabLayout livechinaCollTab;

    @Override
    protected int getViewID() {
        return R.layout.livechina_collection;
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

    @OnClick(R.id.collection_iv)
    public void onClick() {
    }
}
