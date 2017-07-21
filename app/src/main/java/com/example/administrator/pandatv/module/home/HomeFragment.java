package com.example.administrator.pandatv.module.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.HomeBean;
import com.example.administrator.pandatv.model.util.ShowPopuUtils;
import com.example.administrator.pandatv.module.home.viewpager.HomeAdapter;
import com.example.administrator.pandatv.module.home.viewpager.HomeViewPagerAdapter;
import com.example.administrator.pandatv.module.pandaObserver.OnViewPagerItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/12.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.home_recyclerView)
    PullToRefreshRecyclerView homeRecyclerView;
    private View inflater;
    private LinearLayout homeViewpagerLinearLayout;
    private ViewPager homeViewpager;
    private int currmentNum = 100000;
    private List<View> viewPagerFragments = new ArrayList<>();
    private HomeContract.Presenter presenter;
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private ShowPopuUtils showPopuUtils;

    @Override
    protected int getViweId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView(View view) {

        showPopuUtils = ShowPopuUtils.getInsent().create(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        homeRecyclerView.setLayoutManager(manager);
        inflater = LayoutInflater.from(getContext()).inflate(R.layout.home_viewpager_main, null);
        homeViewpagerLinearLayout = (LinearLayout) inflater.findViewById(R.id.home_viewpager_linearLayout);
        homeViewpager = (ViewPager) inflater.findViewById(R.id.home_viewpager);
        homeRecyclerView.setPullRefreshEnabled(true);
        homeRecyclerView.setLoadingMoreEnabled(true);
        homeRecyclerView.displayLastRefreshTime(true);
        homeRecyclerView.addHeaderView(inflater);
    }

    @Override
    protected void loadDate() {
        presenter.start();
    }

    @Override
    protected void setListener() {
        homeViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currmentNum = position;
                for (int i = 0; i < checkBoxes.size(); i++) {
                    if (i == currmentNum % checkBoxes.size()) {
                        checkBoxes.get(i).setChecked(true);
                    } else {
                        checkBoxes.get(i).setChecked(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        homeRecyclerView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                homeRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        homeRecyclerView.setRefreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                homeRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        homeRecyclerView.setLoadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public void setReferesh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void setResult(HomeBean homeBean) {

        HomeBean.DataBean data = homeBean.getData();
        List<Object> list = new ArrayList<>();
        list.add(data.getPandaeye());
        list.add(data.getPandalive());
        list.add(data.getArea());
        list.add(data.getWalllive());
        list.add(data.getChinalive());
        HomeAdapter adapter = new HomeAdapter(getContext(), list);
        homeRecyclerView.setAdapter(adapter);
        List<HomeBean.DataBean.BigImgBean> bigImgBeanList = homeBean.getData().getBigImg();
        showViewPager(bigImgBeanList);
        showPopuUtils.popuUtilsDismiss();
    }

    //輪播圖
    public void showViewPager(final List<HomeBean.DataBean.BigImgBean> bigImgBeanList) {
        View view = null;
        CheckBox checkBox;
        View view1 = null;
        for (HomeBean.DataBean.BigImgBean bigImgBean : bigImgBeanList) {
            view1 = LayoutInflater.from(getContext()).inflate(R.layout.checkbox_item, null);
            checkBox = (CheckBox) view1.findViewById(R.id.viewpager_checkbox_btn);
            homeViewpagerLinearLayout.addView(view1);
            checkBoxes.add(checkBox);
            view = LayoutInflater.from(getContext()).inflate(R.layout.home_viewpager_fragment, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.home_viewpager_image);
            TextView title = (TextView) view.findViewById(R.id.home_viewpager_title);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String image = bigImgBean.getImage();
            String titlestr = bigImgBean.getTitle();
            Glide.with(getContext()).load(image).into(imageView);
            title.setText(titlestr);
            viewPagerFragments.add(view);
        }
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(viewPagerFragments);
        homeViewpager.setAdapter(adapter);
        checkBoxes.get(currmentNum % checkBoxes.size()).setChecked(true);
        homeViewpager.setCurrentItem(currmentNum);
        handler.sendEmptyMessageDelayed(222, 2000);
        adapter.setOnViewPagerItemListener(new OnViewPagerItemListener() {
            @Override
            public void onItemListener(View view, int posetion) {
                String url = bigImgBeanList.get(posetion).getUrl();
                String pid = bigImgBeanList.get(posetion).getPid();
                String stype = bigImgBeanList.get(posetion).getStype();
                String type = bigImgBeanList.get(posetion).getType();
                String title = bigImgBeanList.get(posetion).getTitle();
                if ("2".endsWith(type)) {
                }
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 222:
                    currmentNum++;
                    homeViewpager.setCurrentItem(currmentNum);
                    for (int i = 0; i < checkBoxes.size(); i++) {
                        if (i == currmentNum % checkBoxes.size()) {
                            checkBoxes.get(i).setChecked(true);
                        } else {
                            checkBoxes.get(i).setChecked(false);
                        }
                    }
                    handler.sendEmptyMessageDelayed(222, 2000);
                    break;
            }
        }
    };

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
}
