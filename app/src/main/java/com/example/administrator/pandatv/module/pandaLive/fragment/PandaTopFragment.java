package com.example.administrator.pandatv.module.pandaLive.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.biz.pandaLiveModel.IPandaLiveModel;
import com.example.administrator.pandatv.model.biz.pandaLiveModel.PandaLiveModel;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;
import com.example.administrator.pandatv.module.pandaLive.budpull.BudContract;
import com.example.administrator.pandatv.module.pandaLive.fragment.adapter.SplendidAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//熊猫TOP榜
public class PandaTopFragment extends BaseFragment implements BudContract.view {
    private IPandaLiveModel iPandaLiveModel;


    BudContract.Presenter presenter;
    @BindView(R.id.recycler)
    PullToRefreshRecyclerView recycler;
    private int Index = 1;
    private List< PandaLiveSplendidBean.VideoBean> arraylist = new ArrayList<>();


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
        //vsid=VSET100167216881&n=7&serviceId=panda&o=desc&of=time&p=1";
       // String o,String of
        //hvsid=VSET100167216881&n=7&serviceId=panda&o=desc&of=time&p=1
        presenter.GetData("VSET100167216881", "7","panda","desc","time", Index+"");

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
        this.iPandaLiveModel=new PandaLiveModel();

        /*for (int i = 0; i < pandaLiveSplendidBean.getVideo().size(); i++) {
            PandaLiveSplendidBean.VideoBean videoBean = pandaLiveSplendidBean.getVideo().get(i);

            arraylist.add(videoBean);
        }*/
        List<PandaLiveSplendidBean.VideoBean> video = pandaLiveSplendidBean.getVideo();
         arraylist.addAll(video);
        final SplendidAdapter adapter = new SplendidAdapter(getContext(), arraylist);
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
                        Index=1;
                        presenter.GetData("VSET100167216881", "7","panda","desc","time","1" );
                        adapter.notifyDataSetChanged();
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
                        String i=Index+"";
                        Log.e("TAG",Index+"");
                        //http://api.cntv.cn/video/videolistById?
                        //hvsid=VSET100167216881&n=7&serviceId=panda&o=desc&of=time&p=1
                        presenter.GetData("VSET100167216881", "7","panda","desc","time",  i);

                        adapter.notifyDataSetChanged();
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
