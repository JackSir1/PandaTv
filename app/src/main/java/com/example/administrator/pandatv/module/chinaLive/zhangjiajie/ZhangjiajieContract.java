package com.example.administrator.pandatv.module.chinaLive.zhangjiajie;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.livechinaEntity.ZhangJiajie;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public interface ZhangjiajieContract {
    interface View extends BaseView<Presenter> {
        void onShowDialog();
        void onRefresh();
        void onLoadMore();
        void showError(String msg);
        void setResult(ZhangJiajie zhangJiajie);

    }
    interface Presenter extends BasePresenter {

    }
}
