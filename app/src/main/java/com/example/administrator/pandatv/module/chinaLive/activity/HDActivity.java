package com.example.administrator.pandatv.module.chinaLive.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.entity.livechinaEntity.CehuaBean;
import com.example.administrator.pandatv.module.chinaLive.fragment.HDContract;
import com.example.administrator.pandatv.module.chinaLive.fragment.HDPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HDActivity extends BaseActivity implements PullToRefreshListener,HDContract.View{

    @BindView(R.id.historical_image)
    ImageView historicalImage;
    @BindView(R.id.cehua_pullto)
    PullToRefreshRecyclerView cehuaPullto;
    private HDAdapter adapter;
    private List<CehuaBean.InteractiveBean> list;
    private HDContract.Presenter presenter;
    @Override
    protected int getViewID() {
        return R.layout.activity_cehua;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new HDAdapter(this, list);
        cehuaPullto.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        cehuaPullto.setLayoutManager(manager);

        cehuaPullto.setLoadingMoreEnabled(true);
        cehuaPullto.setPullRefreshEnabled(true);
        cehuaPullto.setPullToRefreshListener(this);

        initData();


    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setIntent() {

    }

    private void initData() {
        new HDPresenter(this);
        presenter.start();
    }

    @Override
    public void onRefresh() {

        cehuaPullto.postDelayed(new Runnable() {
            @Override
            public void run() {

                list.clear();
                initData();
                adapter.notifyDataSetChanged();
                cehuaPullto.setRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {

        cehuaPullto.setLoadMoreComplete();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.historical_image)
    public void onClick() {
        finish();
    }

    @Override
    public void setResult(CehuaBean cehuaBean) {
        list.addAll(cehuaBean.getInteractive());
        Log.e("TAG", list.size() + "");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(HDContract.Presenter presenter) {
        this.presenter=presenter;
    }
}
