package com.example.administrator.pandatv.model.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.administrator.pandatv.app.App;

import java.util.Stack;

/**
 * Created by Administrator on 2017/7/13.
 */

public class ShowDialogUtils {
    private IShowDialogUtils iShowDialogUtils;
    private static ShowDialogUtils showDialogUtils;
    private AlertDialog.Builder dialog;
    private ShowDialogUtils(){
        dialog = new AlertDialog.Builder(App.content);
    }
    public static ShowDialogUtils getInsenter(){
        if (showDialogUtils==null)
            synchronized (ShowDialogUtils.class){
                if (showDialogUtils==null){
                    showDialogUtils=new ShowDialogUtils();
                }
            }
        return showDialogUtils;
    }
    public ShowDialogUtils setViewId(){
        dialog.setTitle("当前处于移动网络是否继续播放？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iShowDialogUtils.setMaked();;
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create().show();
        return this;
    }
    public void setOnYesDialogMake(IShowDialogUtils iShowDialogUtils){
        this.iShowDialogUtils=iShowDialogUtils;
    }
}
