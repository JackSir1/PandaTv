package com.example.administrator.pandatv.module.chinaLive.zhangjiajie;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.ZhangJiajie;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class ZhangjiajiePresenter implements ZhangjiajieContract.Presenter {
    private ZhangjiajieContract.View view;
    private IChinaLiveModel chinaLiveModel;

    public ZhangjiajiePresenter(ZhangjiajieContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.chinaLiveModel = new ChinaLiveModel();
    }

    @Override
    public void start() {
        chinaLiveModel.getLivechinazhangjiajie(new MyNetCallBack<ZhangJiajie>() {

            @Override
            public void onSuccess(ZhangJiajie zhangJiajie) {
                view.setResult(zhangJiajie);
            }

            @Override
            public void onError(String error) {
                view.showError(error);
            }
        });
    }
}
