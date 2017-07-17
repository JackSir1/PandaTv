package com.example.administrator.pandatv.model.biz.chinaModel;

import com.example.administrator.pandatv.config.Url;
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
    public void getLivechinafhgc(MyNetCallBack<LiveFHGCBean> myNetCallBack) {
        HttpFactory.create().get(Url.FHGC,null,myNetCallBack);
    }

    @Override
    public void getLivechinasongshan(MyNetCallBack<SongShanBean> myNetCallBack) {
        HttpFactory.create().get(Url.SONGSHAN,null,myNetCallBack);
    }

    @Override
    public void getLivechinatianshan(MyNetCallBack<TianShanBean> myNetCallBack) {
        HttpFactory.create().get(Url.LIVECHINA,null,myNetCallBack);

    }

    @Override
    public void getLivechinataishan(MyNetCallBack<TaiShanBean> myNetCallBack) {
        HttpFactory.create().get(Url.TAISHAN,null,myNetCallBack);
    }

    @Override
    public void getLivechinahuangshan(MyNetCallBack<HuangShanBean> myNetCallBack) {
        HttpFactory.create().get(Url.HUANGSHAN,null,myNetCallBack);
    }

    @Override
    public void getLivechinaemeishan(MyNetCallBack<EMeiShanBean> myNetCallBack) {
        HttpFactory.create().get(Url.EMEISHAN,null,myNetCallBack);

    }

    @Override
    public void getLivechinazhangjiajie(MyNetCallBack<ZhangJiajie> myNetCallBack) {
        HttpFactory.create().get(Url.ZHANGJIAJIE,null,myNetCallBack);
    }

    @Override
    public void getLivechinayuanchuang(MyNetCallBack<CehuaBean> myNetCallBack) {
        HttpFactory.create().get(Url.CEHUA,null,myNetCallBack);
    }


}
