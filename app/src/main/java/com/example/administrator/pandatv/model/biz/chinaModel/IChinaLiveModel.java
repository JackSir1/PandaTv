package com.example.administrator.pandatv.model.biz.chinaModel;

import com.example.administrator.pandatv.model.entity.livechinaEntity.CehuaBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.EMeiShanBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.HuangShanBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveBDLBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveFHGCBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.SongShanBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.TaiShanBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.TianShanBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.ZhangJiajie;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface IChinaLiveModel {
    void getLivechina(MyNetCallBack<LiveBDLBean> myNetCallBack);
    void getLivechinafhgc(MyNetCallBack<LiveFHGCBean> myNetCallBack);
    void getLivechinasongshan(MyNetCallBack<SongShanBean> myNetCallBack);
    void getLivechinatianshan(MyNetCallBack<TianShanBean> myNetCallBack);
    void getLivechinataishan(MyNetCallBack<TaiShanBean> myNetCallBack);
    void getLivechinahuangshan(MyNetCallBack<HuangShanBean> myNetCallBack);
    void getLivechinaemeishan(MyNetCallBack<EMeiShanBean> myNetCallBack);
    void getLivechinazhangjiajie(MyNetCallBack<ZhangJiajie> myNetCallBack);
    void getLivechinayuanchuang(MyNetCallBack<CehuaBean> myNetCallBack);

}
