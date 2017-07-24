package com.example.administrator.pandatv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.pandatv.config.adapter.StartViewPagerAdapter;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class StartActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<ImageView> imageViewList=new ArrayList<>();
    private ImageView imageView;
    private StartViewPagerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);
        init();
        setListener();
    }
    public void init(){
        viewPager= (ViewPager) findViewById(R.id.start_viewpager);
        add_image();
        adapter=new StartViewPagerAdapter(imageViewList);
        viewPager.setAdapter(adapter);
    }
    public void add_image(){
        imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.guide_one);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageViewList.add(imageView);

        imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.guide_two);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageViewList.add(imageView);

        imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.guide_three);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageViewList.add(imageView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    public void setListener(){
        imageViewList.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
