package com.example.administrator.pandatv.model.biz.chinaModel;

import com.example.administrator.pandatv.net.CallBack.MyEmailCallBack;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/22.
 */

public interface EmailModel {
    void getEmileRegister(String emile, String pwd, String yanzhengma, MyNetCallBack callBack);
    void setEmiRegs(MyEmailCallBack callBack);
}
