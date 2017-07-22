package com.example.administrator.pandatv.model.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.administrator.pandatv.app.App;

/**
 * Created by Administrator on 2017/7/13.
 */

public class ShowDialogUtils {
    private static ShowDialogUtils showDialogUtils;
    private AlertDialog.Builder dialog;

    private ShowDialogUtils() {
        dialog = new AlertDialog.Builder(App.content);
    }

    public static ShowDialogUtils getInsenter() {
        if (showDialogUtils == null)
            synchronized (ShowDialogUtils.class) {
                if (showDialogUtils == null) {
                    showDialogUtils = new ShowDialogUtils();
                }
            }
        return showDialogUtils;
    }

    public ShowDialogUtils setViewId(final IShowDialogUtils ShowDialogUtils) {
        if (netType() == 1) {
            dialog.setTitle("您正在使用移动数据网络，所产生的流量费由当地运营商收取，是否继续？");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ShowDialogUtils.setMaked();
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.create().show();
        }
        return this;
    }

    private Boolean wifiConnected, mobileConnected;

    private void updateConnectedFlags(Context context) {
        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        } else {
            wifiConnected = false;
            mobileConnected = false;
        }
    }

    private static final int MOBILETYPE = 1;
    private static final int NULLNET = 0;
    private static final int WIFITYPE = 2;

    public int netType() {
        if (wifiConnected)
            return WIFITYPE;
        if (mobileConnected)
            return MOBILETYPE;
        return NULLNET;
    }
}
