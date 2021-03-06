package com.example.administrator.pandatv.module.home;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;
import com.example.administrator.pandatv.model.entity.HomeBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.UpDateLoading;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter>{

        void setReferesh();
        void loadMore();
        void setResult(HomeBean homeBean);
        void showErrorMassage(String errorMessage);
        void getVersion(UpDateLoading upDateLoading);

    }
    interface Presenter extends BasePresenter{

        void loadMore(int pageSize,int pageContent);
    }
}
