package com.example.administrator.pandatv.module.chinaLive.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LivechinaTSBean;

import java.util.List;

/**
 * Created by lizhuofang on 2017/7/13.
 */
public class BDLAdapter extends BaseAdapter<LivechinaTSBean.LiveBean> {

    public BDLAdapter(Context context, List<LivechinaTSBean.LiveBean> datas) {
        super(context, R.layout.livechina_adapter, datas);
    }

    @Override
    public void convert(final ViewHolder holder, LivechinaTSBean.LiveBean liveBean) {
        holder.setText(R.id.livechina_adapter_jianjie,liveBean.getBrief());
        holder.setText(R.id.livechina_adapter_title,liveBean.getTitle());
        ImageView imageview = (ImageView) holder.itemView.findViewById(R.id.livechina_adapter_imge);
        Glide.with(context).load(liveBean.getImage()).into(imageview);
        holder.setOnclickListener(R.id.livechina_ada_linear_jianjie, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
