package com.example.administrator.pandatv.model.biz.chinaModel;

import com.example.administrator.pandatv.model.entity.livechinaEntity.EMeiShanBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveBDLBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveFHGCBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.SongShanBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.TianShanBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface IChinaLiveModel {
    void getLivechina(MyNetCallBack<LiveBDLBean> myNetCallBack);
    void getLivechinafhgc(MyNetCallBack<LiveFHGCBean> myNetCallBack);
    void getLivechinasongshan(MyNetCallBack<SongShanBean> myNetCallBack);
    void getLivechinatianshan(MyNetCallBack<TianShanBean> myNetCallBack);
    void getLivechinaemeishan(MyNetCallBack<EMeiShanBean> myNetCallBack);

}
