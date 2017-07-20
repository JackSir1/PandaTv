package com.example.administrator.pandatv.model.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.example.administrator.pandatv.R;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ShowPopuUtils {
    private static ShowPopuUtils showPopuUtils;
    private ShowPopuUtils(){}
    private PopupWindow  popupWindow;
    private ProgressDialog dialog;
    private Dialog loadDialog;
    public static ShowPopuUtils getInsent(){
        if (showPopuUtils==null){
            synchronized (ShowPopuUtils.class){
                if (showPopuUtils==null){
                    showPopuUtils=new ShowPopuUtils();
                }
            }
        }
        return showPopuUtils;
    }
    public ShowPopuUtils create(Context context){

//        View inflate = LayoutInflater.from(context).inflate(R.layout.progressdialog_item, null);
//        View view = LayoutInflater.from(context).inflate(R.layout.activity_main, null);


        loadDialog = new Dialog(context, R.style.dialog);
        loadDialog.setCanceledOnTouchOutside(false);

        loadDialog.setContentView(R.layout.progressdialog_item);
        loadDialog.show();

//        dialog = new ProgressDialog(context);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
//        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
//        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
//        dialog.setIcon(R.drawable.ic_launcher);//
//        dialog.setView(inflate);
//        dialog.show();


//        LinearLayout viewById = (LinearLayout) view.findViewById(R.id.main_linearLayout);
//        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setOutsideTouchable(false);
//        popupWindow.showAsDropDown(viewById);
        return this;
    }
    public ShowPopuUtils popuUtilsDismiss(){
//        popupWindow.dismiss();
        loadDialog.dismiss();
        return this;
    }
}
