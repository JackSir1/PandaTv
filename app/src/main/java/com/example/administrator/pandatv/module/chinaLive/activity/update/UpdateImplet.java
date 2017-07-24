package com.example.administrator.pandatv.module.chinaLive.activity.update;

import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.UpDateLoading;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;
import com.example.administrator.pandatv.net.OkHttpUtils;

/**
 * Created by lizhuofang on 2017/7/23.
 */

public class UpdateImplet implements UpdateContract.Presenter {
    private UpdateContract.View view;
    private IChinaLiveModel chinaLiveModel;
    private OkHttpUtils okHttpUtils;
    public UpdateImplet(UpdateContract.View view){
        this.view=view;
        this.view.setPresenter(this);
    }
    @Override
    public void start() {

    }
    //获取版本号
    public void getVersion(String url){
       okHttpUtils.get(url, null, new MyNetCallBack<UpDateLoading>() {
                      @Override
           public void onSuccess(UpDateLoading upDateLoading) {
               view.getVersion(upDateLoading);
           }

           @Override
            public void onError(String msg) {
            }
        });

    }
}
