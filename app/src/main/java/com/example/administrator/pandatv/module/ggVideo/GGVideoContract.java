package com.example.administrator.pandatv.module.ggVideo;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.GGBean;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface GGVideoContract {
    interface View extends BaseView<Presenter> {
        void showProgresDialog();
        void dismeissDiolog();
        // 获取数据
        void setResult(GGBean ggBean);
        //报错的信息
        void showMessage(String msg);
    }
    interface Presenter extends BasePresenter {

    }
}
