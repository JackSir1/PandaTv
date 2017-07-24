package com.example.administrator.pandatv.net;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by 赵玮 on 2017/4/6.
 */

public class ThreadUtils {

    private  static Handler sHandler = new Handler(Looper.getMainLooper());
    public static void runOnMainThread(Runnable task){
        sHandler.post(task);
    }

    public static void runOnStubThread(Runnable task){
        new Thread(task).start();
    }
}
