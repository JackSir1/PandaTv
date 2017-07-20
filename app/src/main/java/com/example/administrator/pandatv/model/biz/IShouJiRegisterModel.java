package com.example.administrator.pandatv.model.biz;

import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/19.
 */

public interface IShouJiRegisterModel extends BaseModel {
    void setShouJiRegister(String name, String pwd, String yanZhengMa, MyNetCallBack callBack);
}
