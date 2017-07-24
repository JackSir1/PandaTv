package com.example.administrator.pandatv.model.biz.pandaObserverModel;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.model.entity.PandaObserverBean;
import com.example.administrator.pandatv.model.entity.PandaObserverFirstItemBean;
import com.example.administrator.pandatv.model.entity.PlayVideoBean;
import com.example.administrator.pandatv.model.util.ACache;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * 熊猫观察工具类
 * Created by Administrator on 2017/7/11.
 */

public class PandaObserverModel implements IPandaObservermodel {
    @Override
    public void setObserverCallBack(MyNetCallBack<PandaObserverBean> callBack) {
        App.iHttp.get(Url.PandaObserver,null,callBack);
    }

    @Override
    public void setObserverItemVideo(MyNetCallBack<PandaObserverFirstItemBean> callBack) {
        App.iHttp.get(Url.PANDAOBSERVERFIRSTITEM,null,callBack);
    }

    @Override
    public void setObserverItemVideoBean(String pid, MyNetCallBack<PlayVideoBean> callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        I_HTTP.get(Url.PandaVideoPlayPATH,map,callBack);
    }
}
