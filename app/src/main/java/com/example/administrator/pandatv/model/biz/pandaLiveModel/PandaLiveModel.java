package com.example.administrator.pandatv.model.biz.pandaLiveModel;

import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.model.entity.PandaLiveBean;
import com.example.administrator.pandatv.model.entity.PandaLiveMoreBean;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * 熊猫直播工具类
 * Created by Administrator on 2017/7/11.
 */

public class PandaLiveModel implements IPandaLiveModel {
    @Override
    public void getPandaLive(MyNetCallBack<PandaLiveBean> callBack) {
               I_HTTP.get(Url.PANDALIVE,null,callBack);
    }

    @Override
    public void getPandaLiveBud(MyNetCallBack<PandaLiveSplendidBean> callBack) {
        I_HTTP.get(Url.SPLENDID,null,callBack);
    }

    @Override
    public void getMoreLive(MyNetCallBack<PandaLiveMoreBean> callBack) {
        I_HTTP.get(Url.MORELIVE,null,callBack);
    }


}
