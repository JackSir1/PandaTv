package com.example.administrator.pandatv.model.biz;

import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.model.biz.IPlayVideoModel;
import com.example.administrator.pandatv.model.entity.PlayVideoBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/18.
 */

public class PlayVideModel implements IPlayVideoModel {
    @Override
    public <PlayVideoBean> void setVideoPid(String pid, MyNetCallBack<PlayVideoBean> callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        I_HTTP.get(Url.PandaVideoPlayPATH,map,callBack);
    }
}
