package com.example.administrator.pandatv.module.chinaLive.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveBDLBean;
import com.example.administrator.pandatv.module.chinaLive.activity.LiveChinaAdd;
import com.example.administrator.pandatv.module.chinaLive.adapter.MyLivechinaAdapter;
import com.example.administrator.pandatv.module.chinaLive.bdl.BDLFragment;
import com.example.administrator.pandatv.module.chinaLive.bdl.ChinaLiveContract;
import com.example.administrator.pandatv.module.chinaLive.emeishan.EMeiShanFragment;
import com.example.administrator.pandatv.module.chinaLive.fhgc.FHGCFragment;
import com.example.administrator.pandatv.module.chinaLive.huangshan.HuangShanFragment;
import com.example.administrator.pandatv.module.chinaLive.songshan.SongShanFragment;
import com.example.administrator.pandatv.module.chinaLive.taishan.TaiShanFragment;
import com.example.administrator.pandatv.module.chinaLive.zhangjiajie.ZhangjiajieFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ChinaLiveFragment extends BaseFragment implements ChinaLiveContract.View, View.OnClickListener {


    ImageView livechinaFragmentAdd;
    ViewPager myLiveviewpager;
    TabLayout myLivetablayout;
    private MyLivechinaAdapter myLivechinaAdapter;
    private List<String> mListName;
    private List<BaseFragment> fragments;

    @Override
    protected int getViweId() {
        return R.layout.livechina_fragment;
    }

    @Override
    protected void initView(View view) {
        myLivetablayout = (TabLayout) view.findViewById(R.id.livechina_fragment_tablayout);
        myLiveviewpager = (ViewPager) view.findViewById(R.id.livechina_fragment_viewpager);
        livechinaFragmentAdd = (ImageView) view.findViewById(R.id.livechina_fragment_add);
        livechinaFragmentAdd.setOnClickListener(this);
    }

    @Override
    protected void loadDate() {
        mListName = new ArrayList<>();
        fragments = new ArrayList<>();

        mListName.add("八达岭");
        mListName.add("凤凰古城");
        mListName.add("嵩山少林寺");
        mListName.add("嵩山");
        mListName.add("峨眉山");
        mListName.add("泰山");
        mListName.add("黄山");
        mListName.add("张家界");

        fragments.add(new BDLFragment());
        fragments.add(new FHGCFragment());
        fragments.add(new SongShanFragment());
        fragments.add(new SongShanFragment());
        fragments.add(new EMeiShanFragment());
        fragments.add(new TaiShanFragment());
        fragments.add(new HuangShanFragment());
        fragments.add(new ZhangjiajieFragment());

        myLivetablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        myLivechinaAdapter = new MyLivechinaAdapter(getChildFragmentManager(), mListName, fragments);
        myLiveviewpager.setAdapter(myLivechinaAdapter);
        myLivetablayout.setupWithViewPager(myLiveviewpager);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void setPresenter(ChinaLiveContract.Presenter presenter) {
    }

    @Override
    public void onShowDialog() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void setResult(LiveBDLBean tablistBean) {

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), LiveChinaAdd.class);
        startActivity(intent);

    }
}
