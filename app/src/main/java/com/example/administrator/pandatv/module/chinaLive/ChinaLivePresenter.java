package com.example.administrator.pandatv.module.chinaLive;

import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ChinaLivePresenter implements ChinaLiveContract.Presenter{
    private ChinaLiveContract.View view;
    private IChinaLiveModel chinaLiveModel;
    public ChinaLivePresenter(ChinaLiveContract.View view){
        this.view=view;
        view.setPresenter(this);
        this.chinaLiveModel=new ChinaLiveModel();
    }
    @Override
    public void start() {

    }
}
