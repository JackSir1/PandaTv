package com.example.administrator.pandatv.model.biz;

import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/18.
 */

public interface IPlayVideoModel extends BaseModel{
    <T>void setVideoPid(String pid, MyNetCallBack<T> callBack);
}
