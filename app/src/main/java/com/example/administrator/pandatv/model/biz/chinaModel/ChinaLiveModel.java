package com.example.administrator.pandatv.model.biz.chinaModel;

import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.model.entity.LivechinaTSBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;
import com.example.administrator.pandatv.net.HttpFactory;

/**
 * 直播中国工具类
 * Created by Administrator on 2017/7/11.
 */

public class ChinaLiveModel implements IChinaLiveModel {
    @Override
    public void getLivechina(MyNetCallBack<LivechinaTSBean> myNetCallBack) {
        HttpFactory.create().get(Url.LIVECHINA,null,myNetCallBack);
    }
}
