package com.example.administrator.pandatv.module.chinaLive.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveBDLBean;

import java.util.List;

/**
 * Created by lizhuofang on 2017/7/13.
 */
public class BDLAdapter extends BaseAdapter<LiveBDLBean.LiveBean> {
    private CheckBox checkBox;
    private TextView textView;

    public BDLAdapter(Context context, List<LiveBDLBean.LiveBean> datas) {
        super(context, R.layout.livechina_adapter, datas);
    }

    @Override
    public void convert(final ViewHolder holder, LiveBDLBean.LiveBean liveBean) {
        textView = (TextView) holder.itemView.findViewById(R.id.livechina_adapter_jianjie);
        textView.setText(liveBean.getBrief());
        holder.setText(R.id.livechina_adapter_title,liveBean.getTitle());
        ImageView imageview = (ImageView) holder.itemView.findViewById(R.id.livechina_adapter_imge);
        Glide.with(context).load(liveBean.getImage()).into(imageview);
        checkBox= (CheckBox) holder.itemView.findViewById(R.id.livechina_adapter_conimag);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                       if(isChecked) {
                           textView.setVisibility(View.VISIBLE);
                       }else{
                           textView.setVisibility(View.GONE);
                       }
           }
       });
    }
}
