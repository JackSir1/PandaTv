package com.example.administrator.pandatv.module.pandaLive.budpull;


import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;

//精彩时刻契约类
public interface BudContract {
    interface  Presenter extends BasePresenter{
        // VSET100272959126&n=7&serviceId=panda&o=desc&of=time&p=1
        void GetData(String vsid,String n,String serviceId,String o,String of,String p);

    }
    interface  view extends BaseView<Presenter>{
        void showProgresDialog();
        void dismeissDiolog();
        void showMessage(String msg);
        // 获取数据
        void setResult(PandaLiveSplendidBean pandaLiveSplendidBean);
    }
}
