package com.example.administrator.pandatv.module.chinaLive.tianshan;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.TianShanBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class TianShanPresenter implements TianShanContract.Presenter {
    private IChinaLiveModel iChinaLiveModel;
    private TianShanContract.View view;

    public TianShanPresenter(TianShanContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        iChinaLiveModel = new ChinaLiveModel();
    }

    @Override
    public void start() {
        iChinaLiveModel.getLivechinatianshan(new MyNetCallBack<TianShanBean>() {
            @Override
            public void onSuccess(TianShanBean tianShanBean) {
                view.setResult(tianShanBean);
            }

            @Override
            public void onError(String error) {
                view.showError(error);
            }
        });
    }
}
