package com.example.administrator.pandatv.module.chinaLive.bdl;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveBDLBean;
import com.example.administrator.pandatv.module.chinaLive.adapter.Bdadapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhuofang on 2017/7/13.
 */
public class BDLFragment extends BaseFragment implements ChinaLiveContract.View {
    ChinaLiveContract.Presenter presenter;
    //    @BindView(R.id.bdlframent)
    RecyclerView bdlframent;
    private Bdadapter bdlAdapter;
    private List<LiveBDLBean.LiveBean> mList;
    private ChinaLivePresenterTS chinaLivePresenterTS;

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
        chinaLivePresenterTS=new ChinaLivePresenterTS(this);
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
    public void setResult(LiveBDLBean tablistBean) {
        List<LiveBDLBean.LiveBean> live = tablistBean.getLive();
        Log.e("bdffragment", "请求，，，" + live);
        mList.addAll(live);
        bdlAdapter = new Bdadapter(getContext(), mList);
        bdlframent.setAdapter(bdlAdapter);
    }

    //presenter报空的话，是一个fragment对应一个presenter
    @Override
    public void setPresenter(ChinaLiveContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
