package com.example.administrator.pandatv.model.util.playVideoUtil;

import com.example.administrator.pandatv.model.biz.IPlayVideoModel;
import com.example.administrator.pandatv.model.biz.PlayVideModel;
import com.example.administrator.pandatv.model.entity.PlayVideoBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by Administrator on 2017/7/18.
 */

public class PlayVideoPresenter implements IPlayVideoContract.Presenter{
    private IPlayVideoContract.View view;
    private IPlayVideoModel playVideoModel;
    public PlayVideoPresenter(IPlayVideoContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        playVideoModel=new PlayVideModel();
    }
    @Override
    public void start() {

    }

    @Override
    public void setVedioPid(String pid) {
        playVideoModel.setVideoPid(pid, new MyNetCallBack<PlayVideoBean>() {
            @Override
            public void onSuccess(PlayVideoBean playVideoBean) {
                view.getVedioUrl(playVideoBean);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
