package com.example.administrator.pandatv.model.biz.chinaModel;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.config.Url;
import com.example.administrator.pandatv.module.chinaLive.EmailUtils;
import com.example.administrator.pandatv.net.CallBack.MyEmailCallBack;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhuofang on 2017/7/22.
 */

public class EmailImp implements EmailModel {
    @Override
    public void getEmileRegister(String emile, String pwd, String yanzhengma, MyNetCallBack callBack) {

        Map<String,String> map = new HashMap<>();
        map.put("mailAdd",emile);
        map.put("passWd",pwd);
        map.put("verificationCode",yanzhengma);

        Map<String,String> map1 = new HashMap<>();
        try {
            map1.put("Referer", URLEncoder.encode("iPanda.Android", "UTF-8"));
            map1.put("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CNTV_MOBILE", "UTF-8"));
            map1.put("Cookie",getCookie());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        EmailUtils.getInstance().registerPost(Url.EMILEREGIS,map,map1,callBack);
    }

    @Override
    public void setEmiRegs(MyEmailCallBack callBack) {
        EmailUtils.getInstance().loginPost(Url.EMILEYANZHENG,null,null,callBack);
    }
    public String getCookie(){
        SharedPreferences cookie = App.content.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        String string = cookie.getString("Cookie", null);
        return string;
    }
}
