package com.example.administrator.pandatv.module.chinaLive.tianshan;

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
import com.example.administrator.pandatv.model.entity.livechinaEntity.TianShanBean;

import java.util.List;

/**
 * Created by lizhuofang on 2017/7/15.
 */
public class TianshanAdapter extends BaseAdapter<TianShanBean.LiveBean>{
    public TianshanAdapter(Context context, List<TianShanBean.LiveBean> datas) {
        super(context, R.layout.livechina_adapter, datas);
    }

    @Override
    public void convert(ViewHolder holder, TianShanBean.LiveBean tianShanBean) {
        final TextView textView= (TextView) holder.itemView.findViewById(R.id.livechina_adapter_jianjie);
        textView.setText(tianShanBean.getBrief());
        holder.setText(R.id.livechina_adapter_title,tianShanBean.getTitle());
        ImageView imageview = (ImageView) holder.itemView.findViewById(R.id.livechina_adapter_imge);
        Glide.with(context).load(tianShanBean.getImage()).into(imageview);
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
