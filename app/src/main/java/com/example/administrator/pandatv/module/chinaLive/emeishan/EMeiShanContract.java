package com.example.administrator.pandatv.module.chinaLive.emeishan;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.livechinaEntity.EMeiShanBean;

/**
 * Created by lizhuofang on 2017/7/15.
 */

public interface EMeiShanContract {
    interface View extends BaseView<Presenter> {
        void onShowDialog();
        void onRefresh();
        void onLoadMore();
        void showError(String msg);
        void setResult(EMeiShanBean eMeiShanBean);

    }
    interface Presenter extends BasePresenter {

    }
}
