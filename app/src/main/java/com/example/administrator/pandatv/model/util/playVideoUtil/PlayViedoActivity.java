package com.example.administrator.pandatv.model.util.playVideoUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.PlayVideoBean;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCUserActionStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class PlayViedoActivity extends AppCompatActivity implements IPlayVideoContract.View{

    private PlayVideoPresenter presenter;
    private JCVideoPlayerStandard player;
    private JCVideoPlayerStandard play;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_viedo);
        initView();


    }


    private void initView() {
        play = (JCVideoPlayerStandard) findViewById(R.id.play);
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        title = intent.getStringExtra("title");
        new PlayVideoPresenter(this);
        setVideoPid(pid);



    }
    public void setVideoPid(String pid){
        presenter.setVedioPid(pid);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            PlayViedoActivity.this.finish();


        }

        return false;
    }
    public void setVideoPlayer(String videoUrl, String title) {
        play.setUp(videoUrl
                ,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
            Log.e("TAG",videoUrl);
        JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
        play.setMonitor(new JCVideoPlayerStandard.imgClickon() {
            @Override
            public void Share(View view) {
                 share();
                Toast.makeText(PlayViedoActivity.this, "分享", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CollectionMonitor(CompoundButton compoundButton, boolean b) {

            }

            @Override
            public void WatchthelistMonitor(View view) {

            }

            @Override
            public void setgq() {

            }

            @Override
            public void setbq() {

            }
        });
        //JCVideoPlayerStandard.setMonitor
    }

    @Override
    public void setPresenter(IPlayVideoContract.Presenter presenter) {
        this.presenter= (PlayVideoPresenter) presenter;
    }

    @Override
    public void getVedioUrl(PlayVideoBean playVideoBean) {
        String hls_url = playVideoBean.getHls_url();
        setVideoPlayer(hls_url,this.title);
    }
       private void share() {

        UMImage image = new UMImage(PlayViedoActivity.this,R.drawable.ic_launcher);

        new ShareAction(PlayViedoActivity.this).withText(""+"!"+ "" )
                .withMedia(image)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.e("TAG", "onStart");
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                        Log.e("TAG", "onResult");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                        Log.e("TAG", "onError");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                        Log.e("TAG", "onCancel");
                    }
                }).open();
    }

  
}

class MyUserActionStandard implements JCUserActionStandard {

    @Override
    public void onEvent(int type, String url, int screen, Object... objects) {
        switch (type) {
            case JCUserAction.ON_CLICK_START_ICON:
                Log.i("USER_EVENT", "ON_CLICK_START_ICON" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_CLICK_START_ERROR:
                Log.i("USER_EVENT", "ON_CLICK_START_ERROR" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_CLICK_START_AUTO_COMPLETE:
                Log.i("USER_EVENT", "ON_CLICK_START_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_CLICK_PAUSE:
                Log.i("USER_EVENT", "ON_CLICK_PAUSE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_CLICK_RESUME:
                Log.i("USER_EVENT", "ON_CLICK_RESUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_SEEK_POSITION:
                Log.i("USER_EVENT", "ON_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_AUTO_COMPLETE:
                Log.i("USER_EVENT", "ON_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_ENTER_FULLSCREEN:
                Log.i("USER_EVENT", "ON_ENTER_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_QUIT_FULLSCREEN:
                Log.i("USER_EVENT", "ON_QUIT_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_ENTER_TINYSCREEN:
                Log.i("USER_EVENT", "ON_ENTER_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_QUIT_TINYSCREEN:
                Log.i("USER_EVENT", "ON_QUIT_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_TOUCH_SCREEN_SEEK_VOLUME:
                Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_VOLUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserAction.ON_TOUCH_SCREEN_SEEK_POSITION:
                Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;

            case JCUserActionStandard.ON_CLICK_START_THUMB:
                Log.i("USER_EVENT", "ON_CLICK_START_THUMB" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            case JCUserActionStandard.ON_CLICK_BLANK:
                Log.i("USER_EVENT", "ON_CLICK_BLANK" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                break;
            default:
                Log.i("USER_EVENT", "unknow");
                break;
        }
    }


}

