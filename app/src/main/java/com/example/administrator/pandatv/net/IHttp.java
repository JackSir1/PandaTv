package com.example.administrator.pandatv.net;

import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface IHttp {
    <T>void get(String url, Map<String,String> map, MyNetCallBack<T> callBack);
    <T>void post(String url,Map<String,String> map,MyNetCallBack<T> callBack);
    void upload();
    void downLoad();
    void loadImage();
}
