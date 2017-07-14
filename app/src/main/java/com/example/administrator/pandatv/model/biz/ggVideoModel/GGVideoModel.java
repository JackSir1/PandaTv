package com.example.administrator.pandatv.model.biz.ggVideoModel;

import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.model.entity.GGBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 *
 * 滚滚视频工具类
 * Created by Administrator on 2017/7/11.
 */


public class GGVideoModel implements IGGVideoModel {

    @Override
    public void getGGviedo(MyNetCallBack<GGBean> callBack) {
        I_HTTP.get(Url.GGVIEDO,null,callBack);
    }
}
