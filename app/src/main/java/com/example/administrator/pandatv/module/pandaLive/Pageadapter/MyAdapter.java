package com.example.administrator.pandatv.module.pandaLive.Pageadapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
//首页viewpager adapter

public class MyAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    String[] str=null;
    //new String[]{"精选","资讯","选电影","预告片","影评"};
    public MyAdapter(FragmentManager fm, List<Fragment> list, String[] str) {
        super(fm);
        this.list = list;
        this.str=str;

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }

}
