package com.example.administrator.pandatv.module.pandaLive.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.PandaLiveBean;
import com.example.administrator.pandatv.module.pandaLive.Pageadapter.MyAdapter;
import com.example.administrator.pandatv.module.pandaLive.live.LiveContract;
import com.example.administrator.pandatv.module.pandaLive.live.moresight.MoreSightPresenter;
import com.example.administrator.pandatv.viewpager.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//直播
public class LiveFragment extends BaseFragment implements LiveContract.View {
    LiveContract.Presenter presenter;
    private List<Fragment> arraylist = new ArrayList<>();
    @BindView(R.id.live_title)
    TextView liveTitle;
    @BindView(R.id.live_isshow)
    CheckBox liveIsshow;
    @BindView(R.id.live_brief)
    TextView liveBrief;
    @BindView(R.id.live_lin_brief)
    LinearLayout liveLinBrief;
    @BindView(R.id.live_back1)
    TextView liveBack1;
    @BindView(R.id.live_tab_title)
    TabLayout liveTabTitle;
    @BindView(R.id.live_twopager)
    NonSwipeableViewPager liveTwopager;
    private String title;
    private String title1;

    private void Fragment() {
        if(arraylist.size()>0) {
            arraylist.clear();
        }
        ChatFragment chatFragment = new ChatFragment();
        MoreSightFragment moreSightFragment = new MoreSightFragment();
        arraylist.add(moreSightFragment);
        arraylist.add(chatFragment);
        String[] str=new String[]{"多视角直播","边看边聊"};
        pageradapter(str,liveTwopager,liveTabTitle,arraylist);
        new MoreSightPresenter(moreSightFragment);

    }


    @Override
    protected int getViweId() {
        return R.layout.pandalive_live;
    }

    @Override
    protected void initView(View view) {
        Fragment();

    }

    @Override
    protected void loadDate() {
        presenter.start();
    }

    @Override
    protected void setListener() {
        liveIsshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkbox(isChecked);
            }
        });
    }


    @Override
    public void showProgresDialog() {

    }

    @Override
    public void dismeissDiolog() {

    }

    @Override
    public void setResult(PandaLiveBean pandaLiveBean) {
        liveTitle.setText("[正在直播]" + pandaLiveBean.getLive().get(0).getTitle());
        liveBrief.setText(pandaLiveBean.getLive().get(0).getBrief());
        //多视角直播
        title = pandaLiveBean.getBookmark().getMultiple().get(0).getTitle()+"";
        //边看边聊
        title1 = pandaLiveBean.getBookmark().getWatchTalk().get(0).getTitle()+"";


    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void checkbox(Boolean isChecked) {

        if (isChecked == true) {
            liveLinBrief.setVisibility(View.VISIBLE);
            liveBack1.setVisibility(View.VISIBLE);

        } else if (isChecked == false) {
            liveLinBrief.setVisibility(View.GONE);
            liveBack1.setVisibility(View.GONE);
        }
    }

    @Override
    public void pageradapter(String[] strings, ViewPager viewPager, TabLayout tab, List<Fragment> list) {
        MyAdapter pageadapter = new MyAdapter(getActivity().getSupportFragmentManager(), list, strings);
        viewPager.setAdapter(pageadapter);
        tab.setupWithViewPager(viewPager);
        tab.setTabMode(TabLayout.MODE_FIXED);
    }


    @Override
    public void setPresenter(LiveContract.Presenter presenter) {
        this.presenter = presenter;
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
