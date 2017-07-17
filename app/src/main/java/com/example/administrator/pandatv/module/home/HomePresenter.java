package com.example.administrator.pandatv.module.home;

import com.example.administrator.pandatv.MainActivity;
import com.example.administrator.pandatv.model.biz.homeModel.HomeModel;
import com.example.administrator.pandatv.model.biz.homeModel.IHomeModel;
import com.example.administrator.pandatv.model.entity.HomeBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by Administrator on 2017/7/12.
 */

public class HomePresenter implements HomeContract.Presenter {
    private IHomeModel iHomeModel;
    private HomeContract.View view;
    public HomePresenter(HomeContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        this.iHomeModel=new HomeModel();
    }
    @Override
    public void start() {
        iHomeModel.getHomeResult(new MyNetCallBack<HomeBean>() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                view.setResult(homeBean);
            }

            @Override
            public void onError(String error) {
                view.showErrorMassage(error);
            }
        });

    }

    @Override
    public void loadMore(int pageSize,int pageContent) {
        new HomeModel().loadMore(pageSize,pageContent);
    }
}
