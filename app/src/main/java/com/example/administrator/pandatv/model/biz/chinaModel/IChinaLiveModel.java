package com.example.administrator.pandatv.model.biz.chinaModel;

import com.example.administrator.pandatv.model.entity.LiveBDLBean;
import com.example.administrator.pandatv.model.entity.LiveFHGCBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface IChinaLiveModel {
    void getLivechina(MyNetCallBack<LiveBDLBean> myNetCallBack);
    void getLivechinafhgc(MyNetCallBack<LiveFHGCBean> myNetCallBack);
}
