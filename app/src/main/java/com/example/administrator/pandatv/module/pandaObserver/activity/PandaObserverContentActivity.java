package com.example.administrator.pandatv.module.pandaObserver.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.entity.PandaObserverFirstItemBean;
import com.example.administrator.pandatv.model.entity.PlayVideoBean;
import com.example.administrator.pandatv.model.util.saveData.PandaTvBean;
import com.example.administrator.pandatv.model.util.saveData.SaveDataToSD;
import com.example.administrator.pandatv.module.pandaObserver.adapter.IPandaObserverFirstItemListener;
import com.example.administrator.pandatv.module.pandaObserver.adapter.PandaObserverFirstItemAdapter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2017/7/17.
 */

public class PandaObserverContentActivity extends BaseActivity implements PandaObserverContentContract.View {


    @BindView(R.id.ggvideo_backBtn)
    ImageView ggvideoBackBtn;
    @BindView(R.id.ggvideoitem_title)
    TextView ggvideoitemTitle;
    @BindView(R.id.ggvideo_video)
    JCVideoPlayerStandard ggvideoVideo;
    @BindView(R.id.show_ggVideoContent)
    CheckBox showGgVideoContent;
    @BindView(R.id.ggVideoContent_text)
    TextView ggVideoContentText;
    @BindView(R.id.ggvideo_list)
    PullToRefreshRecyclerView ggvideoList;
    @BindView(R.id.web_shoucang_btn)
    CheckBox webShoucangBtn;
    @BindView(R.id.web_fenxiang_btn)
    CheckBox webFenxiangBtn;

    private PandaObserverContentPresenter presenter;
    private List<PandaObserverFirstItemBean.VideoBean> firstItemBeanList;
    private PandaObserverFirstItemAdapter pandaObserverFirstItemAdapter;
    private String url;
    private String vid_this;

    @Override
    protected int getViewID() {
        return R.layout.pandaobserver_firstmain;
    }

    @Override
    protected void initView() {
        new PandaObserverContentPresenter(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        ggvideoList.setLayoutManager(manager);
        firstItemBeanList = new ArrayList<>();
        presenter.start();

    }

    @Override
    protected void setListener() {

        showGgVideoContent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ggVideoContentText.setVisibility(View.VISIBLE);
                } else {
                    ggVideoContentText.setVisibility(View.GONE);
                }
            }
        });
        ggvideoBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webShoucangBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveDataToSD addcollect = SaveDataToSD.getInsent();
                PandaTvBean bean = addcollect.getBean(vid_this);
                if (bean!=null){
                    Boolean save = bean.getSave();
                    save=!save;
                    webShoucangBtn.setChecked(save);
                    if (save){
                        Toast.makeText(PandaObserverContentActivity.this,"已经收藏",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(PandaObserverContentActivity.this,"已经取消收藏",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        webFenxiangBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                share();
            }
        });
    }
    public void share(){
        UMImage image = new UMImage(PandaObserverContentActivity.this,R.drawable.ic_launcher);

        new ShareAction(PandaObserverContentActivity.this).withText(url)
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

    @Override
    protected void setIntent() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            PandaObserverContentActivity.this.finish();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getManager(PandaObserverFirstItemBean firstItemBean) {
        setObserverItemList(firstItemBean.getVideo());
        setObserverItemContent(firstItemBean.getVideoset());
    }

    @Override
    public void setPid(PlayVideoBean playVideoBean) {
        String url = playVideoBean.getVideo().getChapters2().get(0).getUrl();
        String title = playVideoBean.getTitle();
        this.url=url;
        ggvideoVideo.setUp(url, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
        ggvideoVideo.startVideo();
    }

    public void setObserverItemList(List<PandaObserverFirstItemBean.VideoBean> videoBeans) {
        initPlay(videoBeans.get(0));
        firstItemBeanList.clear();
        firstItemBeanList.addAll(videoBeans);
        pandaObserverFirstItemAdapter = new PandaObserverFirstItemAdapter(this, firstItemBeanList);
        ggvideoList.setAdapter(pandaObserverFirstItemAdapter);
        pandaObserverFirstItemAdapter.setObserverItemListener(new IPandaObserverFirstItemListener() {
            @Override
            public void setObserverVideoItemPid(PandaObserverFirstItemBean.VideoBean videoBean) {

                String url = videoBean.getUrl();
                String vsid = videoBean.getVsid();
                String t = videoBean.getT();
                String vid = videoBean.getVid();
                String ptime = videoBean.getPtime();
                String img = videoBean.getImg();
                String len = videoBean.getLen();

                SaveDataToSD addcollect = SaveDataToSD.getInsent();

                Boolean isSave=false;

                PandaTvBean pandaTvBean = addcollect.getBean(vid);
                if (pandaTvBean!=null){
                    Boolean save = pandaTvBean.getSave();
                    isSave=save;
                }else {
                    pandaTvBean=new PandaTvBean();
                }

                pandaTvBean.setImageView(img);
                pandaTvBean.setContent(t);
                pandaTvBean.setVideoTime(len);
                pandaTvBean.setPid(vsid);

                if (!vid.equals("")){
                    vid_this=vid;
                    pandaTvBean.setVid(vid);
                }else {
                    vid_this=vsid;
                    pandaTvBean.setVid(vsid);
                }
                pandaTvBean.setType("1");
                pandaTvBean.setUrl(url);
                addcollect.addcollect(pandaTvBean);

                presenter.getVideoUrl(pandaTvBean.getVid());
            }
        });
    }

    public void initPlay(PandaObserverFirstItemBean.VideoBean videoBean) {
        presenter.getVideoUrl(videoBean.getVid());
//        String vsid = videoBean.getVsid();
//        String vid = videoBean.getVid();
//        String url = videoBean.getUrl();
//        String t = videoBean.getT();
//        ggvideoVideo.setUp(url
//                ,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, t);
//        ggvideoVideo.startVideo();
    }

    public void setObserverItemContent(PandaObserverFirstItemBean.VideosetBean videosetBean) {
        String name = videosetBean.get_$0().getName();
        String desc = videosetBean.get_$0().getDesc();
        ggvideoitemTitle.setText(name);
        ggVideoContentText.setText(desc);
    }

    @Override
    public void setPresenter(PandaObserverContentContract.Presenter presenter) {
        this.presenter = (PandaObserverContentPresenter) presenter;
    }
}
