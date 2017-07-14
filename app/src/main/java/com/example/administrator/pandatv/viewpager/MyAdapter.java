package com.example.administrator.pandatv.viewpager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
   //TAbLayout 适配器

public class MyAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    String[] str=null;
    public MyAdapter(FragmentManager fm, ArrayList<Fragment> list,String[] str) {
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
       /* Drawable drawable = context.getResources().getDrawable(imag[position]);
         drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
        //return sb;

        return str[position];
    }

}
