package com.example.administrator.pandatv.module.chinaLive.fragment.email;

import android.graphics.drawable.Drawable;

import com.example.administrator.pandatv.model.biz.chinaModel.EmailImp;
import com.example.administrator.pandatv.model.biz.chinaModel.EmailModel;
import com.example.administrator.pandatv.net.CallBack.MyEmailCallBack;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

/**
 * Created by lizhuofang on 2017/7/22.
 */

public class EmailPresenter implements EmailContract.Presenter {
    private EmailContract.View emileView;
    private EmailModel emileMoudle;
    public EmailPresenter(EmailContract.View emileView){
        emileView.setPresenter(this);
        this.emileView = emileView;
        emileMoudle = new EmailImp();

    }

    @Override
    public void start() {
        emileMoudle.setEmiRegs(new MyEmailCallBack() {
            @Override
            public void onSuccess(Drawable drawable) {
                emileView.setYanZheng(drawable);
            }

            @Override
            public void onErrorr(String msg) {

            }
        });
    }

    @Override
    public void getRegister(String emile, String pwd, String yzm) {
        emileMoudle.getEmileRegister(emile, pwd, yzm, new MyNetCallBack() {

            @Override
            public void onSuccess(Object o) {
                emileView.setLog((String) o);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
