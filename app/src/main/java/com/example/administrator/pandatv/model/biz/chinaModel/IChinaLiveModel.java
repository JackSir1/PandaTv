package com.example.administrator.pandatv.model.biz.chinaModel;

import com.example.administrator.pandatv.model.entity.livechinaEntity.CehuaBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveBDLBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LivechinaTabBean;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LoginEntity;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface IChinaLiveModel {
    void getLivechina(MyNetCallBack<LiveBDLBean> myNetCallBack);

    void getLivechinayuanchuang(MyNetCallBack<CehuaBean> myNetCallBack);

    void getLiveChinaUrls(String url,MyNetCallBack<LiveBDLBean> myNetCallBack);

    void getLiveChinas(MyNetCallBack<LivechinaTabBean> myNetCallBack);

    void getLogin(String username,String pwd,MyNetCallBack<LoginEntity> myNetCallBack);
}
