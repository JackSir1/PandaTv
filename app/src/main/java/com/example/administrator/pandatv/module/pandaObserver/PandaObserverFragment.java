package com.example.administrator.pandatv.module.pandaObserver;

import android.content.Intent;
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
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.PandaObserverBean;
import com.example.administrator.pandatv.model.util.ACache;
import com.example.administrator.pandatv.model.util.ShowPopuUtils;
import com.example.administrator.pandatv.module.home.viewpager.HomeViewPagerAdapter;
import com.example.administrator.pandatv.module.pandaObserver.activity.PandaObserverContentActivity;
import com.example.administrator.pandatv.module.pandaObserver.activity.PandaObserverWebViewActivity;
import com.example.administrator.pandatv.module.pandaObserver.adapter.OnRecyclerItemClickListener;
import com.example.administrator.pandatv.module.pandaObserver.adapter.PandaObserverAdapter;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/12.
 */

public class PandaObserverFragment extends BaseFragment implements PandaObserverContract.View {
    @BindView(R.id.observer_recyclerView)
    PullToRefreshRecyclerView observerRecyclerView;
    private View inflater;
    private LinearLayout viewpagerLinearLayout;
    private ViewPager viewpager;
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private List<View> viewPagerFragments = new ArrayList<>();
    private int currmentNum = 100000;

    private PandaObserverAdapter pandaObserverAdapter;
    private List<PandaObserverBean.ListBean> beanList = new ArrayList<>();
    private PandaObserverContract.Presenter presenter;
    private ShowPopuUtils showPopuUtils;

    @Override
    protected int getViweId() {
        return R.layout.observer_fragment;
    }

    @Override
    protected void initView(View view) {
        showPopuUtils = ShowPopuUtils.getInsent().create(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        observerRecyclerView.setLayoutManager(manager);

        inflater = LayoutInflater.from(getContext()).inflate(R.layout.home_viewpager_main, null);
        viewpagerLinearLayout = (LinearLayout) inflater.findViewById(R.id.home_viewpager_linearLayout);
        viewpager = (ViewPager) inflater.findViewById(R.id.home_viewpager);
        observerRecyclerView.addHeaderView(inflater);
        observerRecyclerView.displayLastRefreshTime(true);
        pandaObserverAdapter = new PandaObserverAdapter(getContext(), beanList);
        observerRecyclerView.setAdapter(pandaObserverAdapter);
        presenter.start();
    }

    @Override
    protected void loadDate() {
    }

    @Override
    protected void setListener() {
        pandaObserverAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void getViewContent(View view, PandaObserverBean.ListBean listBean) {
                String type = listBean.getType();
                Intent intent;
                if (type.endsWith("1")) {

                } else if (type.endsWith("2")) {
                    intent = new Intent(getContext(), PandaObserverContentActivity.class);
                    getContext().startActivity(intent);
                }

//                getContext().startActivity(intent);
            }
        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    }

    //輪播圖
    public void showViewPager(final List<PandaObserverBean.BigImgBean> bigImgBeanList) {

        View view = null;
        CheckBox checkBox;
        View view1 = null;
        for (PandaObserverBean.BigImgBean t : bigImgBeanList) {
            view1 = LayoutInflater.from(getContext()).inflate(R.layout.checkbox_item, null);
            checkBox = (CheckBox) view1.findViewById(R.id.viewpager_checkbox_btn);
            viewpagerLinearLayout.addView(view1);
            checkBoxes.add(checkBox);
            view = LayoutInflater.from(getContext()).inflate(R.layout.home_viewpager_fragment, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.home_viewpager_image);
            TextView title = (TextView) view.findViewById(R.id.home_viewpager_title);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String image = t.getImage();
            String titlestr = t.getTitle();
            Glide.with(getContext()).load(image).into(imageView);
            title.setText(titlestr);
            viewPagerFragments.add(view);
        }
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(viewPagerFragments);
        viewpager.setAdapter(adapter);
        checkBoxes.get(currmentNum % checkBoxes.size()).setChecked(true);
        viewpager.setCurrentItem(currmentNum);
        handler.sendEmptyMessageDelayed(222, 2000);

        adapter.setOnViewPagerItemListener(new OnViewPagerItemListener() {
            @Override
            public void onItemListener(View view, int posetion) {
                String type = bigImgBeanList.get(posetion).getType();
                String pid = bigImgBeanList.get(posetion).getPid();
                String url = bigImgBeanList.get(posetion).getUrl();
                if (type.endsWith("5")) {
                    Intent intent = new Intent(getContext(), PandaObserverWebViewActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void setPresenter(PandaObserverContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void setListView(List<PandaObserverBean.ListBean> listBeanList) {
        beanList.addAll(listBeanList);
        pandaObserverAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void setResult(PandaObserverBean observerBean) {

        setListView(observerBean.getList());
        showViewPager(observerBean.getBigImg());
        showPopuUtils.popuUtilsDismiss();
    }

    @Override
    public void unNet() {
        ACache aCache = ACache.get(getContext(),"interfaceCache");
        PandaObserverBean homeBean1 = (PandaObserverBean) aCache.getAsObject("homeBean");
        if (homeBean1 != null) {
            setListView(homeBean1.getList());
            showViewPager(homeBean1.getBigImg());
            showPopuUtils.popuUtilsDismiss();
        }
    }

    @Override
    public void setError(String error) {
//        ACache aCache = ACache.get(getContext(),"interfaceCache");
//        PandaObserverBean homeBean1 = (PandaObserverBean) aCache.getAsObject("homeBean");
//        if (homeBean1 != null) {
//            setListView(homeBean1.getList());
//            showViewPager(homeBean1.getBigImg());
//            showPopuUtils.popuUtilsDismiss();
//        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 222:
                    currmentNum++;
                    viewpager.setCurrentItem(currmentNum);
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
}
