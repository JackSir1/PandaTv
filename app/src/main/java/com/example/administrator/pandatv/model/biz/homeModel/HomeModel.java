package com.example.administrator.pandatv.model.biz.homeModel;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.model.entity.HomeBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.UpDateLoading;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * 首页工具类
 * Created by Administrator on 2017/7/11.
 */

public class HomeModel implements IHomeModel {
    @Override
    public void getHomeResult(MyNetCallBack<HomeBean> callBack) {
        App.iHttp.get(Url.HOMELIVE,null,callBack);
    }

    @Override
    public void loadMore(int pageSize, int pageContent) {

    }

    @Override
    public void getVerSion(MyNetCallBack<UpDateLoading> myNetCallBack) {
        App.iHttp.get(Url.UPDATE,null,myNetCallBack);
    }
}
