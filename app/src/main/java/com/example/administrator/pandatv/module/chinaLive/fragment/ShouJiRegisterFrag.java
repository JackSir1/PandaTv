package com.example.administrator.pandatv.module.chinaLive.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by lizhuofang on 2017/7/18.
 */
public class ShouJiRegisterFrag extends BaseFragment {
    @BindView(R.id.register_shouji_zhanghao)
    EditText registerShoujiZhanghao;
    @BindView(R.id.register_shouji_photoyanzheng)
    EditText registerShoujiPhotoyanzheng;
    @BindView(R.id.register_shouji_recivie)
    EditText registerShoujiRecivie;
    @BindView(R.id.register_shouji_receivebut)
    Button registerShoujiReceivebut;
    @BindView(R.id.register_shouji_writepass)
    EditText registerShoujiWritepass;
    @BindView(R.id.register_shouji_radiobutton)
    RadioButton registerShoujiRadiobutton;
    @BindView(R.id.register_shouji_button)
    Button registerShoujiButton;
    Unbinder unbinder;
    private int a = 60;
    private String phone;
    private String pass;
    private String receive;

    @Override
    protected int getViweId() {
        return R.layout.register_shoujiregister;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadDate() {
        SMSSDK.registerEventHandler(eventHandler);
        SMSSDK.getSupportedCountries();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @OnClick({R.id.register_shouji_receivebut, R.id.register_shouji_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_shouji_receivebut:
                phone = registerShoujiZhanghao.getText().toString().trim();
                receive = registerShoujiRecivie.getText().toString().trim();
                if(!TextUtils.isEmpty(phone)) {
                    SMSSDK.getVerificationCode("+86", phone, new OnSendMessageHandler() {
                        @Override
                        public boolean onSendMessage(String s, String s1) {
                            return false;
                        }
                    });
                    handler.post(runnable);
                }else{
                    Toast.makeText(getContext(), "请填写您的手机号", Toast.LENGTH_SHORT).show();
                }
                String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
                String from = "http://cbox_mobile.regclientuser.cntv.cn";
                HashMap<String, String> tHeaders = new HashMap<String, String>();
                try {

                    tHeaders.put("Referer", URLEncoder.encode(from, "UTF-8"));
                    tHeaders.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
                    tHeaders.put("Cookie", "JSESSIONID=" + 2);
                } catch (Exception e) {

                }

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("method", "getRequestVerifiCodeM");
                params.put("mobile", phone);
                params.put("verfiCodeType", "1");
                params.put("verificationCode", receive);
//                tHandler.postHeaderJson(url, tHeaders, params, 0);

                break;
            case R.id.register_shouji_button:
                pass = registerShoujiWritepass.getText().toString().trim();
                if (TextUtils.isEmpty(pass)&&pass.length() < 6 || pass.length() > 18) {


                }
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(getContext(), "短信验证成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getContext(), "短信获取成功", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    break;
            }
        }
    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (a > 0) {
                registerShoujiReceivebut.setText("正在获取验证码");
                a--;
                handler.sendEmptyMessage(2);
                handler.postDelayed(runnable, 1000);
                registerShoujiReceivebut.setClickable(false);
            }  else {
                a = 60;
                registerShoujiReceivebut.setClickable(true);
                registerShoujiReceivebut.setText("验证码获取成功");

            }
        }
    };
    private EventHandler eventHandler = new EventHandler() {

        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    HashMap<String, Object> map = (HashMap<String, Object>) data;
                    String shouji = (String) map.get("phone");
                    if (shouji.equals(phone)) {
                        handler.sendEmptyMessage(0);
                    }
                    //提交验证码成功
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    Boolean boo = (Boolean) data;
                    handler.sendEmptyMessage(1);
                    //获取验证码成功
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) data;
                    Log.e("aaa", list + "");
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };
}
