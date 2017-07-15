package com.example.administrator.pandatv.module.pandaLive.live.moresight;


import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.PandaLiveMoreBean;

public interface MorSightContract {
    interface Presenter extends BasePresenter{


    }
    interface view extends BaseView<Presenter>{
        // 获取数据
        void setResult(PandaLiveMoreBean pandaLiveMoreBean);
        //报错的信息
        void showMessage(String msg);
    }
}
