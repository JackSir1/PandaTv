package com.example.administrator.pandatv.module.pandaLive.live.moresight;


import com.example.administrator.pandatv.model.biz.pandaLiveModel.IPandaLiveModel;
import com.example.administrator.pandatv.model.biz.pandaLiveModel.PandaLiveModel;
import com.example.administrator.pandatv.model.entity.PandaLiveMoreBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

public class MoreSightPresenter implements MorSightContract.Presenter {
    private MorSightContract.view view;
    private IPandaLiveModel iPandaLiveModel;

    public MoreSightPresenter(MorSightContract.view view) {
        this.view = view;
        this.view.setPresenter(this);
        this.iPandaLiveModel= new PandaLiveModel();
    }

    @Override
    public void start() {
        iPandaLiveModel.getMoreLive(new MyNetCallBack<PandaLiveMoreBean>() {
            @Override
            public void onSuccess(PandaLiveMoreBean pandaLiveMoreBean) {
                view.setResult(pandaLiveMoreBean);
            }

            @Override
            public void onError(String error) {
                view.showMessage(error);
            }
        });

    }
}
