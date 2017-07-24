package com.example.administrator.pandatv.module.chinaLive.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.module.chinaLive.fragment.email.EmailContract;
import com.example.administrator.pandatv.module.chinaLive.fragment.email.EmailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.administrator.pandatv.R.id.register_youxiang_pass;
import static com.example.administrator.pandatv.R.id.register_youxiang_receivebut;
import static com.example.administrator.pandatv.R.id.register_youxiang_yanzhengma;
import static com.example.administrator.pandatv.R.id.register_youxiang_zhanghao;

/**
 * Created by lizhuofang on 2017/7/18.
 */
public class YouXiangRegiFrag extends BaseFragment implements EmailContract.View{
    EmailContract.Presenter presenter;
    @BindView(R.id.register_youxiang_zhanghao)
    EditText registerYouxiangZhanghao;
    @BindView(R.id.register_youxiang_pass)
    EditText registerYouxiangPass;
    @BindView(R.id.register_youxiang_querenpass)
    EditText registerYouxiangQuerenpass;
    @BindView(register_youxiang_yanzhengma)
    EditText registerYouxiangYanzhengma;
    @BindView(register_youxiang_receivebut)
    ImageView registerYouxiangReceivebut;
    @BindView(R.id.register_shouji_radiobutton)
    RadioButton registerShoujiRadiobutton;
    @BindView(R.id.register_youxiang_button)
    Button registerYouxiangButton;
    Unbinder unbinder;

    @Override
    protected int getViweId() {
        return R.layout.register_youxiangregister;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadDate() {
        new EmailPresenter(this);
        presenter.start();
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
    }

    @OnClick({register_youxiang_zhanghao, register_youxiang_pass, R.id.register_youxiang_querenpass, register_youxiang_yanzhengma, register_youxiang_receivebut, R.id.register_youxiang_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case register_youxiang_zhanghao:
                break;
            case register_youxiang_pass:
                break;
            case R.id.register_youxiang_querenpass:
                break;
            case register_youxiang_yanzhengma:
                break;
            case register_youxiang_receivebut:
                presenter.start();
                break;
            case R.id.register_youxiang_button:
                presenter.getRegister(registerYouxiangZhanghao.getText().toString()
                        , registerYouxiangPass.getText().toString()
                        , registerYouxiangYanzhengma.getText().toString());
                getActivity().finish();
                Toast.makeText(getContext(), "邮箱注册成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void setYanZheng(Drawable drawable) {
        registerYouxiangReceivebut.setImageDrawable(drawable);
    }

    @Override
    public void setLog(String string) {

    }

    @Override
    public void setPresenter(EmailContract.Presenter presenter) {
        this.presenter =  presenter;
    }
}
