package com.example.administrator.pandatv.module.chinaLive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.pandatv.R;

import java.util.List;

/**
 * Created by lizhuofang on 2017/7/24.
 */

public class HistoryInfoAdapter extends RecyclerView.Adapter<HistoryInfoAdapter.ViewHolder> {

    private Context mCon;
    private List<String> mList;

    public HistoryInfoAdapter(Context mCon, List<String> mList) {
        this.mCon = mCon;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_info,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView mTitle,mTime;
        CheckBox radioButton;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.history_image);
            mTitle= (TextView) itemView.findViewById(R.id.history_info_title);
            mTime= (TextView) itemView.findViewById(R.id.history_info_time);
            radioButton= (CheckBox) itemView.findViewById(R.id.history_radiobutton);
        }
    }
    public interface OnClickListeren{
        void getListeren(View view,int position);
    }
    public OnClickListeren onClickListeren;
    public void setOnclick(OnClickListeren onClickListeren){
        this.onClickListeren=onClickListeren;
    }

}
