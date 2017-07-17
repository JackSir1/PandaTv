package com.example.administrator.pandatv.module.chinaLive.emeishan;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.livechinaEntity.EMeiShanBean;
import com.example.administrator.pandatv.module.chinaLive.adapter.EMeiShanAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lizhuofang on 2017/7/15.
 */

public class EMeiShanFragment extends BaseFragment implements EMeiShanContract.View {
    EMeiShanContract.Presenter presenter;
    @BindView(R.id.pulltorefresh)
    PullToRefreshRecyclerView pulltorefresh;
    Unbinder unbinder;
    private EMeiShanAdapter emeishanAdapter;
    private List<EMeiShanBean.LiveBean> mList;

    @Override
    protected int getViweId() {
        return R.layout.livechina_songfra;
    }

    @Override
    protected void initView(View view) {
        mList = new ArrayList<>();
        pulltorefresh.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        pulltorefresh.setPullRefreshEnabled(true);
        pulltorefresh.setLoadingMoreEnabled(true);

        new EMeiShanPresenter(this);

    }

    @Override
    protected void loadDate() {
        presenter.start();
    }

    @Override
    protected void setListener() {
        pulltorefresh.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pulltorefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        loadDate();
                        emeishanAdapter.notifyDataSetChanged();
                        pulltorefresh.setRefreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {

//                loadDate();
//                emeishanAdapter.notifyDataSetChanged();
                pulltorefresh.setLoadMoreComplete();
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
    public void setResult(EMeiShanBean eMeiShanBean) {
        List<EMeiShanBean.LiveBean> live = eMeiShanBean.getLive();
        mList.addAll(live);
        emeishanAdapter = new EMeiShanAdapter(getContext(), mList);
        pulltorefresh.setAdapter(emeishanAdapter);
        emeishanAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(EMeiShanContract.Presenter presenter) {
        this.presenter = presenter;
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
}
