package com.example.administrator.pandatv.module.chinaLive;

import com.example.administrator.pandatv.module.chinaLive.bdl.ChinaLiveContract;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ChinaLivePresenter implements ChinaLiveContract.Presenter{
    private ChinaLiveContract.View view;
//    private IChinaLiveModel chinaLiveModel;
    public ChinaLivePresenter(ChinaLiveContract.View view){
        this.view=view;
        this.view.setPresenter(this);
//        this.chinaLiveModel=new ChinaLiveModel();
    }
    @Override
    public void start() {

    }
}
