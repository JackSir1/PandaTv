package com.example.administrator.pandatv.module.chinaLive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.livechinaEntity.LiveFHGCBean;

import java.util.List;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class Bdadapterfhgc extends RecyclerView.Adapter<Bdadapterfhgc.Holder> {
    private Context mCon;
    private List<LiveFHGCBean.LiveBean> mList;

    public Bdadapterfhgc(Context mCon, List<LiveFHGCBean.LiveBean> mList) {
        this.mCon = mCon;
        this.mList = mList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.livechina_adapter,null);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        LiveFHGCBean.LiveBean liveBean = mList.get(position);
        holder.title.setText(liveBean.getTitle());
        holder.textView.setText(liveBean.getBrief());
        Glide.with(mCon).load(liveBean.getImage()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        private TextView textView,title;
        private LinearLayout layout;
        ImageView imageView;
        CheckBox checkbox;
        public Holder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.livechina_adapter_imge);
            checkbox= (CheckBox) itemView.findViewById(R.id.livechina_adapter_conimag);
            textView= (TextView) itemView.findViewById(R.id.livechina_adapter_jianjie);
            title= (TextView) itemView.findViewById(R.id.livechina_adapter_title);
            layout= (LinearLayout) itemView.findViewById(R.id.livechina_ada_linear_jianjie);
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
}
