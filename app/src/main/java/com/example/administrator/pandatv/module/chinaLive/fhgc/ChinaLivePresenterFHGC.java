package com.example.administrator.pandatv.module.chinaLive.fhgc;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.LiveFHGCBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class ChinaLivePresenterFHGC implements ChinaLivefhgcContract.Presenter{

    private ChinaLivefhgcContract.View view;
    private IChinaLiveModel chinaLiveModel;
    public ChinaLivePresenterFHGC(ChinaLivefhgcContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        this.chinaLiveModel=new ChinaLiveModel();
    }
    @Override
    public void start() {
        chinaLiveModel.getLivechinafhgc(new MyNetCallBack<LiveFHGCBean>() {
            @Override
            public void onSuccess(LiveFHGCBean tablistBean)
            {
                view.setResult(tablistBean);
            }

            @Override
            public void onError(String error) {
                view.showError(error);
            }
        });
    }
}
