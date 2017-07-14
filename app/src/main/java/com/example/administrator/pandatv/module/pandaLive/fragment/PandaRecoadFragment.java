package com.example.administrator.pandatv.module.pandaLive.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;
import com.example.administrator.pandatv.module.pandaLive.budpull.BudContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//熊猫档案
public class PandaRecoadFragment extends BaseFragment implements BudContract.view {
    @BindView(R.id.recycler)
    PullToRefreshRecyclerView recycler;
    private BudContract.Presenter presenter;
    Unbinder unbinder;

    @Override
    protected int getViweId() {
        return R.layout.pandalive_recode;
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
    }

    @Override
    public void setPresenter(BudContract.Presenter presenter) {
   this.presenter=presenter;
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

    }
}
