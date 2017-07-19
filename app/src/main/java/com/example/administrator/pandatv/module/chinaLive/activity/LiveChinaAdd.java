package com.example.administrator.pandatv.module.chinaLive.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LivechinaTabBean;
import com.example.administrator.pandatv.model.util.ACache;
import com.example.administrator.pandatv.module.chinaLive.adapter.DragAdapter;
import com.example.administrator.pandatv.module.chinaLive.fragment.DragGridView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lizhuofang on 2017/7/13.
 */
public class LiveChinaAdd extends BaseActivity {

    private RelativeLayout relativeLayout;

    private List<String> channels = new ArrayList<>();
    private List<String> channels_other = new ArrayList<>();
    private DragGridView gridView;
    private DragGridView gridView_other;
    private DragAdapter dragAdapter;
    private DragAdapter other_adapter;
    private CheckBox button;
    private ImageView mImae;
    private ACache aCache;
    private LivechinaTabBean livechinaTabBean;

    @Override
    protected int getViewID() {
        return R.layout.activity_popup_columns;
    }

    @Override
    protected void initView() {


        aCache = ACache.get(App.content);

        livechinaTabBean = (LivechinaTabBean) aCache.getAsObject("livechinaTabBean");

        gridView = (DragGridView) findViewById(R.id.gridView_channel);
        gridView_other = (DragGridView) findViewById(R.id.gridView_channel_other);
        button = (CheckBox) findViewById(R.id.licechina_add_button);
        mImae = (ImageView) findViewById(R.id.catefpry_item_image);
    }

    @Override
    protected void setListener() {

        add_list();
        gridView.setNumColumns(3);
        dragAdapter = new DragAdapter(this, channels);
        gridView.setAdapter(dragAdapter);

        other_adapter = new DragAdapter(this, channels_other);
        gridView_other.setAdapter(other_adapter);
        gridView_other.setNumColumns(3);

        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    button.setText("完成");
                    mImae.setVisibility(View.VISIBLE);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String channel = channels.get(position);
                            channels.remove(position);
                            channels_other.add(channel);
                            refreshList();

                            dragAdapter.notifyDataSetChanged();
                            other_adapter.notifyDataSetChanged();
                        }
                    });
                    gridView_other.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String channel = channels_other.get(position);
                            channels_other.remove(position);
                            channels.add(channel);
                            refreshList();
                            dragAdapter.notifyDataSetChanged();
                            other_adapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    button.setText("编辑");
                    mImae.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void setIntent() {

    }

    public void refreshList() {
        LivechinaTabBean livechinaTabBean1=new LivechinaTabBean();
        List<LivechinaTabBean.AlllistBean> alllist = new ArrayList<>();
        List<LivechinaTabBean.TablistBean> tablist = new ArrayList<>();
        LivechinaTabBean.TablistBean tablistBean=null;
        LivechinaTabBean.AlllistBean alllistBean1=null;
        for (String title:channels){
            for (LivechinaTabBean.AlllistBean alllistBean:livechinaTabBean.getAlllist()){
                if(alllistBean.getTitle().equals(title)) {
                    tablistBean=new LivechinaTabBean.TablistBean();
                    tablistBean.setOrder(alllistBean.getOrder());
                    tablistBean.setTitle(alllistBean.getTitle());
                    tablistBean.setType(alllistBean.getType());
                    tablistBean.setUrl(alllistBean.getUrl());
                    tablist.add(tablistBean);

                }else {
                    alllistBean1=new LivechinaTabBean.AlllistBean();
                    alllistBean1.setOrder(alllistBean.getOrder());
                    alllistBean1.setTitle(alllistBean.getTitle());
                    alllistBean1.setType(alllistBean.getType());
                    alllistBean1.setUrl(alllistBean.getUrl());
                    alllist.add(alllistBean1);
                }
            }
            for (LivechinaTabBean.TablistBean tablistBean1:livechinaTabBean.getTablist()){
                if(tablistBean1.getTitle().equals(title)) {

                    tablistBean=new LivechinaTabBean.TablistBean();
                    tablistBean.setOrder(tablistBean1.getOrder());
                    tablistBean.setTitle(tablistBean1.getTitle());
                    tablistBean.setType(tablistBean1.getType());
                    tablistBean.setUrl(tablistBean1.getUrl());
                    tablist.add(tablistBean1);

                }else {
                    alllistBean1=new LivechinaTabBean.AlllistBean();
                    alllistBean1.setOrder(tablistBean1.getOrder());
                    alllistBean1.setTitle(tablistBean1.getTitle());
                    alllistBean1.setType(tablistBean1.getType());
                    alllistBean1.setUrl(tablistBean1.getUrl());
                    alllist.add(alllistBean1);
                }
            }
            livechinaTabBean1.setTablist(tablist);
            livechinaTabBean1.setAlllist(alllist);
        }

        aCache.put("livechinaTabBean",livechinaTabBean1);
    }

    public void add_list() {
        List<LivechinaTabBean.TablistBean> tablist = livechinaTabBean.getTablist();
        for (LivechinaTabBean.TablistBean tablistBean : tablist) {
            channels.add(tablistBean.getTitle());
        }
        List<LivechinaTabBean.AlllistBean> alllist = livechinaTabBean.getAlllist();
        for (LivechinaTabBean.AlllistBean alllistBean : alllist) {
            channels_other.add(alllistBean.getTitle());
        }
    }
}
