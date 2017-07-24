package com.example.administrator.pandatv.module.pandaObserver.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.PandaObserverFirstItemBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class PandaObserverFirstItemAdapter extends BaseAdapter<PandaObserverFirstItemBean.VideoBean> {
    public PandaObserverFirstItemAdapter(Context context,List<PandaObserverFirstItemBean.VideoBean> datas) {
        super(context, R.layout.pandaobserver_firstitem, datas);
    }

    @Override
    public void convert(ViewHolder holder, PandaObserverFirstItemBean.VideoBean videoBean) {
        holder.setText(R.id.observerfirstitem_content,videoBean.getT());
        holder.setText(R.id.observerfirstItem_time,videoBean.getLen());
        ImageView imageView=holder.getView(R.id.pandaObserverFirstItem_image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(videoBean.getImg()).asBitmap().into(imageView);

    }
}
