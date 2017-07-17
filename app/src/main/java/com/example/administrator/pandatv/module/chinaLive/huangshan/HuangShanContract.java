package com.example.administrator.pandatv.module.chinaLive.huangshan;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.livechinaEntity.HuangShanBean;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public interface HuangShanContract {
    interface View extends BaseView<Presenter> {
        void onShowDialog();
        void onRefresh();
        void onLoadMore();
        void showError(String msg);
        void setResult(HuangShanBean songShanBean);

    }
    interface Presenter extends BasePresenter {

    }
}
