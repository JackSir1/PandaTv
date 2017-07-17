package com.example.administrator.pandatv.module.chinaLive.taishan;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.TaiShanBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class TaiShanPresenter implements TaiShanContract.Presenter {
    private TaiShanContract.View view;
    private IChinaLiveModel chinaLiveModel;
    public TaiShanPresenter(TaiShanContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        this.chinaLiveModel=new ChinaLiveModel();
    }
    @Override
    public void start() {
        chinaLiveModel.getLivechinataishan(new MyNetCallBack<TaiShanBean>() {
            @Override
            public void onSuccess(TaiShanBean tablistBean)
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
