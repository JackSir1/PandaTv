package com.example.administrator.pandatv.module.pandaLive.fragment;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.PandaLiveMoreBean;
import com.example.administrator.pandatv.module.pandaLive.fragment.adapter.MoreLiveAdapter;
import com.example.administrator.pandatv.module.pandaLive.live.moresight.MorSightContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//原创新闻
public class MoreSightFragment extends BaseFragment implements MorSightContract.view {
  MorSightContract.Presenter presenter;
    @BindView(R.id.mRecyclerview)
    PullToRefreshRecyclerView mRecyclerview;
    private List<PandaLiveMoreBean.ListBean> arraylist = new ArrayList<>();
    private MoreLiveAdapter adapter;

    @Override
    protected int getViweId() {
        return R.layout.pandalive_moresightl;
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
    public void setPresenter(MorSightContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setResult(PandaLiveMoreBean pandaLiveMoreBean) {
        for (int i = 0; i < pandaLiveMoreBean.getList().size(); i++) {
            final PandaLiveMoreBean.ListBean daytime = pandaLiveMoreBean.getList().get(i);
              arraylist.add(daytime);

        }

        mRecyclerview.setPullRefreshEnabled(false);
        mRecyclerview.setLoadingMoreEnabled(false);
        adapter = new MoreLiveAdapter(getContext(), arraylist);
        mRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerview.setAdapter(adapter);

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


}
