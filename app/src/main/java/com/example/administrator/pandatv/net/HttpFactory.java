package com.example.administrator.pandatv.net;

/**
 * Created by Administrator on 2017/7/11.
 */

public class HttpFactory {
    public IHttp create(){
        return OkHttpUtils.getInsent();
    }
}
