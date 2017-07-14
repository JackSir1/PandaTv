package com.example.administrator.pandatv.module.pandaLive.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;
import com.example.administrator.pandatv.module.pandaLive.budpull.BudContract;
import com.example.administrator.pandatv.module.pandaLive.fragment.adapter.SplendidAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//熊猫TOP榜
public class PandaTopFragment extends BaseFragment implements BudContract.view {

    BudContract.Presenter presenter;
    @BindView(R.id.recycler)
    PullToRefreshRecyclerView recycler;
    private int Index = 0;
    private List<PandaLiveSplendidBean.ListBean> arraylist = new ArrayList<>();


    @Override
    protected int getViweId() {
        return R.layout.pandalive_top;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadDate() {
        presenter.start();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public void setPresenter(BudContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgresDialog() {

    }

    @Override
    public void dismeissDiolog() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setResult(PandaLiveSplendidBean pandaLiveSplendidBean) {
        for (int i = 0; i < pandaLiveSplendidBean.getList().size(); i++) {
            PandaLiveSplendidBean.ListBean listBean = pandaLiveSplendidBean.getList().get(i);
            arraylist.add(listBean);
        }

        SplendidAdapter adapter = new SplendidAdapter(getContext(), arraylist);
        recycler.setPullRefreshEnabled(true);
        recycler.setLoadingMoreEnabled(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        recycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                recycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recycler.setRefreshComplete();
                        arraylist.clear();
                        loadDate();
                    }
                },2000);

            }

            @Override
            public void onLoadMore() {
                recycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recycler.setLoadMoreComplete();
                        Index++;
                        loadDate();
                    }
                },2000);
            }
        });

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

    }
}
