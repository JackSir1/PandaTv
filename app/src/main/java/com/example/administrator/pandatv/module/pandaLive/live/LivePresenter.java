package com.example.administrator.pandatv.module.pandaLive.live;


import com.example.administrator.pandatv.model.biz.pandaLiveModel.IPandaLiveModel;
import com.example.administrator.pandatv.model.biz.pandaLiveModel.PandaLiveModel;
import com.example.administrator.pandatv.model.entity.PandaLiveBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

public class LivePresenter implements LiveContract.Presenter {
    private LiveContract.View view;
    private IPandaLiveModel iPandaLiveModel;

    public LivePresenter(LiveContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.iPandaLiveModel= new PandaLiveModel();
    }

    @Override
    public void start() {
           iPandaLiveModel.getPandaLive(new MyNetCallBack<PandaLiveBean>() {
               @Override
               public void onSuccess(PandaLiveBean pandaLiveBean) {
                    view.setResult(pandaLiveBean);
               }

               @Override
               public void onError(String error) {

               }
           });
    }
}
