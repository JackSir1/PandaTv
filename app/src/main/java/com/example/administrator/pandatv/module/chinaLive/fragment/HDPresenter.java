package com.example.administrator.pandatv.module.chinaLive.fragment;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.CehuaBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/21.
 */

public class HDPresenter implements HDContract.Presenter {
    private HDContract.View view;
    private IChinaLiveModel chinaLiveModel;
    public HDPresenter(HDContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        chinaLiveModel=new ChinaLiveModel();
    }
    @Override
    public void start() {
        chinaLiveModel.getLivechinayuanchuang(new MyNetCallBack<CehuaBean>() {
            @Override
            public void onSuccess(CehuaBean cehuaBean) {
                view.setResult(cehuaBean);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
