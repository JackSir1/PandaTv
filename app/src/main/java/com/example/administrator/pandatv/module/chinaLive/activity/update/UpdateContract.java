package com.example.administrator.pandatv.module.chinaLive.activity.update;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.livechinaEntity.UpDateLoading;

/**
 * Created by lizhuofang on 2017/7/23.
 */

public class UpdateContract {
    interface View extends BaseView<Presenter>{
        void getVersion(UpDateLoading upDateLoading);
    }
    interface Presenter extends BasePresenter{
    }
}
