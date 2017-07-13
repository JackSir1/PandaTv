package com.example.administrator.pandatv.net;

/**
 * Created by Administrator on 2017/7/11.
 */

public class HttpFactory {

    public static final int VOLLEY = 0;
    public static final int OKHTTP = 1;
    public static final int RETROFIT = 2;
    public static final int TYPE = OKHTTP;
    public static IHttp create(){
        IHttp iHttp=null;
        switch (TYPE){
            case 0:
                break;
            case 1:
                iHttp=OkHttpUtils.getInsent();
                break;
            case 2:
                break;
        }
        return iHttp;
    }
}
