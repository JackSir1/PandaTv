package com.example.administrator.pandatv.module.pandaLive.fragment.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.PandaLiveSplendidBean;
import com.example.administrator.pandatv.model.util.playVideoUtil.PlayViedoActivity;

import java.util.List;


public class SplendidAdapter extends BaseAdapter< PandaLiveSplendidBean.VideoBean> {

    public SplendidAdapter(Context context, List< PandaLiveSplendidBean.VideoBean> datas) {
        super(context, R.layout.pandalive_top_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final PandaLiveSplendidBean.VideoBean listBean) {
        holder.setText(R.id.tv_top_item, listBean.getT());
        holder.setText(R.id.data_top, listBean.getLen());
        holder.setText(R.id.time_top_item, listBean.getPtime());
        ImageView viewById = (ImageView) holder.itemView.findViewById(R.id.image_top_item);
        Glide.with(context).load(listBean.getImg()).into(viewById);
        holder.setOnclickListener(R.id.lin_gg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(context, PlayViedoActivity.class);
                in.putExtra("pid",listBean.getVid());
                in.putExtra("title",listBean.getT());
                context.startActivity(in);
            }
        });
    }


}
