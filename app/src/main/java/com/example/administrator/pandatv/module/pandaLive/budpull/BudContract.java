package com.example.administrator.pandatv.module.pandaLive.budpull;


import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;
//精彩时刻契约类
public interface BudContract {
    interface  Presenter extends BasePresenter{

    }
    interface  view extends BaseView<Presenter>{
        void showProgresDialog();
        void dismeissDiolog();
        void showMessage(String msg);
        // 获取数据
        void setResult(PandaLiveSplendidBean pandaLiveSplendidBean);
    }
}
