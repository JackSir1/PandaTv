package com.example.administrator.pandatv.module.pandaLive.budpull;


import com.example.administrator.pandatv.model.biz.pandaLiveModel.IPandaLiveModel;
import com.example.administrator.pandatv.model.biz.pandaLiveModel.PandaLiveModel;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;
//精彩时刻
public class BudPresenter implements BudContract.Presenter {
    private BudContract.view view;
    private IPandaLiveModel iPandaLiveModel;

    public BudPresenter(BudContract.view view) {
        this.view = view;
        this.view.setPresenter(this);
        this.iPandaLiveModel = new PandaLiveModel();

    }

    @Override
    public void start() {
        iPandaLiveModel.getPandaLiveBud(new MyNetCallBack<PandaLiveSplendidBean>() {
            @Override
            public void onSuccess(PandaLiveSplendidBean pandaLiveSplendidBean) {
                view.setResult(pandaLiveSplendidBean);
            }

            @Override
            public void onError(String error) {
                view.showMessage(error);
            }
        });

    }
}
