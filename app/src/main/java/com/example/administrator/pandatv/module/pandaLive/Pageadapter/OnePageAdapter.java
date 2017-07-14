package com.example.administrator.pandatv.module.pandaLive.Pageadapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
//首页viewpager adapter

public class OnePageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    String[] str=new String[]{" 直播 ","精彩一刻","当熊不让","超萌滚滚秀","熊猫档案","熊猫TOP榜","熊猫那些事儿","特别节目","原创新闻"};
    public OnePageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;


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
