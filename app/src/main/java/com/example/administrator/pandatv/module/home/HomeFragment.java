package com.example.administrator.pandatv.module.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.HomeBean;
import com.example.administrator.pandatv.module.home.viewpager.HomeChinaLiveAdapter;
import com.example.administrator.pandatv.module.home.viewpager.HomePandaLiveAdapter;
import com.example.administrator.pandatv.module.home.viewpager.HomeSplendidAdapter;
import com.example.administrator.pandatv.module.home.viewpager.HomeViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.home_viewpager)
    ViewPager homeViewpager;
    @BindView(R.id.home_viewpager_linearLayout)
    LinearLayout homeViewpagerLinearLayout;
    @BindView(R.id.home_pandaObserver_image)
    ImageView homePandaObserverImage;
    private int currmentNum = 100000;
    private List<View> viewPagerFragments = new ArrayList<>();
    private HomeContract.Presenter presenter;
    private List<CheckBox> checkBoxes = new ArrayList<>();

    private List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeans=new ArrayList<>();
    private HomePandaLiveAdapter pandaLiveAdapter;

    private List<HomeBean.DataBean.ChinaliveBean.ListBeanXX> chinaLiveBeans=new ArrayList<>();
    private HomeChinaLiveAdapter chinaLiveAdapter;

    private List<HomeBean.DataBean.AreaBean.ListscrollBean> listscrollBeens=new ArrayList<>();
    private HomeSplendidAdapter splendidAdapter;

    @Override
    protected int getViweId() {
        return R.layout.home_fragment;
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

        List<HomeBean.DataBean.BigImgBean> bigImgBeanList = homeBean.getData().getBigImg();
        HomeBean.DataBean.ChinaliveBean chinalive = homeBean.getData().getChinalive();
        HomeBean.DataBean.PandaeyeBean pandaeye = homeBean.getData().getPandaeye();
        HomeBean.DataBean.PandaliveBean pandalive = homeBean.getData().getPandalive();
        HomeBean.DataBean.InteractiveBean interactive = homeBean.getData().getInteractive();
        HomeBean.DataBean.AreaBean area = homeBean.getData().getArea();
        showGG(area);
        showLiveChina(chinalive);
        showLivePanda(pandalive);
        showPandaObserever(pandaeye);
        showSplendid(area);
        showViewPager(bigImgBeanList);
    }

    //輪播圖
    public void showViewPager(List<HomeBean.DataBean.BigImgBean> bigImgBeanList) {
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

    //熊貓播報
    public void showPandaBoBao(){

    }

    //熊貓觀察
    public void showPandaObserever(HomeBean.DataBean.PandaeyeBean pandaeye) {
        String pandaeyelogo = pandaeye.getPandaeyelogo();
        homePandaObserverImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(getContext()).load(pandaeyelogo).into(homePandaObserverImage);
        homeObserverBtn1.setText(pandaeye.getItems().get(0).getBrief());
        homeObserverBtn2.setText(pandaeye.getItems().get(1).getBrief());
        homeObserverTitle1.setText(pandaeye.getItems().get(0).getTitle());
        homeObserverTitle2.setText(pandaeye.getItems().get(0).getTitle());
    }

    //直播秀場
    public void showLivePanda(HomeBean.DataBean.PandaliveBean pandalive) {
        if (pandaliveBeans!=null)
            pandaliveBeans.clear();
        pandaliveBeans.addAll(pandalive.getList());
        pandaLiveAdapter=new HomePandaLiveAdapter(getContext(),pandaliveBeans);
        homePlayLive.setAdapter(pandaLiveAdapter);
    }

    //直播中國
    public void showLiveChina(HomeBean.DataBean.ChinaliveBean chinalive) {
        chinaLiveBeans.addAll(chinalive.getList());
        chinaLiveAdapter=new HomeChinaLiveAdapter(getContext(),chinaLiveBeans);
        homeLiveChinaGridView.setAdapter(chinaLiveAdapter);
    }

    //精彩一刻
    public void showSplendid(HomeBean.DataBean.AreaBean area) {
        if (listscrollBeens.size()>0){
            listscrollBeens.clear();
        }
        for (int i=0;i<4;i++){
            listscrollBeens.add(area.getListscroll().get(i));
        }
        splendidAdapter=new HomeSplendidAdapter(getContext(),listscrollBeens);
        homeSplendidGridView.setAdapter(splendidAdapter);

    }

    //滾滾視頻
    public void showGG(HomeBean.DataBean.AreaBean area) {

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
}
