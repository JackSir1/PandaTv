package com.example.administrator.pandatv.module.chinaLive.fragment.email;

import android.graphics.drawable.Drawable;

import com.example.administrator.pandatv.base.BasePresenter;
import com.example.administrator.pandatv.base.BaseView;

/**
 * Created by lizhuofang on 2017/7/22.
 */

public interface EmailContract {
    interface View extends BaseView<Presenter> {

        void setYanZheng(Drawable drawable);
        void setLog(String string);
    }
    interface Presenter extends BasePresenter {
        void getRegister(String emile,String pwd,String yzm);

    }
}
