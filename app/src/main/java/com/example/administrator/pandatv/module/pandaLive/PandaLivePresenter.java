package com.example.administrator.pandatv.module.pandaLive;

import com.example.administrator.pandatv.model.biz.pandaLiveModel.IPandaLiveModel;
import com.example.administrator.pandatv.model.biz.pandaLiveModel.PandaLiveModel;
import com.example.administrator.pandatv.model.entity.PandaLiveBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

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
       // view.checkbox();
        pandaLiveModel.getPandaLive(new MyNetCallBack<PandaLiveBean>() {
            @Override
            public void onSuccess(PandaLiveBean pandaLiveBean) {
                view.setResult(pandaLiveBean);
            }

            @Override
            public void onError(String error) {
                view.showMessage(error);

            }
        });

    }
}
