package com.example.administrator.pandatv.module.chinaLive.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LivechinaTabBean;
import com.example.administrator.pandatv.model.util.ACache;
import com.example.administrator.pandatv.module.chinaLive.adapter.DragAdapter;
import com.example.administrator.pandatv.module.chinaLive.adapter.MyLivechinaAdapter;
import com.example.administrator.pandatv.module.chinaLive.bdl.BDLFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ChinaLiveFragment extends BaseFragment implements ChinaLiveContract.View, View.OnClickListener,CompoundButton.OnCheckedChangeListener {


    ImageView livechinaFragmentAdd;
    ViewPager myLiveviewpager;
    TabLayout myLivetablayout;
    private MyLivechinaAdapter myLivechinaAdapter;
    private List<String> mTabListName;
    private List<BaseFragment> mList;
    private ChinaLivePresenter presenter;
    private ACache aCache;
    private PopupWindow popupWindow;
    private DragGridView gridView;
    private DragGridView gridView_other;
    private DragAdapter dragAdapter;
    private DragAdapter other_adapter;
    private CheckBox checkBox;
    private List<String> channels = new ArrayList<>();
    private List<String> channels_other = new ArrayList<>();
    private Map<String,String> tagUrlMap;

    @Override
    protected int getViweId() {
        return R.layout.livechina_fragment;
    }

    @Override
    protected void initView(View view) {
        aCache = ACache.get(getContext());
        myLivetablayout = (TabLayout) view.findViewById(R.id.livechina_fragment_tablayout);
        myLiveviewpager = (ViewPager) view.findViewById(R.id.livechina_fragment_viewpager);
        livechinaFragmentAdd = (ImageView) view.findViewById(R.id.livechina_fragment_add);
        livechinaFragmentAdd.setOnClickListener(this);
        upWopwindowo();
    }

    @Override
    protected void loadDate() {

        new ChinaLivePresenter(this);
        presenter.start();

    }

    @Override
    protected void setListener() {

    }


    @Override
    public void onClick(View v) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, null);
        View view = inflate.findViewById(R.id.main_linearLayout);
        popupWindow.showAsDropDown(view);
    }

    @Override
    public void setResult(LivechinaTabBean livechinaTabBean) {
        LivechinaTabBean livechinaTabBean1= (LivechinaTabBean) aCache.getAsObject("livechinaTabBean");
        if(livechinaTabBean1==null){
            livechinaTabBean1=livechinaTabBean;
        }

        add_Fragment(livechinaTabBean1);
        List<LivechinaTabBean.TablistBean> tablist = livechinaTabBean1.getTablist();
        List<LivechinaTabBean.AlllistBean> alllist = livechinaTabBean1.getAlllist();
        initDatatitle(tablist);
        initDataOther(alllist);

    }

    public void add_Fragment(LivechinaTabBean popupBean) {
        mTabListName = new ArrayList<>();
        mList = new ArrayList<>();
        tagUrlMap=new HashMap<>();
        List<LivechinaTabBean.TablistBean> tablist = popupBean.getTablist();
        List<LivechinaTabBean.AlllistBean> alllist = popupBean.getAlllist();
        BDLFragment badaLingFragment = null;
        Bundle bundle = null;
        for (LivechinaTabBean.TablistBean tablistBean : tablist) {
            mTabListName.add(tablistBean.getTitle());
            badaLingFragment = new BDLFragment();
            bundle = new Bundle();
            bundle.putString("url", tablistBean.getUrl());
            badaLingFragment.setParams(bundle);
            tagUrlMap.put(tablistBean.getTitle(),tablistBean.getUrl());
            mList.add(badaLingFragment);
        }
        for (LivechinaTabBean.AlllistBean alllistBean:alllist){
            tagUrlMap.put(alllistBean.getTitle(),alllistBean.getUrl());
        }
        myLivetablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        myLivechinaAdapter=new MyLivechinaAdapter(getChildFragmentManager(),mTabListName,mList);
        myLiveviewpager.setAdapter(myLivechinaAdapter);
        myLivetablayout.setupWithViewPager(myLiveviewpager);

    }

    @Override
    public void setPresenter(ChinaLiveContract.Presenter presenter) {
        this.presenter = (ChinaLivePresenter) presenter;
    }

    private void upWopwindowo() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_popup_columns, null);

        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        ImageView imageView = (ImageView) view.findViewById(R.id.fanhui);
        gridView = (DragGridView) view.findViewById(R.id.gridView_channel);
        gridView_other = (DragGridView) view.findViewById(R.id.gridView_channel_other);
        checkBox= (CheckBox) view.findViewById(R.id.licechina_add_button);
        checkBox.setOnCheckedChangeListener(this);

        gridView.setNumColumns(3);
        dragAdapter = new DragAdapter(getActivity(), channels);
        gridView.setAdapter(dragAdapter);

        other_adapter = new DragAdapter(getActivity(), channels_other);
        gridView_other.setAdapter(other_adapter);
        gridView_other.setNumColumns(3);






        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    private void initDataOther(List<LivechinaTabBean.AlllistBean> alllistBeanList) {
        for (LivechinaTabBean.AlllistBean alllistBean : alllistBeanList) {
            channels_other.add(alllistBean.getTitle());
        }

    }

    private void initDatatitle(List<LivechinaTabBean.TablistBean> tablistBeanList) {
        for (LivechinaTabBean.TablistBean alllistBean : tablistBeanList) {
            channels.add(alllistBean.getTitle());
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
        if(isChecked){
            checkBox.setText("完成");
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (isChecked && channels != null && channels.size() > 4) {
                        String channel = channels.get(position);
                        channels.remove(position);
                        channels_other.add(channel);
                        dragAdapter.notifyDataSetChanged();
                        other_adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getContext(), "个数不能小于4", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            gridView_other.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(isChecked) {
                        String channel = channels_other.get(position);
                        channels_other.remove(position);
                        channels.add(channel);
                        dragAdapter.notifyDataSetChanged();
                        other_adapter.notifyDataSetChanged();
                    }

                }
            });
        }else {
            setRefresh();
            checkBox.setText("编辑");
        }
    }
    public void setRefresh(){
        mTabListName = new ArrayList<>();
        mList = new ArrayList<>();
        BDLFragment badaLingFragment = null;
        Bundle bundle = null;
        for (String title:channels){
            String url = tagUrlMap.get(title);
            mTabListName.add(title);
            badaLingFragment = new BDLFragment();
            bundle = new Bundle();
            bundle.putString("url", url);
            badaLingFragment.setParams(bundle);
            mList.add(badaLingFragment);
        }
        myLivechinaAdapter=new MyLivechinaAdapter(getChildFragmentManager(),mTabListName,mList);
        myLiveviewpager.setAdapter(myLivechinaAdapter);
        myLivetablayout.setupWithViewPager(myLiveviewpager);
    }
    public void setSave(){
        LivechinaTabBean.TablistBean tablistBean;
        List<LivechinaTabBean.TablistBean> tablistBeanList=new ArrayList<>();
        List<LivechinaTabBean.AlllistBean> alllistBeanList=new ArrayList<>();
        LivechinaTabBean.AlllistBean alllistBean;

        LivechinaTabBean livechinaTabbean=new LivechinaTabBean();
        for (String title: channels){
            tablistBean = new LivechinaTabBean.TablistBean();
            tablistBean.setTitle(title);
            tablistBean.setUrl(tagUrlMap.get(title));
            tablistBeanList.add(tablistBean);
        }
        for (String title: channels_other){
            alllistBean = new LivechinaTabBean.AlllistBean();
            alllistBean.setUrl(tagUrlMap.get(title));
            alllistBean.setTitle(title);
            alllistBeanList.add(alllistBean);
        }
        livechinaTabbean.setAlllist(alllistBeanList);
        livechinaTabbean.setTablist(tablistBeanList);
        aCache.put("livechinaTabBean",livechinaTabbean);


    }
}