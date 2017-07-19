package com.example.administrator.pandatv.model.biz.chinaModel;

import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.model.entity.livechinaEntity.CehuaBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveBDLBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LivechinaTabBean;
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
    public void getLivechinayuanchuang(MyNetCallBack<CehuaBean> myNetCallBack) {
        HttpFactory.create().get(Url.CEHUA,null,myNetCallBack);
    }

    @Override
    public void getLiveChinaUrls(String url,MyNetCallBack<LiveBDLBean> myNetCallBack) {
        HttpFactory.create().get(url,null,myNetCallBack);
    }

    @Override
    public void getLiveChinas(MyNetCallBack<LivechinaTabBean> myNetCallBack) {
        HttpFactory.create().get(Url.LIVECHINAS,null,myNetCallBack);
    }


}
