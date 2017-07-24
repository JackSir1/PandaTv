package com.example.administrator.pandatv.module.pandaObserver.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;
import com.example.administrator.pandatv.model.entity.PandaObserverFirstItemBean;
import com.example.administrator.pandatv.module.pandaObserver.adapter.PandaObserverFirstItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/17.
 */

public class PandaObserverContentActivity extends BaseActivity implements PandaObserverContentContract.View {
    @BindView(R.id.ggvideo_backBtn)
    ImageView ggvideoBackBtn;
    @BindView(R.id.ggvideoitem_title)
    TextView ggvideoitemTitle;
    @BindView(R.id.ggvideo_video)
    ImageView ggvideoVideo;
    @BindView(R.id.show_ggVideoContent)
    CheckBox showGgVideoContent;
    @BindView(R.id.ggVideoContent_text)
    TextView ggVideoContentText;
    @BindView(R.id.ggvideo_list)
    PullToRefreshRecyclerView ggvideoList;

    private PandaObserverContentPresenter presenter;
    private List<PandaObserverFirstItemBean.VideoBean> firstItemBeanList;
    private PandaObserverFirstItemAdapter pandaObserverFirstItemAdapter;

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

        firstItemBeanList=new ArrayList<>();
        pandaObserverFirstItemAdapter=new PandaObserverFirstItemAdapter(this,firstItemBeanList);
        ggvideoList.setAdapter(pandaObserverFirstItemAdapter);
    }

    @Override
    protected void setListener() {

        showGgVideoContent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ggVideoContentText.setVisibility(View.VISIBLE);
                }else {
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

    public void setObserverItemList(List<PandaObserverFirstItemBean.VideoBean> videoBeans){
        firstItemBeanList.clear();
        firstItemBeanList.addAll(videoBeans);
        pandaObserverFirstItemAdapter.notifyDataSetChanged();
    }
    public void setObserverItemContent(PandaObserverFirstItemBean.VideosetBean videosetBean){
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
