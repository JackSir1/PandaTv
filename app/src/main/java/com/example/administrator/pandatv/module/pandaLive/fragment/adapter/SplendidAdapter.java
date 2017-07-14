package com.example.administrator.pandatv.module.pandaLive.fragment.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;

import java.util.List;


public class SplendidAdapter extends BaseAdapter<PandaLiveSplendidBean.ListBean> {

    public SplendidAdapter(Context context, List<PandaLiveSplendidBean.ListBean> datas) {
        super(context, R.layout.pandalive_top_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, PandaLiveSplendidBean.ListBean listBean) {
        holder.setText(R.id.tv_top_item, listBean.getTitle());
        holder.setText(R.id.time_top_item, listBean.getDaytime()+"2017-07-13 15:38");
        ImageView viewById = (ImageView) holder.itemView.findViewById(R.id.image_top_item);
        Glide.with(context).load(listBean.getImage()).into(viewById);
    }


}
