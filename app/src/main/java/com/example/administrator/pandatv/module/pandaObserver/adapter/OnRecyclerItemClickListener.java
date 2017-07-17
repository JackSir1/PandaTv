package com.example.administrator.pandatv.module.pandaObserver.adapter;

import android.view.View;

import com.example.administrator.pandatv.model.entity.PandaObserverBean;

/**
 * Created by Administrator on 2017/7/17.
 */

public interface OnRecyclerItemClickListener {
    void getViewContent(View view, PandaObserverBean.ListBean listBean);
}
