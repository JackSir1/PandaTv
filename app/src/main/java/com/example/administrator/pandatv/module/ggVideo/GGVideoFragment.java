package com.example.administrator.pandatv.module.ggVideo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.GGBean;
import com.example.administrator.pandatv.module.ggVideo.adapter.GGViedoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/12.
 */

public class GGVideoFragment extends BaseFragment implements GGVideoContract.View {
    @BindView(R.id.recy_gg)
    PullToRefreshRecyclerView recyGg;
    private List<GGBean.ListBean> arraylist = new ArrayList<>();
    GGVideoContract.Presenter presenter;
    private int index = 0;


    @Override
    protected int getViweId() {
        return R.layout.ggviedo;
    }

    @Override
    protected void initView(View view) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.ggimage, null, false);
        recyGg.addHeaderView(inflate);
        recyGg.setPullRefreshEnabled(true);
        recyGg.setLoadingMoreEnabled(true);


        recyGg.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void loadDate() {
        presenter.start();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void setPresenter(GGVideoContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgresDialog() {

    }

    @Override
    public void dismeissDiolog() {

    }

    @Override
    public void setResult(GGBean ggBean) {
        for (int i = 0; i < ggBean.getList().size(); i++) {
            GGBean.ListBean listBean = ggBean.getList().get(i);
            arraylist.add(listBean);
        }

        GGViedoAdapter adapter = new GGViedoAdapter(getContext(), arraylist);

        recyGg.setAdapter(adapter);
        recyGg.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                recyGg.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyGg.setRefreshComplete();
                        arraylist.clear();
                        loadDate();
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                recyGg.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyGg.setLoadMoreComplete();

                        index++;
                        loadDate();
                    }
                }, 2000);
            }
        });

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
