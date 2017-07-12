package com.example.administrator.pandatv.module.pandaLive;

import com.example.administrator.pandatv.model.biz.pandaLiveModel.IPandaLiveModel;
import com.example.administrator.pandatv.model.biz.pandaLiveModel.PandaLiveModel;

/**
 * Created by Administrator on 2017/7/12.
 */

public class PandaLivePresenter implements PandaLiveContract.Presenter {
    private PandaLiveContract.View view;
    private IPandaLiveModel pandaLiveModel;
    public PandaLivePresenter(PandaLiveContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        this.pandaLiveModel=new PandaLiveModel();
    }
    @Override
    public void start() {

    }
}
