package com.example.administrator.pandatv.module.home.viewpager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.HomeBean;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    public static final int TYPE1=1,TYPE2=2,TYPE3=3,TYPE4=4,TYPE5=5;
    private List<Object> objects;
    private Context context;
    private LayoutInflater inflater;
    public HomeAdapter(Context context,List<Object> objects){
        inflater=LayoutInflater.from(context);
        this.context=context;
        this.objects=objects;
    }

    @Override
    public int getItemViewType(int position) {
        Object o=objects.get(position);
        if (o instanceof HomeBean.DataBean.PandaeyeBean){
            return TYPE1;
        }else if (o instanceof HomeBean.DataBean.PandaliveBean){
            return TYPE2;
        }else if (o instanceof HomeBean.DataBean.AreaBean){
            return TYPE3;
        }else if (o instanceof HomeBean.DataBean.WallliveBean){
            return TYPE4;
        }else if (o instanceof HomeBean.DataBean.ChinaliveBean){
            return TYPE5;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType){
            case TYPE1:
                View view1 = inflater.inflate(R.layout.home_pandaobserver_main, parent, false);
                viewHolder=new ViewHolder(context,view1);
                break;
            case TYPE2:
                View view2 = inflater.inflate(R.layout.home_liveplay_main, parent, false);
                viewHolder=new ViewHolder(context,view2);
                break;
            case TYPE3:
                View view3 = inflater.inflate(R.layout.home_splendid_main, parent, false);
                viewHolder=new ViewHolder(context,view3);
                break;
            case TYPE4:
                View view4 = inflater.inflate(R.layout.home_ggvideo_main, parent, false);
                viewHolder=new ViewHolder(context,view4);
                break;
            case TYPE5:
                View view5 = inflater.inflate(R.layout.home_livechina_main, parent, false);
                viewHolder=new ViewHolder(context,view5);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case TYPE1:
                ObserverViewHolder observerViewHolder=new ObserverViewHolder(holder.itemView);
                HomeBean.DataBean.PandaeyeBean pandaeyeBean= (HomeBean.DataBean.PandaeyeBean) objects.get(position);
                observerViewHolder.setDate(pandaeyeBean);
                break;
            case TYPE2:
                LivePlayViewHolder livePlayViewHolder=new LivePlayViewHolder(holder.itemView);
                HomeBean.DataBean.PandaliveBean pandaliveBean= (HomeBean.DataBean.PandaliveBean) objects.get(position);
                livePlayViewHolder.setDate(pandaliveBean);
                break;
            case TYPE3:
                SplendidViewHolder splendidViewHolder=new SplendidViewHolder(holder.itemView);
                HomeBean.DataBean.AreaBean area= (HomeBean.DataBean.AreaBean) objects.get(position);
                splendidViewHolder.setDate(area);
                break;
            case TYPE4:
                GGViewHolder ggViewHolder=new GGViewHolder(holder.itemView);
                HomeBean.DataBean.WallliveBean wallliveBean= (HomeBean.DataBean.WallliveBean) objects.get(position);
                ggViewHolder.setDate(wallliveBean);
                break;
            case TYPE5:
                LiveChinaViewHolder liveChinaViewHolder=new LiveChinaViewHolder(holder.itemView);
                HomeBean.DataBean.ChinaliveBean chinalive= (HomeBean.DataBean.ChinaliveBean) objects.get(position);
                liveChinaViewHolder.setDate(chinalive);
                break;
            case 0:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return objects.isEmpty()?0: objects.size();
    }

    //精彩一刻
    class SplendidViewHolder extends RecyclerView.ViewHolder{
        private GridView homeSplendidGridView;
        public SplendidViewHolder(View itemView) {
            super(itemView);
            homeSplendidGridView= (GridView) itemView.findViewById(R.id.home_splendid_gridView);
        }
        public void setDate(HomeBean.DataBean.AreaBean area){
            List<HomeBean.DataBean.AreaBean.ListscrollBean> listscroll = area.getListscroll();
            List<HomeBean.DataBean.AreaBean.ListscrollBean> listscrol2=new ArrayList<>();
            for (int i=0;i<4;i++){
                listscrol2.add(listscroll.get(i));
            }
            HomeSplendidAdapter splendidAdapter=new HomeSplendidAdapter(context,listscrol2);
            homeSplendidGridView.setAdapter(splendidAdapter);
        }
    }
    class LivePlayViewHolder extends RecyclerView.ViewHolder{
        private GridView homePlayLive;
        public LivePlayViewHolder(View itemView) {
            super(itemView);
            homePlayLive= (GridView) itemView.findViewById(R.id.home_playLive);
        }
        public void setDate(HomeBean.DataBean.PandaliveBean pandaliveBean){
            List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeans=new ArrayList<>();
            pandaliveBeans.addAll(pandaliveBean.getList());
            HomePandaLiveAdapter pandaLiveAdapter=new HomePandaLiveAdapter(context,pandaliveBeans);
            homePlayLive.setAdapter(pandaLiveAdapter);
        }
    }
    class ObserverViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView  btn1,btn2,title1,title2;
        public ObserverViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.home_pandaObserver_image);
            btn1= (TextView) itemView.findViewById(R.id.home_observer_btn1);
            btn2= (TextView) itemView.findViewById(R.id.home_observer_btn2);
            title1= (TextView) itemView.findViewById(R.id.home_observer_title1);
            title2= (TextView) itemView.findViewById(R.id.home_observer_title2);
        }
        public void setDate(HomeBean.DataBean.PandaeyeBean pandaeye){
            String pandaeyelogo = pandaeye.getPandaeyelogo();
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(pandaeyelogo).into(imageView);
            btn1.setText(pandaeye.getItems().get(0).getBrief());
            btn2.setText(pandaeye.getItems().get(1).getBrief());
            title1.setText(pandaeye.getItems().get(0).getTitle());
            title2.setText(pandaeye.getItems().get(0).getTitle());
        }
    }
    class GGViewHolder extends RecyclerView.ViewHolder{

        private ListView home_ggVedio_listView;
        public GGViewHolder(View itemView) {
            super(itemView);
            home_ggVedio_listView= (ListView) itemView.findViewById(R.id.home_ggVedio_listView);
        }
        public void setDate(HomeBean.DataBean.WallliveBean wallliveBean){

            List<HomeBean.DataBean.WallliveBean.ListBeanX> wallliveBeanList=new ArrayList<>();
            for (int i = 0; i <6 ; i++) {
                wallliveBeanList.add(wallliveBean.getList().get(i));
            }
            HomeGGVideoAdapter splendidAdapter=new HomeGGVideoAdapter(context,wallliveBeanList);
            home_ggVedio_listView.setAdapter(splendidAdapter);
        }
    }
    class LiveChinaViewHolder extends  RecyclerView.ViewHolder{

        private GridView homeLiveChinaGridView;
        public LiveChinaViewHolder(View itemView) {
            super(itemView);
            homeLiveChinaGridView= (GridView) itemView.findViewById(R.id.home_liveChina_gridView);
        }
        public void setDate(HomeBean.DataBean.ChinaliveBean chinalive) {
            HomeChinaLiveAdapter chinaLiveAdapter=new HomeChinaLiveAdapter(context,chinalive.getList());
            homeLiveChinaGridView.setAdapter(chinaLiveAdapter);
        }
    }
}
