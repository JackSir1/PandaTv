package com.example.administrator.pandatv.module.pandaLive.live;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.PandaLiveBean;

import java.util.List;

public interface LiveContract {
    interface View extends BaseView<Presenter>{
        void showProgresDialog();
        void dismeissDiolog();
        // 获取数据
        void setResult(PandaLiveBean pandaLiveBean);
        //报错的信息
        void showMessage(String msg);
        void checkbox(Boolean isChecked);
        //tablayout
        void pageradapter(String[] strings, ViewPager viewPager, TabLayout tab, List<Fragment> list);

        // void setfragent(Fragment fragment,int Layout);
    }
    interface  Presenter extends BasePresenter{

    }
}
