package com.example.administrator.pandatv.model.biz.pandaLiveModel;

import com.example.administrator.pandatv.model.biz.BaseModel;
import com.example.administrator.pandatv.model.entity.PandaLiveBean;
import com.example.administrator.pandatv.model.entity.PandaLiveMoreBean;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface IPandaLiveModel extends BaseModel {
    void getPandaLive(MyNetCallBack<PandaLiveBean> callBack);
    void getPandaLiveBud(String vsid,String n,String serviceId,String o,String of,String p,MyNetCallBack<PandaLiveSplendidBean> callBack);
    void getMoreLive(MyNetCallBack<PandaLiveMoreBean> callBack);



}
