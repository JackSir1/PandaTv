package com.example.administrator.pandatv.module.chinaLive.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.biz.chinaModel.ChinaLiveModel;
import com.example.administrator.pandatv.model.biz.chinaModel.IChinaLiveModel;
import com.example.administrator.pandatv.model.entity.livechinaEntity.CehuaBean;
import com.example.administrator.pandatv.net.CallBack.MyNetCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CehuaActivity extends BaseActivity implements PullToRefreshListener {

    @BindView(R.id.historical_image)
    ImageView historicalImage;
    @BindView(R.id.cehua_pullto)
    PullToRefreshRecyclerView cehuaPullto;
    private CehuaAdapter adapter;
    private List<CehuaBean.InteractiveBean> list;

    @Override
    protected int getViewID() {
        return R.layout.activity_cehua;
    }

    @Override
    protected void initView() {


        list = new ArrayList<>();
        adapter = new CehuaAdapter(this, list);
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

        IChinaLiveModel iChinaLiveModel = new ChinaLiveModel();
        iChinaLiveModel.getLivechinayuanchuang(new MyNetCallBack<CehuaBean>() {
            @Override
            public void onSuccess(CehuaBean cehuaBean) {
                list.addAll(cehuaBean.getInteractive());
                Log.e("TAG", list.size() + "");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {

            }

        });

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
        },1000);
    }

    @Override
    public void onLoadMore() {

        cehuaPullto.setLoadMoreComplete();
    }
}
