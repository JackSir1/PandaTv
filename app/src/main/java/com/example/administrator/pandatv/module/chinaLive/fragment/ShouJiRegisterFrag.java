package com.example.administrator.pandatv.module.chinaLive.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.util.ACache;
import com.example.administrator.pandatv.net.ThreadUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    @BindView(R.id.shouji_photo)
    ImageView shoujiPhoto;
    @BindView(R.id.phone_red_number)
    TextView phoneRedNumber;
    @BindView(R.id.phone_red_photo)
    TextView phoneRedPhoto;
    @BindView(R.id.phone_red_pass)
    TextView phoneRedPass;
    Unbinder unbinder1;
    private int a = 60;
    private String phone;
    private String pass;
    private String receive;
    private byte[] bytes;
    private String jsonid;
    private OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    private ACache aCache;
    private String zhanghao;
    private String photoyanzheng;

    @Override
    protected int getViweId() {
        return R.layout.register_shoujiregister;
    }

    @Override
    protected void initView(View view) {

        aCache = ACache.get(App.activity);
    }

    @Override
    protected void loadDate() {
        getPhoto();
    }

    @Override
    protected void setListener() {

    }


    /*
    * 图片验证码
    * */
    private void getPhoto() {
        String from = "http://reg.cntv.cn/simple/verificationCode.action";
        final Request request = new Request.Builder()
                .url(from)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                bytes = response.body().bytes();
                Headers headers = response.headers();
                jsonid = headers.get("Set-Cookie");

                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        shoujiPhoto.setImageDrawable(byteToDrawable(bytes));
                    }
                });
            }
        });
    }

    public Drawable byteToDrawable(byte[] buteArray) {
        try {
            String string = new String(buteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ByteArrayInputStream ins = new ByteArrayInputStream(buteArray);
        return Drawable.createFromStream(ins, null);
    }

    /*
    * 短信验证码
    * */
    private void getMessage() {
        String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
        String from = "http://cbox_mobile.regclientuser.cntv.cn";
//                    手机号
        String phoneNumber = registerShoujiZhanghao.getText().toString().trim();
//                    验证码
        String imgyanzhengma = registerShoujiPhotoyanzheng.getText().toString().trim();

        RequestBody body = new FormBody.Builder()
                .add("method", "getRequestVerifiCodeM")
                .add("mobile", phoneNumber)
                .add("verfiCodeType", "1")
                .add("verificationCode", imgyanzhengma)
                .build();

        try {
            Request request = new Request.Builder().url(url)
                    .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .addHeader("Cookie", jsonid)
                    .post(body).build();
            aCache.put("Cookie", jsonid);
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("TAG", "失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    Log.i("TAG", "手机验证码打印：" + string);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /*
    * 注册
    * */
    private void register() {
        String url = "https://reg.cntv.cn/regist/mobileRegist.do";
        RequestBody requestBody = null;
        try {
            requestBody = new FormBody.Builder()
                    .add("method", "saveMobileRegisterM")
                    .add("mobile", registerShoujiZhanghao.getText().toString().trim())
                    .add("verfiCode", registerShoujiRecivie.getText().toString().toString())
                    .add("passWd", URLEncoder.encode(registerShoujiWritepass.getText().toString().trim(), "UTF-8"))
                    .add("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        OkHttpClient okHttpClient1 = new OkHttpClient();
        Request request2 = null;
        try {
            request2 = new Request.Builder()
                    .url(url)
                    .addHeader("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .post(requestBody)
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        okHttpClient1.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("lianxi", "result:" + e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("Main", "请求结果" + response.body().string());
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @OnClick({R.id.register_shouji_zhanghao,R.id.register_shouji_writepass,R.id.register_shouji_photoyanzheng,R.id.shouji_photo,R.id.register_shouji_receivebut, R.id.register_shouji_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_shouji_zhanghao:
                    phoneRedNumber.setVisibility(View.GONE);
                break;
            case R.id.register_shouji_writepass:
                if(TextUtils.isEmpty(zhanghao)&&TextUtils.isEmpty(photoyanzheng)) {
                    phoneRedNumber.setVisibility(View.VISIBLE);
                    phoneRedPhoto.setVisibility(View.VISIBLE);
                }else{
                    phoneRedNumber.setVisibility(View.GONE);
                    phoneRedPhoto.setVisibility(View.GONE);
                }
                break;
            case R.id.register_shouji_photoyanzheng:
                if(TextUtils.isEmpty(zhanghao)) {
                        phoneRedNumber.setVisibility(View.VISIBLE);
                }else {
                    phoneRedNumber.setVisibility(View.GONE);
                }
                break;
            case R.id.shouji_photo:
                getPhoto();
                break;
            case R.id.register_shouji_receivebut:
                zhanghao = registerShoujiZhanghao.getText().toString().trim();
                photoyanzheng = registerShoujiPhotoyanzheng.getText().toString().trim();
                if(TextUtils.isEmpty(zhanghao)&&TextUtils.isEmpty(photoyanzheng)) {
                    phoneRedNumber.setVisibility(View.VISIBLE);
                    phoneRedPhoto.setVisibility(View.VISIBLE);
                }else{
                    phoneRedNumber.setVisibility(View.GONE);
                    phoneRedPhoto.setVisibility(View.GONE);
                }
                if (a > 0) {
                    Timer ti = new Timer();
                    ti.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            a--;
                        }
                    }, 1000);
                    registerShoujiReceivebut.setText("重新发送" + "(" + ti + ")");
                    getMessage();
                } else {
                    a = 60;
                    registerShoujiReceivebut.setText("获取验证码成功");
                }


                break;
            case R.id.register_shouji_button:
                register();
                break;
        }
    }

}
