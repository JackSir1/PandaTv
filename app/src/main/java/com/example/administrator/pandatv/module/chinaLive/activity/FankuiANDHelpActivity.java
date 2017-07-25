package com.example.administrator.pandatv.module.chinaLive.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveBDLBean;
import com.example.administrator.pandatv.module.chinaLive.adapter.MyLivechinaAdapter;
import com.example.administrator.pandatv.module.chinaLive.bdl.BDLChinaLiveContract;
import com.example.administrator.pandatv.module.chinaLive.fragment.AlwaysquestFra;
import com.example.administrator.pandatv.module.chinaLive.fragment.MeetquestFra;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class FankuiANDHelpActivity extends BaseActivity implements BDLChinaLiveContract.View {

    @BindView(R.id.userfankui_tablayout)
    TabLayout tablayout;
    @BindView(R.id.userfankui_viewpager)
    ViewPager viewpager;
    MyLivechinaAdapter adapter;
    @BindView(R.id.livechina_user_return)
    ImageView livechinaUserReturn;
    private List<String> mListName;
    private List<BaseFragment> fragments;

    @Override
    protected int getViewID() {
        return R.layout.livechina_userfankui;
    }

    @Override
    protected void initView() {
        mListName = new ArrayList<>();
        fragments = new ArrayList<>();

        mListName.add("遇到的问题");
        mListName.add("常见的问题");
        fragments.add(new MeetquestFra());
        fragments.add(new AlwaysquestFra());
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        adapter = new MyLivechinaAdapter(getSupportFragmentManager(), mListName, fragments);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);

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
    public void setPresenter(BDLChinaLiveContract.Presenter presenter) {

    }

    @OnClick(R.id.livechina_user_return)
    public void onClick() {
        finish();
    }
}
