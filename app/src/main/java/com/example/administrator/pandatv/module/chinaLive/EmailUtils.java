package com.example.administrator.pandatv.module.chinaLive;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.net.CallBack.MyEmailCallBack;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;
import com.example.administrator.pandatv.net.IHttp;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/21.
 */

public class EmailUtils implements IHttp {
    private String jsonId;
    byte[] bytes;
    private OkHttpClient okHttpClient;

    //构造函数私有化
    private EmailUtils() {
        this.okHttpClient = new OkHttpClient.Builder().build();
    }


    private static EmailUtils okHttpUtils;

    public static EmailUtils getInstance() {
        //加锁判断如果为空就创建对象
        if (okHttpUtils == null) {
            synchronized (EmailUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new EmailUtils();
                }
            }
        }
        //判断完成后返回工具类单例对象
        return okHttpUtils;
    }

    public void loginPost(String url, Map<String, String> parmers, Map<String, String> heards, final MyEmailCallBack callBack) {
        StringBuffer sb = new StringBuffer(url);
        if (parmers != null && parmers.size() > 0) {
            sb.append("?");
            Set<String> strings = parmers.keySet();
            for (String string : strings) {
                String s = parmers.get(string);
                sb.append(string).append("=").append(s).append("&");
            }
            url = sb.deleteCharAt(sb.length() - 1).toString();
        }
        Request.Builder builder = new Request.Builder();

        if (heards != null && heards.size() > 0) {
            Set<String> strings = heards.keySet();
            for (String string : strings) {
                String s = heards.get(string);
                builder.addHeader(string, s);
            }
        }
       Request request = builder.url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //okhttp请求失败的回调
                Log.d("OkHttpUtils", "网络请求异常" + e.toString());
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callBack.onErrorr("执行了网络请求异常");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers headers = response.headers();
                jsonId = headers.get("Set-Cookie");
                bytes = response.body().bytes();
                //执行在子线程中
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        saveCookie(jsonId);
                        Drawable drawable = byteToDrawable(bytes);

                        callBack.onSuccess(drawable);
                    }
                });

            }
        });
    }

    public void registerPost(String url, Map<String, String> parmers, Map<String, String> heards, final MyNetCallBack callBack) {
        StringBuffer sb = new StringBuffer(url);
        if (parmers != null && parmers.size() > 0) {
            sb.append("?");
            Set<String> strings = parmers.keySet();
            for (String string : strings) {
                String s = parmers.get(string);
                sb.append(string).append("=").append(s).append("&");
            }
            url = sb.deleteCharAt(sb.length() - 1).toString();
        }
        Request.Builder builder = new Request.Builder();

        if (heards != null && heards.size() > 0) {
            Set<String> strings = heards.keySet();
            for (String string : strings) {
                String s = heards.get(string);
                builder.addHeader(string, s);
            }
        }
        Request request = builder.url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //okhttp请求失败的回调
                Log.d("OkHttpUtils", "网络请求异常" + e.toString());
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callBack.onError("执行了网络请求异常");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
                //执行在子线程中
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        callBack.onSuccess(Drawable.createFromPath(s));
                    }
                });

            }
        });
    }
    public void saveCookie(String value){
        SharedPreferences cookie = App.content.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = cookie.edit();
        edit.putString("Cookie",value);
        edit.commit();
    }


    @Override
    public <T> void get(String url, Map<String, String> map, MyNetCallBack<T> callBack) {

    }

    @Override
    public <T> void post(String url, Map<String, String> map, MyNetCallBack<T> callBack) {

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
    private <T> T getGeneric(String jsonData,MyNetCallBack<T> callBack){
        Gson gson = new Gson();
        //通过反射获取泛型的实例
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        T t = gson.fromJson(jsonData,type);
        return t;
    }

    public static Drawable byteToDrawable(byte[] byteArray) {
        try {
            String string = new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ByteArrayInputStream ins = new ByteArrayInputStream(byteArray);
        return Drawable.createFromStream(ins, null);
    }

}
