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

    private ShowPopuUtils() {
    }
    private Dialog loadDialog;

    public static ShowPopuUtils getInsent() {
        if (showPopuUtils == null) {
            synchronized (ShowPopuUtils.class) {
                if (showPopuUtils == null) {
                    showPopuUtils = new ShowPopuUtils();
                }
            }
        }
        return showPopuUtils;
    }

    public ShowPopuUtils create(Context context) {

        loadDialog = new Dialog(context, R.style.dialog);
        loadDialog.setCanceledOnTouchOutside(false);

        loadDialog.setContentView(R.layout.progressdialog_item);
        loadDialog.show();

        return this;
    }

    public ShowPopuUtils popuUtilsDismiss() {

        loadDialog.dismiss();
        return this;
    }
}
