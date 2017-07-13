package com.example.administrator.pandatv;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.util.MainFragmentBuild;
import com.example.administrator.pandatv.module.chinaLive.ChinaLiveFragment;
import com.example.administrator.pandatv.module.ggVideo.GGVideoFragment;
import com.example.administrator.pandatv.module.ggVideo.GGVideoPresenter;
import com.example.administrator.pandatv.module.home.HomeFragment;
import com.example.administrator.pandatv.module.home.HomePresenter;
import com.example.administrator.pandatv.module.pandaLive.PandaLiveFragment;
import com.example.administrator.pandatv.module.pandaLive.PandaLivePresenter;
import com.example.administrator.pandatv.module.pandaObserver.PandaObserverFragment;
import com.example.administrator.pandatv.module.pandaObserver.PandaObserverPresenter;

import static com.example.administrator.pandatv.R.id.main_imagepersonal;

public class MainActivity extends BaseActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    private HomeFragment homeFragment;
    private RadioGroup main_radiogroup;
    private ImageView imageView;
    private int viewID=R.id.main_fragment;
    @Override
    protected int getViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        main_radiogroup= (RadioGroup) findViewById(R.id.main_radiogroup);
        imageView= (ImageView) findViewById(main_imagepersonal);
    }

    @Override
    protected void setListener() {
        main_radiogroup.setOnCheckedChangeListener(this);
        imageView.setOnClickListener(this);
    }

    @Override
    protected void setIntent() {

    }
    public void showHome(){
        HomeFragment homFragment = (HomeFragment) MainFragmentBuild.getInsenter().setFragmentView(viewID, HomeFragment.class).builder().getFragmentContext();
        new HomePresenter(homFragment);
    }
    public void showPandaLive(){
        PandaLiveFragment pandaLiveFragment= (PandaLiveFragment) MainFragmentBuild.getInsenter().setFragmentView(viewID,PandaLiveFragment.class).builder().getFragmentContext();
        new PandaLivePresenter(pandaLiveFragment);
    }
    public void showGGVideo(){
        GGVideoFragment ggVideoFragment= (GGVideoFragment) MainFragmentBuild.getInsenter().setFragmentView(viewID,GGVideoFragment.class).builder().getFragmentContext();
        new GGVideoPresenter(ggVideoFragment);
    }
    public void showPandaObserver(){
        PandaObserverFragment pandaObserverFragment = (PandaObserverFragment) MainFragmentBuild.getInsenter().setFragmentView(viewID, PandaObserverFragment.class).builder().getFragmentContext();
        new PandaObserverPresenter(pandaObserverFragment);

    }
    public void showChinaLive(){
//        ChinaLiveFragment chinaLiveFragment= (ChinaLiveFragment) MainFragmentBuild.getInsenter().setFragmentView(viewID,ChinaLiveFragment.class).builder().getFragmentContext();
//        new ChinaLivePresenter(chinaLiveFragment);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment,new ChinaLiveFragment());
        transaction.commit();
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

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,PersonalActivity.class);
        startActivity(intent);
    }
}
