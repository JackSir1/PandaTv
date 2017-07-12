package com.example.administrator.pandatv.module.pandaObserver;

import com.example.administrator.pandatv.model.biz.pandaObserverModel.IPandaObservermodel;
import com.example.administrator.pandatv.model.biz.pandaObserverModel.PandaObserverModel;
import com.example.administrator.pandatv.module.pandaLive.PandaLivePresenter;

/**
 * Created by Administrator on 2017/7/12.
 */

public class PandaObserverPresenter implements PandaObserverContract.Presenter {
    private PandaObserverContract.View view;
    private IPandaObservermodel pandaObservermodel;
    public PandaObserverPresenter(PandaObserverContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        this.pandaObservermodel=new PandaObserverModel();
    }
    @Override
    public void start() {

    }
}
