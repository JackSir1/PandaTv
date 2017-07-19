package com.example.administrator.pandatv.module.pandaLive.fragment.adapter;


import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.livechinaEntity.Watch;

import java.util.List;


public class ChatAdapter extends BaseAdapter<Watch.Bean> {

    public ChatAdapter(Context context, List<Watch.Bean> datas) {
        super(context, R.layout.pandalive_live_chat_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, Watch.Bean listBean) {
        holder.setText(R.id.watch_item_author,listBean.getAuthor());
        holder.setText(R.id.watch_item_message,listBean.getMessage());
       // ImageView viewById = (ImageView) holder.itemView.findViewById(R.id.image_pandalive_more);
       // Glide.with(context).load(listBean.getImage()).into(viewById);


    }
}
