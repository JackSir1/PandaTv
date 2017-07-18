package com.example.administrator.pandatv.model.biz.pandaObserverModel;

import com.example.administrator.pandatv.model.entity.PandaObserverBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * 熊猫观察工具类
 * Created by Administrator on 2017/7/11.
 */

public class PandaObserverModel implements IPandaObservermodel {
    @Override
    public void setObserverCallBack(MyNetCallBack<PandaObserverBean> callBack) {
//        App.iHttp.get(Url.PandaObserver,null,callBack);
    }
}
