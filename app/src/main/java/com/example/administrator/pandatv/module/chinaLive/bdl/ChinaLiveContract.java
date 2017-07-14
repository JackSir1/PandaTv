package com.example.administrator.pandatv.module.chinaLive.bdl;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.LiveBDLBean;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface ChinaLiveContract {
    interface View extends BaseView<Presenter>{
        void onShowDialog();
        void onRefresh();
        void onLoadMore();
        void showError(String msg);
        void setResult(LiveBDLBean tablistBean);

    }
    interface Presenter extends BasePresenter {

    }
}
