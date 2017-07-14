package com.example.administrator.pandatv.module.pandaLive;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.PandaLiveBean;
import com.example.administrator.pandatv.module.pandaLive.Pageadapter.MyAdapter;
import com.example.administrator.pandatv.module.pandaLive.Pageadapter.OnePageAdapter;
import com.example.administrator.pandatv.module.pandaLive.budpull.BudPresenter;
import com.example.administrator.pandatv.module.pandaLive.fragment.BudRullFragment;
import com.example.administrator.pandatv.module.pandaLive.fragment.LiveFragment;
import com.example.administrator.pandatv.module.pandaLive.fragment.OriginalFragment;
import com.example.administrator.pandatv.module.pandaLive.fragment.PandaRecoadFragment;
import com.example.administrator.pandatv.module.pandaLive.fragment.PandaThingsFragment;
import com.example.administrator.pandatv.module.pandaLive.fragment.PandaTopFragment;
import com.example.administrator.pandatv.module.pandaLive.fragment.PandabarFragment;
import com.example.administrator.pandatv.module.pandaLive.fragment.SpecialslFragment;
import com.example.administrator.pandatv.module.pandaLive.fragment.SplendidFragment;
import com.example.administrator.pandatv.module.pandaLive.live.LivePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/12.
 */

public class PandaLiveFragment extends BaseFragment implements PandaLiveContract.View {
    PandaLiveContract.Presenter presenter;
    List<Fragment> arraylist = new ArrayList<Fragment>();
    @BindView(R.id.tab_live)
    TabLayout tabLive;
    @BindView(R.id.pandaLive_pager)
    ViewPager pandaLivePager;
    private OnePageAdapter pageAdapter;


    @Override
    protected int getViweId() {
        return R.layout.pandalive;
    }

    private void fragment() {
        //直播1
        LiveFragment liveFragment = new LiveFragment();
        //超萌滚滚秀2
        BudRullFragment budRullFragment = new BudRullFragment();
        //原创新闻3
        OriginalFragment originalFragment = new OriginalFragment();
        //当熊不让4
        PandabarFragment pandabarFragment = new PandabarFragment();
        //熊猫那些事儿5
        PandaThingsFragment pandaThingsFragment = new PandaThingsFragment();
        //熊猫TOP榜6
        PandaTopFragment topFragment = new PandaTopFragment();
        //特别节目7
        SpecialslFragment specialslFragment = new SpecialslFragment();
        //精彩一刻8
        SplendidFragment splendidFragment = new SplendidFragment();
        //熊猫档案9
        if(arraylist.size()>0) {
            arraylist.clear();
        }
        String[] str=new String[]{"  直播  ","精彩一刻","当熊不让","超萌滚滚秀","熊猫档案","熊猫TOP榜","熊猫那些事儿","特别节目","原创新闻"};

        PandaRecoadFragment pandaRecoadFragment = new PandaRecoadFragment();
        arraylist.add(liveFragment);
        for(int i = 0; i <str.length-2 ; i++) {
            topFragment = new PandaTopFragment();
             arraylist.add(topFragment);
            new BudPresenter(topFragment);
        }

        new LivePresenter(liveFragment);
        pageradapter(str,pandaLivePager,tabLive,arraylist);

    }

    @Override
    protected void initView(View view) {
        fragment();
    }

    @Override
    protected void loadDate() {

        presenter.start();


    }

    @Override
    protected void setListener() {


    }

    @Override
    public void setPresenter(PandaLiveContract.Presenter presenter) {

        this.presenter = presenter;
    }

    @Override
    public void showProgresDialog() {

    }

    @Override
    public void dismeissDiolog() {

    }

    @Override
    public void setResult(PandaLiveBean pandaLiveBean) {


    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void checkbox(Boolean isChecked) {

    }

    @Override
    public void pageradapter(String[] strings, ViewPager viewPager, TabLayout tab, List<Fragment> list) {

        MyAdapter pageadapter = new MyAdapter(getActivity().getSupportFragmentManager(), list, strings);
        viewPager.setAdapter(pageadapter);
        tab.setupWithViewPager(viewPager);
        tab.setTabMode(TabLayout.MODE_FIXED);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
