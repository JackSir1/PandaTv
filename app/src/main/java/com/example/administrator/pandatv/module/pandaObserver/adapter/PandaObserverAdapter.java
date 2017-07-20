package com.example.administrator.pandatv.module.pandaObserver.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.PandaObserverBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/15.
 */

public class PandaObserverAdapter extends BaseAdapter<PandaObserverBean.ListBean> {
    public PandaObserverAdapter(Context context, List<PandaObserverBean.ListBean> datas) {
        super(context, R.layout.observer_adapter_item, datas);
    }
    private OnRecyclerItemClickListener  onItemClickListener;
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener  onItemClickListener){
        this.onItemClickListener=onItemClickListener;

    }

    @Override
    public void convert(ViewHolder holder, final PandaObserverBean.ListBean listBean) {
        String image = listBean.getImage();
        String title = listBean.getTitle();
        String videoLength = listBean.getVideoLength();
        String brief = listBean.getBrief();
        holder.setText(R.id.observer_list_time,videoLength);
        holder.setText(R.id.observer_list_title,title);
        holder.setText(R.id.observer_list_content,brief);
        ImageView imageView=holder.getView(R.id.observer_list_image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(image).into(imageView);

        LinearLayout pandaObserverLinearLayout=holder.getView(R.id.pandaObserver_linearLayout);
        pandaObserverLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.getViewContent(v,listBean);
            }
        });
    }
}
