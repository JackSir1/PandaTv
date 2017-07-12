package com.example.administrator.pandatv.model.biz.homeModel;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.model.entity.HomeBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

import java.util.Map;

/**
 * 首页工具类
 * Created by Administrator on 2017/7/11.
 */

public class HomeModel implements IHomeModel {
    @Override
    public void getHomeResult(MyNetCallBack<HomeBean> callBack) {
        App.iHttp.get(Url.HOMELIVE,null,callBack);
    }
}
