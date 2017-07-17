package com.example.administrator.pandatv.module.chinaLive.tianshan;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.livechinaEntity.TianShanBean;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public interface TianShanContract {
    interface View extends BaseView<Presenter>{
        void onShowDialog();
        void onRefresh();
        void onLoadMore();
        void showError(String msg);
        void setResult(TianShanBean tianShanBean);

    }
    interface Presenter extends BasePresenter{

    }
}
