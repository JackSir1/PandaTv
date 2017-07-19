package com.example.administrator.pandatv.module.chinaLive.bdl;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveBDLBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/13.
 */

public class BDLChinaLivePresenterTS implements BDLChinaLiveContract.Presenter {
    private BDLChinaLiveContract.View view;
    private IChinaLiveModel chinaLiveModel;
    public BDLChinaLivePresenterTS(BDLChinaLiveContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        this.chinaLiveModel=new ChinaLiveModel();
    }
    @Override
    public void start() {

    }

    @Override
    public void setUrl(String url) {
        chinaLiveModel.getLiveChinaUrls(url, new MyNetCallBack<LiveBDLBean>() {
            @Override
            public void onSuccess(LiveBDLBean liveBDLBean) {
                view.setResult(liveBDLBean);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
