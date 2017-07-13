package com.example.administrator.pandatv.module.chinaLive;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.LivechinaTSBean;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface ChinaLiveContract {
    interface View extends BaseView<Presenter>{
        void onShowDialog();
        void onRefresh();
        void onLoadMore();
        void showError(String msg);
        void setResult(LivechinaTSBean livechinaTSBean);

    }
    interface Presenter extends BasePresenter {

    }
}
