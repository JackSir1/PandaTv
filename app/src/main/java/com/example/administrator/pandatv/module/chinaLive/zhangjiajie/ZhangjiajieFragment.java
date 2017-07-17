package com.example.administrator.pandatv.module.chinaLive.zhangjiajie;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.livechinaEntity.ZhangJiajie;
import com.example.administrator.pandatv.module.chinaLive.adapter.ZhangjiajieAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class ZhangjiajieFragment extends BaseFragment implements ZhangjiajieContract.View {
    ZhangjiajieContract.Presenter presenter;
    @BindView(R.id.pulltorefresh)
    PullToRefreshRecyclerView pullframent;
    Unbinder unbinder;
    private ZhangjiajieAdapter songshanAdapter;
    private List<ZhangJiajie.LiveBean> mList;
    @Override
    protected int getViweId() {
        return R.layout.livechina_songfra;
    }

    @Override
    protected void initView(View view) {
        mList=new ArrayList<>();
        pullframent.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        pullframent.setPullRefreshEnabled(true);
        pullframent.setLoadingMoreEnabled(true);

        new ZhangjiajiePresenter(this);
    }

    @Override
    protected void loadDate() {

        presenter.start();
    }

    @Override
    protected void setListener() {
        pullframent.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pullframent.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        loadDate();
                        songshanAdapter.notifyDataSetChanged();
                        pullframent.setRefreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {

//                loadDate();
//                songshanAdapter.notifyDataSetChanged();
                pullframent.setLoadMoreComplete();
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
    public void setResult(ZhangJiajie songShanBean) {
        List<ZhangJiajie.LiveBean> live =songShanBean.getLive();
        mList.addAll(live);
        songshanAdapter =new ZhangjiajieAdapter(getContext(),mList);
        pullframent.setAdapter(songshanAdapter);
        songshanAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(ZhangjiajieContract.Presenter presenter) {
        this.presenter = presenter;
    }


}
