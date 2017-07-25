package com.example.administrator.pandatv.module.chinaLive.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.module.chinaLive.adapter.MyLivechinaAdapter;
import com.example.administrator.pandatv.module.chinaLive.fragment.LiveFra;
import com.example.administrator.pandatv.module.chinaLive.fragment.jcFra;

import java.util.ArrayList;
import java.util.List;

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
    MyLivechinaAdapter adapter;
    @BindView(R.id.collection_viewpager)
    ViewPager collectionViewpager;
    private List<String> mListName;
    private List<BaseFragment> fragments;

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
        mListName = new ArrayList<>();
        fragments = new ArrayList<>();

        mListName.add("直播");
        mListName.add("精彩看点");
        fragments.add(new LiveFra());
        fragments.add(new jcFra());
        livechinaCollTab.setTabMode(TabLayout.MODE_FIXED);
        adapter = new MyLivechinaAdapter(getSupportFragmentManager(), mListName, fragments);
        collectionViewpager.setAdapter(adapter);
        livechinaCollTab.setupWithViewPager(collectionViewpager);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.collection_iv)
    public void onClick() {
        finish();
    }
}
