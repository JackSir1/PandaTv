package com.example.administrator.pandatv.module.chinaLive.fragment;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.livechinaEntity.CehuaBean;

/**
 * Created by lizhuofang on 2017/7/21.
 */

public interface HDContract {
    interface View extends BaseView<Presenter>{
        void setResult(CehuaBean cehuaBean);
    }
    interface Presenter extends BasePresenter{

    }
}
