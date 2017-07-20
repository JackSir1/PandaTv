package com.example.administrator.pandatv.net.okhttploginandregist;

import com.example.administrator.pandatv.net.IHttp;

/**
 * Created by lizhuofang on 2017/7/19.
 */

public class OkhttpFra {
    public static IHttp create(){

        return OkhttpUtilsLogReg.getInstance();
    }
}
