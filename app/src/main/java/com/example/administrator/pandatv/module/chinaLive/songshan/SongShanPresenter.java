package com.example.administrator.pandatv.module.chinaLive.songshan;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.SongShanBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class SongShanPresenter implements SongShanContract.Presenter {
    private SongShanContract.View view;
    private IChinaLiveModel chinaLiveModel;
    public SongShanPresenter(SongShanContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        this.chinaLiveModel=new ChinaLiveModel();
    }
    @Override
    public void start() {
        chinaLiveModel.getLivechinasongshan(new MyNetCallBack<SongShanBean>() {
            @Override
            public void onSuccess(SongShanBean tablistBean)
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
