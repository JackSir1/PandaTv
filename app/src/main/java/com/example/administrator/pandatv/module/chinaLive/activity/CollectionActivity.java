package com.example.administrator.pandatv.module.chinaLive.activity;

import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class CollectionActivity extends BaseActivity {
    @BindView(R.id.collection_iv)
    ImageView collectionIv;
    @BindView(R.id.livechina_coll_tab)
    TableLayout livechinaCollTab;

    @Override
    protected int getViewID() {
        return R.layout.livechina_collection;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setIntent() {

    }



    @OnClick(R.id.collection_iv)
    public void onClick() {
        finish();
    }
}
