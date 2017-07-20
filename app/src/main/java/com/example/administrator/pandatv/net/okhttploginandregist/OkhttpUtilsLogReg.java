package com.example.administrator.pandatv.net.okhttploginandregist;

import android.content.SharedPreferences;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;
import com.example.administrator.pandatv.net.IHttp;
import com.example.administrator.pandatv.net.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lizhuofang on 2017/7/19.
 */

public class OkhttpUtilsLogReg implements IHttp {
    private static OkhttpUtilsLogReg httpUtils = new OkhttpUtilsLogReg();
    private OkHttpClient okHttpClient;
    private static OkHttpUtils okHttpUtils;
    private SharedPreferences mPreferences;

    private OkhttpUtilsLogReg() {
        okHttpClient=new OkHttpClient();
    }

    public static OkhttpUtilsLogReg getInstance() {
        return httpUtils;
    }


    @Override
    public <T> void get(String url, Map<String, String> map, final MyNetCallBack<T> callBack) {
        StringBuffer sb=new StringBuffer(url);
        if (map !=null && map.size()>0){
            sb.append("?");
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                String value = map.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url=sb.deleteCharAt(sb.length()-1).toString();
        }

        Request request = null;
        try {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(getGeneric(jsonData,callBack));
                    }
                });
            }
        });
    }

    @Override
    public <T> void post(String url, Map<String, String> map, final MyNetCallBack<T> callBack) {
        StringBuffer sb=new StringBuffer(url);
        if (map !=null && map.size()>0){
            sb.append("?");
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                String value = map.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url=sb.deleteCharAt(sb.length()-1).toString();
        }

        Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Referer","https://reg.cntv.cn/login/login.action")
                    .addHeader("User-Agent","CNTV_APP_CLIENT_CYNTV_MOBILE")
                    .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(getGeneric(jsonData,callBack));
                    }
                });
            }
        });


    }

    @Override
    public void upload() {

    }

    @Override
    public void downLoad() {

    }

    @Override
    public void loadImage() {

    }
    /**
     * 自动解析json至回调中的JavaBean
     * @param jsonData
     * @param callBack
     * @param <T>
     * @return
     */
    private <T> T getGeneric(String jsonData,MyNetCallBack<T> callBack){
        Gson gson = new Gson();
        //通过反射获取泛型的实例
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        T t = gson.fromJson(jsonData,type);
        return t;
    }
}
