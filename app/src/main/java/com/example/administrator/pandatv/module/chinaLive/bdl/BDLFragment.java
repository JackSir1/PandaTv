package com.example.administrator.pandatv.module.chinaLive.bdl;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveBDLBean;
import com.example.administrator.pandatv.module.chinaLive.adapter.BDLAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhuofang on 2017/7/13.
 */
public class BDLFragment extends BaseFragment implements BDLChinaLiveContract.View {
    BDLChinaLiveContract.Presenter presenter;
    PullToRefreshRecyclerView bdlframent;
    private BDLAdapter bdlAdapter;
    private List<LiveBDLBean.LiveBean> mList;
    private Bundle bundle;

    @Override
    protected int getViweId() {
        return R.layout.bdlfragment;
    }

    @Override
    protected void initView(View view) {
        bdlframent = (PullToRefreshRecyclerView) view.findViewById(R.id.bdlframent);
    }

    @Override
    protected void loadDate() {
        mList = new ArrayList<>();
        bdlframent.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        bdlframent.setLoadingMoreEnabled(true);
        bdlframent.setPullRefreshEnabled(true);
        new BDLChinaLivePresenterTS(this);
        if(bundle!=null) {
            String url = bundle.getString("url");
            presenter.setUrl(url);
        }
    }

    @Override
    protected void setListener() {
        bdlframent.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                bdlframent.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadDate();
                        bdlframent.setRefreshComplete();
                        bdlAdapter.notifyDataSetChanged();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                bdlframent.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadDate();
                        bdlframent.setLoadMoreComplete();
                        bdlAdapter.notifyDataSetChanged();
                    }
                },2000);
            }
        });
    }

    @Override
    public void onShowDialog() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void setResult(LiveBDLBean tablistBean) {
        List<LiveBDLBean.LiveBean> live = tablistBean.getLive();
        Log.e("bdffragment", "请求，，，" + live);
        mList.addAll(live);
        bdlAdapter = new BDLAdapter(getContext(), mList);
        bdlframent.setAdapter(bdlAdapter);
    }

    @Override
    public void setParams(Bundle bundle) {
        this.bundle=bundle;
    }

    //presenter报空的话，是一个fragment对应一个presenter
    @Override
    public void setPresenter(BDLChinaLiveContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
