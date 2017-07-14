package com.example.administrator.pandatv.model.biz.ggVideoModel;

import com.example.administrator.pandatv.model.biz.BaseModel;
import com.example.administrator.pandatv.model.entity.GGBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface IGGVideoModel extends BaseModel {
    void getGGviedo(MyNetCallBack<GGBean> callBack);
}
