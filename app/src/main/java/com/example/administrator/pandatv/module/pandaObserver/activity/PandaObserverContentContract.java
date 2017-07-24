package com.example.administrator.pandatv.module.pandaObserver.activity;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.PandaObserverFirstItemBean;
import com.example.administrator.pandatv.model.entity.PlayVideoBean;

/**
 * Created by Administrator on 2017/7/21.
 */

public interface PandaObserverContentContract {
    interface View extends BaseView<Presenter>{
        void getManager(PandaObserverFirstItemBean firstItemBean);
        void setPid(PlayVideoBean playVideoBean);
    }
    interface Presenter extends BasePresenter{
        void getVideoUrl(String pid);
    }
}
