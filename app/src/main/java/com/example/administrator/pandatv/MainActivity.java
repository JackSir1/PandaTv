package com.example.administrator.pandatv;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.util.MainFragmentBuild;
import com.example.administrator.pandatv.module.chinaLive.ChinaLiveFragment;
import com.example.administrator.pandatv.module.chinaLive.ChinaLivePresenter;
import com.example.administrator.pandatv.module.ggVideo.GGVideoFragment;
import com.example.administrator.pandatv.module.ggVideo.GGVideoPresenter;
import com.example.administrator.pandatv.module.home.HomeFragment;
import com.example.administrator.pandatv.module.home.HomePresenter;
import com.example.administrator.pandatv.module.pandaLive.PandaLiveFragment;
import com.example.administrator.pandatv.module.pandaLive.PandaLivePresenter;
import com.example.administrator.pandatv.module.pandaObserver.PandaObserverFragment;
import com.example.administrator.pandatv.module.pandaObserver.PandaObserverPresenter;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup main_radiogroup;

    private TextView main_titlebar;
    private ImageView main_image;
    private ImageView main_imagehudong;
    private int viewID=R.id.main_fragment;
    @Override
    protected int getViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        main_radiogroup= (RadioGroup) findViewById(R.id.main_radiogroup);
        main_titlebar= (TextView) findViewById(R.id.main_titlebar);
        main_image= (ImageView) findViewById(R.id.main_image);
        main_imagehudong= (ImageView) findViewById(R.id.main_imagehudong);

    }

    @Override
    protected void setListener() {
        main_radiogroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void setIntent() {
        showHome();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_home:
                showHome();

                break;
            case R.id.main_pandaLive:
                showPandaLive();

                break;
            case R.id.main_ggVideo:
                showGGVideo();

                break;
            case R.id.main_pandaObserver:
                showPandaObserver();

                break;
            case R.id.mian_chinaLive:
                showChinaLive();

                break;
        }
    }
    public void showHome(){
        isShowTitle(true,"");
        HomeFragment homFragment = (HomeFragment) MainFragmentBuild.getInsenter().setFragmentView(viewID, HomeFragment.class).builder().getFragmentContext();
        new HomePresenter(homFragment);
    }
    public void showPandaLive(){
        isShowTitle(false,"熊猫直播");
        PandaLiveFragment pandaLiveFragment= (PandaLiveFragment) MainFragmentBuild.getInsenter().setFragmentView(viewID,PandaLiveFragment.class).builder().getFragmentContext();
        new PandaLivePresenter(pandaLiveFragment);
    }
    public void showGGVideo(){
        isShowTitle(false,"滚滚视频");
        GGVideoFragment ggVideoFragment= (GGVideoFragment) MainFragmentBuild.getInsenter().setFragmentView(viewID,GGVideoFragment.class).builder().getFragmentContext();
        new GGVideoPresenter(ggVideoFragment);
    }
    public void showPandaObserver(){
        isShowTitle(false,"熊猫播报");
        PandaObserverFragment pandaObserverFragment= (PandaObserverFragment) MainFragmentBuild.getInsenter().setFragmentView(viewID,PandaObserverFragment.class).builder().getFragmentContext();
        new PandaObserverPresenter(pandaObserverFragment);
    }
    public void showChinaLive(){
        isShowTitle(false,"直播中国");
        ChinaLiveFragment chinaLiveFragment= (ChinaLiveFragment) MainFragmentBuild.getInsenter().setFragmentView(viewID,ChinaLiveFragment.class).builder().getFragmentContext();
        new ChinaLivePresenter(chinaLiveFragment);
    }
    public void isShowTitle(Boolean isShow,String title){
        if (isShow){
            main_image.setVisibility(View.VISIBLE);
            main_titlebar.setVisibility(View.INVISIBLE);
            main_imagehudong.setVisibility(View.VISIBLE);
        }else {
            main_image.setVisibility(View.GONE);
            main_titlebar.setVisibility(View.VISIBLE);
            main_imagehudong.setVisibility(View.GONE);
            main_titlebar.setText(title);
        }
    }

}
