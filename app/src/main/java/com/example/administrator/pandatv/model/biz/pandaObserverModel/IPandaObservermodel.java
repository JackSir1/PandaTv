package com.example.administrator.pandatv.model.biz.pandaObserverModel;

import com.example.administrator.pandatv.model.biz.BaseModel;
import com.example.administrator.pandatv.model.entity.PandaObserverBean;
import com.example.administrator.pandatv.model.entity.PandaObserverFirstItemBean;
import com.example.administrator.pandatv.model.entity.PlayVideoBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface IPandaObservermodel extends BaseModel {
    void setObserverCallBack(MyNetCallBack<PandaObserverBean> callBack);
    void setObserverItemVideo(MyNetCallBack<PandaObserverFirstItemBean> callBack);
    void setObserverItemVideoBean(String pid, MyNetCallBack<PlayVideoBean> callBack);
}
