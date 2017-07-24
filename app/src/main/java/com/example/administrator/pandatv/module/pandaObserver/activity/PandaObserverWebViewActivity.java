package com.example.administrator.pandatv.module.pandaObserver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/17.
 */

public class PandaObserverWebViewActivity extends BaseActivity {
    @BindView(R.id.observer_webBack)
    ImageView observerWebBack;
    @BindView(R.id.pandaObserver_title)
    TextView pandaObserverTitle;
    @BindView(R.id.pandaObserver_webUrl)
    WebView pandaObserverWebUrl;
    @BindView(R.id.shoucang_btn)
    ImageView shoucangBtn;
    @BindView(R.id.fenxiang_btn)
    ImageView fenxiangBtn;

    private String url;
    @Override
    protected int getViewID() {
        return R.layout.webviewload_main;
    }

    @Override
    protected void initView() {

        Intent intent=getIntent();

        url = intent.getStringExtra("url");

        WebSettings webSettings = pandaObserverWebUrl.getSettings();
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
    }

    @Override
    protected void setListener() {

        observerWebBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void setIntent() {

        if (url!=null){
            pandaObserverWebUrl.loadUrl(url);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            PandaObserverWebViewActivity.this.finish();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
