package com.example.administrator.pandatv.module.chinaLive.fragment;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LivechinaTabBean;

/**
 * Created by lizhuofang on 2017/7/18.
 */

public interface ChinaLiveContract {
    interface View extends BaseView<Presenter>{
        void setResult(LivechinaTabBean livechinaTabBean);
    }
    interface Presenter extends BasePresenter{

    }
}
