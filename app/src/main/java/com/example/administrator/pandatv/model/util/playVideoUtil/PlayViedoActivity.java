package com.example.administrator.pandatv.model.util.playVideoUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.PlayVideoBean;

import fm.jiecao.jcvideoplayer_lib.JCFullScreenActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import fm.jiecao.jcvideoplayer_lib.PandaVedioPlayer;

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
       // String s = "http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/07/14/c39ef06c39314cb6a9d6c25f6527c095_h264418000nero_aac32.mp4";

        JCFullScreenActivity.toActivity(this,
                videoUrl, null,
                PandaVedioPlayer.class, title);
               PlayViedoActivity.this.finish();
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
