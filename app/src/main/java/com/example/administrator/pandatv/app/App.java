package com.example.administrator.pandatv.app;

import android.app.Application;

import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.config.crash.CrashHandler;
import com.example.administrator.pandatv.net.IHttp;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/7/11.
 */

public class App extends Application {
    public static BaseActivity content;
    public static RegisterActivity activity;
    public static IHttp iHttp;

    @Override
    public void onCreate() {
        super.onCreate();
        MobclickAgent.setDebugMode( true );
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("2699399578", "2a6cf5946a9cf8407923d46ce23e09d7", "http://sns.whalecloud.com");
        ShareSDK.initSDK(this);
        SMSSDK.initSDK(this, "1e506690017a4", "a627c68689e042e44ed4177f45e65638");

//        CrashHandler.getInstance().init(this);//初始化全局异常管理
    }

}
