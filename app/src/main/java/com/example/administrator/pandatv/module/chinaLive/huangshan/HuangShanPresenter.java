package com.example.administrator.pandatv.module.chinaLive.huangshan;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.HuangShanBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class HuangShanPresenter implements HuangShanContract.Presenter {
    private HuangShanContract.View view;
    private IChinaLiveModel chinaLiveModel;
    public HuangShanPresenter(HuangShanContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        this.chinaLiveModel=new ChinaLiveModel();
    }
    @Override
    public void start() {
        chinaLiveModel.getLivechinahuangshan(new MyNetCallBack<HuangShanBean>() {
            @Override
            public void onSuccess(HuangShanBean tablistBean)
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
