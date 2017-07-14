package com.example.administrator.pandatv.module.chinaLive.fhgc;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.LiveFHGCBean;
import com.example.administrator.pandatv.module.chinaLive.adapter.Bdadapterfhgc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class FHGCFragment extends BaseFragment implements ChinaLivefhgcContract.View {
     ChinaLivefhgcContract.Presenter presenter;
    //    @BindView(R.id.bdlframent)
    RecyclerView bdlframent;
    private Bdadapterfhgc bdlAdapter;
    private List<LiveFHGCBean.LiveBean> mList;
    private ChinaLivePresenterFHGC chinaLivePresenterTS;

    @Override
    protected int getViweId() {
        return R.layout.bdlfragment;
    }

    @Override
    protected void initView(View view) {
        bdlframent = (RecyclerView) view.findViewById(R.id.bdlframent);
    }

    @Override
    protected void loadDate() {
        mList = new ArrayList<>();
        bdlframent.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
//        bdlframent.setLoadingMoreEnabled(true);
//        bdlframent.setPullRefreshEnabled(true);
        chinaLivePresenterTS=new ChinaLivePresenterFHGC(this);
        presenter.start();
    }

    @Override
    protected void setListener() {

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
    public void setResult(LiveFHGCBean liveFHGCBean) {
        List<LiveFHGCBean.LiveBean> live = liveFHGCBean.getLive();
        mList.addAll(live);
        bdlAdapter=new Bdadapterfhgc(getContext(),mList);
        bdlframent.setAdapter(bdlAdapter);
        bdlAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(ChinaLivefhgcContract.Presenter presenter) {
            this.presenter=presenter;
    }
}
