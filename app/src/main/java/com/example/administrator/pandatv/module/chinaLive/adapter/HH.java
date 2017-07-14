package com.example.administrator.pandatv.module.chinaLive.adapter;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ZoomButtonsController;

import com.example.administrator.pandatv.MainActivity;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.module.chinaLive.MyGridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class HH extends Activity {
    private LinearLayout my_grid_ll;
    private boolean mFlag = true;
    private MyGridLayout mGridLayout1;
    private MyGridLayout mGridLayout2;
    private List<String> mList1;
    private List<String> mList2;
    private RelativeLayout mGridTitle;
    private LinearLayout mLL_2222;
    private WebView mWebView;
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        my_grid_ll = (LinearLayout) findViewById(R.id.my_grid_ll);
        mGridTitle = (RelativeLayout) findViewById(R.id.myGrid_rl_title);
        initGridLayout();
        initWebView();
    }

    // 初始化WebView
    private void initWebView() {
        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings setting = mWebView.getSettings();
        setting.setDefaultFontSize(15);
        setting.setJavaScriptEnabled(true);
        setting.setSupportZoom(true);
        // 限制在一定的放大范围内
        setting.setBuiltInZoomControls(true);

        // 3.0以及以上的话
        if (Build.VERSION.SDK_INT >= 11) {
            // 禁用缩放控件
            setting.setDisplayZoomControls(false);
        } else {
            ZoomButtonsController zc = new ZoomButtonsController(mWebView);
            zc.getZoomControls().setVisibility(View.GONE);

        }

    }

    private void initGridLayout() {
        mGridLayout1 = (MyGridLayout) findViewById(R.id.dragable_myGridLayoutt);
        mGridLayout2 = (MyGridLayout) findViewById(R.id.dis_dragable_myGridLa);
        initData();
    }

    private void initData() {
        mGridLayout1.setDragAble(true);
        mList1 = new ArrayList<String>();
        mList1.add("热门资讯");
        mList1.add("开源资讯");
        mList1.add("推荐博客");
        mList1.add("技术问题");
        mList1.add("移动开发");
        mList1.add("云计算");
        mList1.add("软件工程");
        mList1.add("开源软件");
        mGridLayout1.setItems(mList1);

        mGridLayout2.setDragAble(false);
        mList2 = new ArrayList<String>();
        mList2.add("数据库");
        mList2.add("编程语言");
        mList2.add("游戏开发");
        mList2.add("职业生涯");
        mList2.add("最新博客");
        mList2.add("开源访谈");
        mList2.add("高手问答");
        mGridLayout2.setItems(mList2);

    }

    public void click_jahao(View view) {
        my_grid_ll.setVisibility(View.VISIBLE);
        if (mFlag) {// 打开
            Animation anim = AnimationUtils.loadAnimation(HH.this,
                    R.anim.shunshizhen_raotate);
            view.startAnimation(anim);

            // 打开主页面
            Animation mainOutAnim = AnimationUtils.loadAnimation(
                    HH.this, R.anim.top2bottom);
            my_grid_ll.startAnimation(mainOutAnim);

            // 标题由半透明变为完全不透明
            AlphaAnimation aa = new AlphaAnimation(0f, 1f);
            aa.setDuration(500);
            aa.setFillAfter(true);
            mGridTitle.startAnimation(aa);

            mFlag = false;
        } else {// 关闭
            Animation anim = AnimationUtils.loadAnimation(HH.this,
                    R.anim.nishizhen_rotate);
            view.startAnimation(anim);

            // 关闭主页面
            Animation mainOutAnim = AnimationUtils.loadAnimation(
                    HH.this, R.anim.bottom2top);
            my_grid_ll.startAnimation(mainOutAnim);

            AlphaAnimation aa = new AlphaAnimation(1.0f, 0f);
            aa.setDuration(500);
            aa.setFillAfter(true);
            mGridTitle.startAnimation(aa);

            mFlag = true;
        }

    }


    long firstTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Timer timer = null;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {// 如果没有退出
                isExit = true;
                Toast.makeText(HH.this, "再按一次退出程序", 0).show();
                timer = new Timer();
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }

//	       if(keyCode==KeyEvent.KEYCODE_BACK ){
//	            if (System.currentTimeMillis()-firstTime>2000){
//	                Toast.makeText(MainActivity.this,"再按一次退出程序--->onKeyDown",Toast.LENGTH_SHORT).show();
//	                firstTime=System.currentTimeMillis();
//	            }else{
//	                finish();
//	                System.exit(0);
//	            }
//	            return true;
//	        }

        return super.onKeyDown(keyCode, event);

    }

}
