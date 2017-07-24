package com.example.administrator.pandatv.module.pandaObserver.activity;

import com.example.administrator.pandatv.model.biz.pandaObserverModel.IPandaObservermodel;
import com.example.administrator.pandatv.model.biz.pandaObserverModel.PandaObserverModel;
import com.example.administrator.pandatv.model.entity.PandaObserverFirstItemBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by Administrator on 2017/7/21.
 */

public class PandaObserverContentPresenter implements PandaObserverContentContract.Presenter {
    private PandaObserverContentContract.View view;
    private IPandaObservermodel pandaObservermodel;
    public PandaObserverContentPresenter(PandaObserverContentContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        pandaObservermodel=new PandaObserverModel();
    }
    @Override
    public void start() {
        pandaObservermodel.setObserverItemVideo(new MyNetCallBack<PandaObserverFirstItemBean>() {
            @Override
            public void onSuccess(PandaObserverFirstItemBean pandaObserverFirstItemBean) {
                view.getManager(pandaObserverFirstItemBean);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
