package com.example.administrator.pandatv.module.pandaLive.fragment.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.PandaLiveMoreBean;

import java.util.List;


public class MoreLiveAdapter extends BaseAdapter<PandaLiveMoreBean.ListBean> {

    public MoreLiveAdapter(Context context, List<PandaLiveMoreBean.ListBean> datas) {
        super(context, R.layout.pandalive_more_item1, datas);
    }

    @Override
    public void convert(ViewHolder holder, PandaLiveMoreBean.ListBean listBean) {
        holder.setText(R.id.tv_pandalive_more,listBean.getTitle());
        ImageView viewById = (ImageView) holder.itemView.findViewById(R.id.image_pandalive_more);
        Glide.with(context).load(listBean.getImage()).into(viewById);


    }
}
