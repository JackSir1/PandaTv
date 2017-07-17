package com.example.administrator.pandatv.module.chinaLive.fhgc;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveFHGCBean;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public interface ChinaLivefhgcContract {
    interface View extends BaseView<Presenter> {
        void onShowDialog();
        void onRefresh();
        void onLoadMore();
        void showError(String msg);
        void setResult(LiveFHGCBean liveFHGCBean);

    }
    interface Presenter extends BasePresenter {

    }
}
