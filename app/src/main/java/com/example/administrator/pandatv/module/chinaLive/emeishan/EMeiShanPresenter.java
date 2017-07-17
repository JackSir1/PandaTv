package com.example.administrator.pandatv.module.chinaLive.emeishan;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.EMeiShanBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/15.
 */

public class EMeiShanPresenter implements EMeiShanContract.Presenter {
    private IChinaLiveModel iChinaLiveModel;
    private EMeiShanContract.View view;

    public EMeiShanPresenter(EMeiShanContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        iChinaLiveModel = new ChinaLiveModel();
    }

    @Override
    public void start() {
        iChinaLiveModel.getLivechinaemeishan(new MyNetCallBack<EMeiShanBean>() {
            @Override
            public void onSuccess(EMeiShanBean eMeiShanBean) {
                view.setResult(eMeiShanBean);
            }

            @Override
            public void onError(String error) {
                view.showError(error);
            }
        });
    }
}
