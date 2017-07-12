package com.example.administrator.pandatv.model.biz.homeModel;

import com.example.administrator.pandatv.model.biz.BaseModel;
import com.example.administrator.pandatv.model.entity.HomeBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface IHomeModel extends BaseModel {
    void getHomeResult(MyNetCallBack<HomeBean> callBack);
}
