package com.example.administrator.pandatv.app;

import android.app.Application;

import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.net.IHttp;

/**
 * Created by Administrator on 2017/7/11.
 */

public class App extends Application {
    public static BaseActivity content;
    public static IHttp iHttp;
}
