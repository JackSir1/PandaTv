package com.example.administrator.pandatv.module.pandaLive;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.PandaLiveBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface PandaLiveContract {
    interface View extends BaseView<Presenter> {
        //dialog
        void showProgresDialog();
        void dismeissDiolog();
        // 获取数据
        void setResult(PandaLiveBean pandaLiveBean);
        //报错的信息
        void showMessage(String msg);
        void checkbox(Boolean isChecked);
        //tablayout
        void pageradapter(String[] strings, ViewPager viewPager, TabLayout tab, List<Fragment> list);

        //



    }

    interface Presenter extends BasePresenter {

    }
}
