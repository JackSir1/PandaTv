package com.example.administrator.pandatv.module.chinaLive.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.livechinaEntity.CehuaBean;

import java.util.List;


/**
 * Created by lenovo on 2017/7/15.
 */

public class HDAdapter extends BaseAdapter<CehuaBean.InteractiveBean> {


    public HDAdapter(Context context, List datas) {
        super(context, R.layout.cehua_item, datas);
    }


    @Override
    public void convert(ViewHolder holder, final CehuaBean.InteractiveBean interactiveBean) {

        holder.setText(R.id.cehua_item_tv,interactiveBean.getTitle());
        ImageView img = (ImageView) holder.itemView.findViewById(R.id.cehua_item_img);
        Glide.with(context).load(interactiveBean.getImage()).into(img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,WebActivity.class);
                intent.putExtra("url",interactiveBean.getUrl());
                context.startActivity(intent);
            }
        });
    }
}
