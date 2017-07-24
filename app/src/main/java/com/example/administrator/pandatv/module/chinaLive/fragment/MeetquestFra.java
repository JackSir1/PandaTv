package com.example.administrator.pandatv.module.chinaLive.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lizhuofang on 2017/7/15.
 */
public class MeetquestFra extends BaseFragment {
    @BindView(R.id.meetquestion_cb1)
    CheckBox meetquestionCb1;
    @BindView(R.id.meetquestion_cb2)
    CheckBox meetquestionCb2;
    @BindView(R.id.meetquestion_cb3)
    CheckBox meetquestionCb3;
    @BindView(R.id.meetquestion_cb4)
    CheckBox meetquestionCb4;
    @BindView(R.id.meetquestion_cb5)
    CheckBox meetquestionCb5;
    @BindView(R.id.meetquestion_cb6)
    CheckBox meetquestionCb6;
    @BindView(R.id.meetquestion_ider)
    EditText meetquestionider;
    @BindView(R.id.meetquestion_contact)
    EditText meetquestionContact;
    @BindView(R.id.meetquestion_but_submit)
    Button meetquestionButSubmit;
    Unbinder unbinder;

    @Override
    protected int getViweId() {
        return R.layout.userfankui_meetquestion;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadDate() {

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

    @OnClick(R.id.meetquestion_but_submit)
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.meetquestion_but_submit:
                String question = meetquestionContact.getText().toString().trim();
                String questionider = meetquestionider.getText().toString().trim();
                if(TextUtils.isEmpty(question)&&TextUtils.isEmpty(questionider)) {
                    Toast.makeText(getContext(), "请填写参数", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
