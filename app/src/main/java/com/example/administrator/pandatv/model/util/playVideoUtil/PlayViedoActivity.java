package com.example.administrator.pandatv.model.util.playVideoUtil;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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
import com.umeng.socialize.media.UMVideo;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCUserActionStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class PlayViedoActivity extends AppCompatActivity implements IPlayVideoContract.View{

    private PlayVideoPresenter presenter;
    private JCVideoPlayerStandard player;
    private JCVideoPlayerStandard play;
    private String title;
    private String hls_url;
    private String image;
    private String url;
    private String viedourl;

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


    public void setVideoPlayer(String image,final String videoUrl, final String title) {
        url = videoUrl;

        play.setUp(videoUrl
                ,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
        play.startVideo();


            Log.e("TAG",videoUrl);
        JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
        //JCVideoPlayerStandard.startFullscreen(this,JCUserActionStandard.class,videoUrl,title);

        play.setMonitor(new JCVideoPlayerStandard.imgClickon() {
            @Override
            //分享
            public void Share(View view) {
                play.onStatePlaying();
                 share1();

                Toast.makeText(PlayViedoActivity.this, "分享", Toast.LENGTH_SHORT).show();
            }

            @Override
            //收藏
            public void CollectionMonitor(CompoundButton compoundButton, boolean b) {
                if(b==true) {
                     Toast.makeText(PlayViedoActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PlayViedoActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void WatchthelistMonitor(View view) {

            }

            @Override
            public void setgq() {
          Toast.makeText(PlayViedoActivity.this, "已切换至高清", Toast.LENGTH_SHORT).show();
                play.setUp(viedourl
                        ,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
                play.startVideo();

            }

            @Override
            public void setbq() {

                play.setUp(videoUrl
                        ,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
                play.startVideo();
                Toast.makeText(PlayViedoActivity.this, "已切换至标清", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void setPresenter(IPlayVideoContract.Presenter presenter) {
        this.presenter= (PlayVideoPresenter) presenter;
    }

    @Override
    public void getVedioUrl( final PlayVideoBean playVideoBean) {
        hls_url = playVideoBean.getHls_url();
        Log.d("PlayViedoActivity", hls_url);
        viedourl = playVideoBean.getVideo().getChapters4().get(0).getUrl();
        image = playVideoBean.getVideo().getChapters().get(0).getImage();
        setVideoPlayer(image, hls_url,this.title);
    }
    //分享

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            JCMediaManager.instance().mediaPlayer.stop();
            //PlayViedoActivity.this.finish();
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayViedoActivity.this.finish();
        JCMediaManager.instance().mediaPlayer.stop();
    }
    public void share1(){
        UMVideo video = new UMVideo(url);
        video.setTitle(title);//视频的标题

        video.setDescription(title);//视频的描述
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(PlayViedoActivity.this, mPermissionList, 123);
        }

        //video.setThumb(new UMImage(PlayViedoActivity.this, image));
        new ShareAction(PlayViedoActivity.this).withText("").setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN).setCallback(new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                //JCMediaManager.instance().mediaPlayer.pause();
                Log.e("share", "onStart");
            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {
                Log.e("share", "onResult");
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                Log.e("share","error"+ throwable.toString());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                Log.e("share", "onCancel");
                Toast.makeText(PlayViedoActivity.this, "11", Toast.LENGTH_SHORT).show();

            }
        }).withMedia(video).open();
        play.setUp(viedourl
                ,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
        play.startVideo();
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

