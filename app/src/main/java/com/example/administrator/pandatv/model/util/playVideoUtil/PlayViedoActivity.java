package com.example.administrator.pandatv.model.util.playVideoUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.PlayVideoBean;

import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCUserActionStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class PlayViedoActivity extends AppCompatActivity implements IPlayVideoContract.View{

    private JCVideoPlayerStandard videoplayer;
    private PlayVideoPresenter presenter;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_viedo);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        title = intent.getStringExtra("title");
        new PlayVideoPresenter(this);
        setVideoPid(pid);


        //直接进入全屏
    }
    public void setVideoPid(String pid){
        presenter.setVedioPid(pid);
    }

    public void setVideoPlayer(String videoUrl, String title) {
        if (videoUrl.isEmpty())
            JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", "空标题");
        else
            JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, videoUrl, title);
    }




    @Override
    public void getVedioUrl(PlayVideoBean playVideoBean) {
        String hls_url = playVideoBean.getHls_url();
        setVideoPlayer(hls_url,this.title);
    }

    @Override
    public void setPresenter(IPlayVideoContract.Presenter presenter) {
        this.presenter= (PlayVideoPresenter) presenter;
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            PlayViedoActivity.this.finish();
        }

        return false;
    }
}
