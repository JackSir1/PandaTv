package com.example.administrator.pandatv.model.biz.pandaLiveModel;

import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.model.entity.PandaLiveBean;
import com.example.administrator.pandatv.model.entity.PandaLiveMoreBean;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * 熊猫直播工具类
 * Created by Administrator on 2017/7/11.
 */

public class PandaLiveModel implements IPandaLiveModel {
    @Override
    public void getPandaLive(MyNetCallBack<PandaLiveBean> callBack) {
               I_HTTP.get(Url.PANDALIVE,null,callBack);
    }
//精彩一刻
    @Override
    public void getPandaLiveBud(String vsid, String n, String serviceId,String o,String of,String p,MyNetCallBack<PandaLiveSplendidBean> callBack) {
        //vsid=VSET100167216881&n=7&serviceId=panda&o=desc&of=time&p=1
        Map<String,String> map=new HashMap<>();
        map.put("vsid",vsid);
        map.put("n",n);
        map.put("serviceId",serviceId);
        map.put("o",o);
        map.put("of",of);
        map.put("p",p);
        I_HTTP.get(Url.SPLENDID,map,callBack);
    }

    @Override
    public void getMoreLive( MyNetCallBack<PandaLiveMoreBean> callBack) {

        I_HTTP.get(Url.MORELIVE,null,callBack);
    }




}
