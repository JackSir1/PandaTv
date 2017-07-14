package com.example.administrator.pandatv.module.chinaLive.bdl;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.LiveBDLBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/13.
 */

public class ChinaLivePresenterTS implements ChinaLiveContract.Presenter {
    private ChinaLiveContract.View view;
    private IChinaLiveModel chinaLiveModel;
    public ChinaLivePresenterTS(ChinaLiveContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        this.chinaLiveModel=new ChinaLiveModel();
    }
    @Override
    public void start() {
        chinaLiveModel.getLivechina(new MyNetCallBack<LiveBDLBean>() {
            @Override
            public void onSuccess(LiveBDLBean tablistBean) {
                view.setResult(tablistBean);
            }

            @Override
            public void onError(String error) {
                view.showError(error);
            }
        });
    }
}
