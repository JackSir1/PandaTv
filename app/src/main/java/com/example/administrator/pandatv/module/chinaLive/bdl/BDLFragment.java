package com.example.administrator.pandatv.module.chinaLive.bdl;

import android.os.Bundle;
import android.os.Handler;
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

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by lizhuofang on 2017/7/13.
 */
public class BDLFragment extends BaseFragment implements BDLChinaLiveContract.View {
    BDLChinaLiveContract.Presenter presenter;
    RecyclerView bdlframent;
    private Bdadapter bdlAdapter;
    private List<LiveBDLBean.LiveBean> mList;
    private Bundle bundle;
    private PtrClassicFrameLayout ptrFrameLayout;
    Handler handler=new Handler();
    int pager=0;
    @Override
    protected int getViweId() {
        return R.layout.bdlfragment;
    }

    @Override
    protected void initView(View view) {
        bdlframent = (RecyclerView) view.findViewById(R.id.bdlframent);
        ptrFrameLayout= (PtrClassicFrameLayout) view.findViewById(R.id.main_ptr);
    }

    @Override
    protected void loadDate() {
        mList = new ArrayList<>();
        bdlframent.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        new BDLChinaLivePresenterTS(this);
        if(bundle!=null) {
            String url = bundle.getString("url");
            presenter.setUrl(url);
        }
    }

    @Override
    protected void setListener() {
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.autoRefresh(true);
            }
        },2000);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        pager++;
//                        loadDate();
//                        bdlAdapter.notifyDataSetChanged();
//                        ptrFrameLayout.setLoadingMinTime(1000);

                    }
                }, 1000);

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        loadDate();
                        bdlAdapter.notifyDataSetChanged();
                        ptrFrameLayout.refreshComplete();
                        ptrFrameLayout.autoLoadMore(true);
                    }
                }, 1000);

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
        bdlAdapter = new Bdadapter(getContext(), mList);
        bdlframent.setAdapter(bdlAdapter);
        bdlAdapter.notifyDataSetChanged();
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
