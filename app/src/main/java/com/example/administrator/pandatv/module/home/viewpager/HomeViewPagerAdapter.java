package com.example.administrator.pandatv.module.home.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.adapter.BaseAdapter;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.module.home.HomeFragment;

import java.util.List;


/**
 * Created by Administrator on 2017/7/13.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;

    public HomeViewPagerAdapter(FragmentManager fm,List<BaseFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position % fragments.size());
    }

    @Override
    public int getCount() {
        return fragments.size()<0?0:Integer.MAX_VALUE;
    }
}
