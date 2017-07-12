package com.example.administrator.pandatv.net.CallBack;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface MyNetCallBack<T> {
    void onSuccess(T t);
    void onError(String error);
}
