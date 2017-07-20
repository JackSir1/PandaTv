package com.example.administrator.pandatv.net;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/11.
 */

public class OkHttpUtils implements IHttp{
    private OkHttpClient okHttpClient;
    private static OkHttpUtils okHttpUtils;
    private OkHttpUtils(){
        okHttpClient=new OkHttpClient();
    }
    public static OkHttpUtils getInsent(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils=new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
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
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call,final IOException e) {
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  callBack.onError(e.getMessage().toString());
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
    public <T> void post(String url, Map<String, String> map,final MyNetCallBack<T> callBack) {

        FormBody.Builder builder = new FormBody.Builder();
        if(map !=null && map.size() > 0){
            Set<String> keys = map.keySet();
            for (String key : keys) {
                String value = map.get(key);
                builder.add(key,value);
            }
        }

        Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call,final IOException e) {
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
