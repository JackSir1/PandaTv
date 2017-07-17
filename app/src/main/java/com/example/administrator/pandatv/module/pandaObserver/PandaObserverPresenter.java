package com.example.administrator.pandatv.module.pandaObserver;

import com.example.administrator.pandatv.model.biz.pandaObserverModel.IPandaObservermodel;
import com.example.administrator.pandatv.model.biz.pandaObserverModel.PandaObserverModel;
import com.example.administrator.pandatv.model.entity.PandaObserverBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

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
        pandaObservermodel.setObserverCallBack(new MyNetCallBack<PandaObserverBean>() {
            @Override
            public void onSuccess(PandaObserverBean pandaObserverBean) {
                view.setResult(pandaObserverBean);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
