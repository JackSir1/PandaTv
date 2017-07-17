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
import com.example.administrator.pandatv.model.entity.livechinaEntity.HuangShanBean;

import java.util.List;

/**
 * Created by lizhuofang on 2017/7/14.
 */

public class HuangshanAdapter extends BaseAdapter<HuangShanBean.LiveBean> {


    public HuangshanAdapter(Context context, List<HuangShanBean.LiveBean> datas) {
        super(context, R.layout.livechina_adapter,datas);
    }

    @Override
    public void convert(ViewHolder holder, HuangShanBean.LiveBean liveBean) {
        final TextView textView= (TextView) holder.itemView.findViewById(R.id.livechina_adapter_jianjie);
        textView.setText(liveBean.getBrief());
        holder.setText(R.id.livechina_adapter_title,liveBean.getTitle());
        ImageView imageView= (ImageView) holder.itemView.findViewById(R.id.livechina_adapter_imge);
        Glide.with(context).load(liveBean.getImage()).into(imageView);
        CheckBox checkBox= (CheckBox) holder.itemView.findViewById(R.id.livechina_adapter_conimag);//checkbox
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
