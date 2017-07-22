package com.example.administrator.pandatv.module.chinaLive.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.pandatv.base.BaseFragment;

import java.util.List;

/**
 * Created by lizhuofang on 2017/7/13.
 */

public class MyLivechinaAdapter extends FragmentStatePagerAdapter {
    private List<String> mListName;
    private List<BaseFragment> fragments;

    public MyLivechinaAdapter(FragmentManager fm, List<String> mListName, List<BaseFragment> fragments) {
        super(fm);
        this.mListName = mListName;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListName.get(position);
    }
}
