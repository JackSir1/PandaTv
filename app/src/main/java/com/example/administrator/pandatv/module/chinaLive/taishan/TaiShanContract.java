package com.example.administrator.pandatv.module.chinaLive.taishan;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.livechinaEntity.TaiShanBean;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public interface TaiShanContract {
    interface View extends BaseView<Presenter> {
        void onShowDialog();
        void onRefresh();
        void onLoadMore();
        void showError(String msg);
        void setResult(TaiShanBean songShanBean);

    }
    interface Presenter extends BasePresenter {

    }
}
