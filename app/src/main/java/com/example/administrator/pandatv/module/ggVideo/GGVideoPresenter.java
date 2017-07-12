package com.example.administrator.pandatv.module.ggVideo;

import com.example.administrator.pandatv.model.biz.ggVideoModel.GGVideoModel;
import com.example.administrator.pandatv.model.biz.ggVideoModel.IGGVideoModel;

/**
 * Created by Administrator on 2017/7/12.
 */

public class GGVideoPresenter implements GGVideoContract.Presenter {
    private GGVideoContract.View view;
    private IGGVideoModel ggVideoModel;
    public GGVideoPresenter(GGVideoContract.View view){
        this.view=view;
        view.setPresenter(this);
        this.ggVideoModel=new GGVideoModel();
    }
    @Override
    public void start() {

    }
}
