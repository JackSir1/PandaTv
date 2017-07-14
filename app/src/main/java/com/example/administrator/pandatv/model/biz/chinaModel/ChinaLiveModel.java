package com.example.administrator.pandatv.model.biz.chinaModel;

import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.model.entity.LiveBDLBean;
import com.example.administrator.pandatv.model.entity.LiveFHGCBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;
import com.example.administrator.pandatv.net.HttpFactory;

/**
 * 直播中国工具类
 * Created by Administrator on 2017/7/11.
 */

public class ChinaLiveModel implements IChinaLiveModel {
    @Override
    public void getLivechina(MyNetCallBack<LiveBDLBean> myNetCallBack) {
        HttpFactory.create().get(Url.BADALING,null,myNetCallBack);
    }

    @Override
    public void getLivechinafhgc(MyNetCallBack<LiveFHGCBean> myNetCallBack) {
        HttpFactory.create().get(Url.FHGC,null,myNetCallBack);
    }
}
