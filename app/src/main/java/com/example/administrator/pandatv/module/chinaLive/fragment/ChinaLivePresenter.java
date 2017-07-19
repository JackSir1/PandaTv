package com.example.administrator.pandatv.module.chinaLive.fragment;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LivechinaTabBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/18.
 */

public class ChinaLivePresenter implements ChinaLiveContract.Presenter {
    private ChinaLiveContract.View view;
    private IChinaLiveModel chinaLiveModel;
    public ChinaLivePresenter(ChinaLiveContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        chinaLiveModel=new ChinaLiveModel();
    }
    @Override
    public void start() {
        chinaLiveModel.getLiveChinas(new MyNetCallBack<LivechinaTabBean>() {
            @Override
            public void onSuccess(LivechinaTabBean livechinaTabBean) {
                view.setResult(livechinaTabBean);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
