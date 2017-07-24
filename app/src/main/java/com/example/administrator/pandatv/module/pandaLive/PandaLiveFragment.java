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
import com.example.administrator.pandatv.model.entity.PandLiveTitleBean;
import com.example.administrator.pandatv.model.entity.PandaLiveBean;
import com.example.administrator.pandatv.model.util.ShowPopuUtils;
import com.example.administrator.pandatv.module.pandaLive.Pageadapter.MyAdapter;
import com.example.administrator.pandatv.module.pandaLive.Pageadapter.OnePageAdapter;
import com.example.administrator.pandatv.module.pandaLive.budpull.BudPresenter;
import com.example.administrator.pandatv.module.pandaLive.fragment.LiveFragment;
import com.example.administrator.pandatv.module.pandaLive.fragment.PandaTopFragment;
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

    private Bundle bundle;
    private PandLiveTitleBean titleBean;
    private ShowPopuUtils showPopuUtils;

    @Override
    protected int getViweId() {
        return R.layout.pandalive;
    }



    @Override
    protected void initView(View view) {
        showPopuUtils = ShowPopuUtils.getInsent().create(getContext());

        presenter.start();
        presenter.getLoadTitle();
    }
    private void fragment() {
        LiveFragment liveFragment=new LiveFragment();
        //熊猫TOP榜6
        PandaTopFragment topFragment = new PandaTopFragment();
        if(arraylist.size()>0) {
            arraylist.clear();
        }
        String[] str=new String[]{"  直播  ","精彩一刻","当熊不让","超萌滚滚秀","熊猫档案","熊猫TOP榜","熊猫那些事儿","特别节目","原创新闻"};
        arraylist.add(liveFragment);
        Bundle bundle=null;
        for(int i = 1; i <str.length ; i++) {
            topFragment = new PandaTopFragment();
            String id = titleBean.getTablist().get(i).getId();
            bundle=new Bundle();
            bundle.putString("vid",id);
            topFragment.setParams(bundle);
            arraylist.add(topFragment);
            new BudPresenter(topFragment);
        }

        new LivePresenter(liveFragment);
        pageradapter(str,pandaLivePager,tabLive,arraylist);


    }

    @Override
    protected void loadDate() {


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
    public void setParams(Bundle bundle) {

    }

    @Override
    public void pageradapter(String[] strings, ViewPager viewPager, TabLayout tab, List<Fragment> list) {

        MyAdapter pageadapter = new MyAdapter(getActivity().getSupportFragmentManager(), list, strings);
        viewPager.setAdapter(pageadapter);
        tab.setupWithViewPager(viewPager);
        tab.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    public void getLoadTitle(PandLiveTitleBean liveTitleBean) {
        titleBean=liveTitleBean;
        fragment();
        showPopuUtils.popuUtilsDismiss();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
