package com.example.administrator.pandatv.module.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.HomeBean;
import com.example.administrator.pandatv.module.home.viewpager.HomeViewPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/12.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.home_observer)
    TextView homeObserver;
    @BindView(R.id.home_observer_btn1)
    TextView homeObserverBtn1;
    @BindView(R.id.home_observer_title1)
    TextView homeObserverTitle1;
    @BindView(R.id.home_observer_btn2)
    TextView homeObserverBtn2;
    @BindView(R.id.home_observer_title2)
    TextView homeObserverTitle2;
    @BindView(R.id.home_live)
    TextView homeLive;
    @BindView(R.id.home_playLive)
    GridView homePlayLive;
    @BindView(R.id.home_splendid)
    TextView homeSplendid;
    @BindView(R.id.home_splendid_gridView)
    GridView homeSplendidGridView;
    @BindView(R.id.home_GGvedio)
    TextView homeGGvedio;
    @BindView(R.id.home_ggVedio_listView)
    ListView homeGgVedioListView;
    @BindView(R.id.home_)
    TextView home;
    @BindView(R.id.home_liveChina_gridView)
    GridView homeLiveChinaGridView;

    private HomeViewPagerAdapter adapter;
    @Override
    protected int getViweId() {
        return R.layout.home_fragment;
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
    public void setPresenter(HomeContract.Presenter presenter) {


    }

    @Override
    public void setReferesh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void setResult(HomeBean homeBean) {

        List<HomeBean.DataBean.BigImgBean> bigImgBeanList = homeBean.getData().getBigImg();
    }
    public void showPandaObserever(){

    }

    @Override
    public void showErrorMassage(String errorMessage) {

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
