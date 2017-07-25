package com.example.administrator.pandatv.module;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.util.ACache;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/24.
 */
public class UpdateInfo extends BaseActivity {
    @BindView(R.id.updateinfo_return)
    ImageView updateinfoReturn;
    @BindView(R.id.updateinfo_save)
    TextView updateinfoSave;
    @BindView(R.id.updateinfo_edit)
    EditText updateinfoEdit;
    private String updateName;

    @Override
    protected int getViewID() {
        return R.layout.updateinfo;
    }

    @Override
    protected void initView() {
        Intent intent=getIntent();
        updateName = intent.getStringExtra("UpdateName");
        updateinfoEdit.setText(updateName);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setIntent() {
        AlertDialog.Builder dilog = new AlertDialog.Builder(this);
        dilog.setMessage("您的头像已提交审核请稍后回来确认吧");
        dilog.setNegativeButton("我知道啦", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.updateinfo_return, R.id.updateinfo_save, R.id.updateinfo_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updateinfo_return:
                finish();
                break;
            case R.id.updateinfo_save:
                ACache cache=ACache.get(this);
                cache.put("updatename",updateName);
                Intent intent=getIntent();
                intent.putExtra("updatena",updateinfoEdit.getText().toString());
                setResult(1,intent);
                finish();
                break;
            case R.id.updateinfo_edit:
                String trim = updateinfoEdit.getText().toString().trim();
                if(trim.equals(updateName)) {
                    updateinfoSave.setTextColor(getResources().getColor(R.color.colorshenhui));
                }else{
                    updateinfoSave.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                break;
        }
    }
}
