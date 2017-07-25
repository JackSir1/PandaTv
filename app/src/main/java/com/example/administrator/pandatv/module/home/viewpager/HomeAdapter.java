package com.example.administrator.pandatv.module.home.viewpager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.administrator.pandatv.model.util.playVideoUtil.PlayViedoActivity;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.model.entity.HomeBean;
import com.example.administrator.pandatv.model.util.saveData.PandaTvBean;
import com.example.administrator.pandatv.model.util.saveData.SaveDataToSD;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    public static final int TYPE1 = 1, TYPE2 = 2, TYPE3 = 3, TYPE4 = 4, TYPE5 = 5;
    private List<Object> objects;
    private Context context;
    private LayoutInflater inflater;


    public HomeAdapter(Context context, List<Object> objects) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public int getItemViewType(int position) {
        Object o = objects.get(position);
        if (o instanceof HomeBean.DataBean.PandaeyeBean) {
            return TYPE1;
        } else if (o instanceof HomeBean.DataBean.PandaliveBean) {
            return TYPE2;
        } else if (o instanceof HomeBean.DataBean.AreaBean) {
            return TYPE3;
        } else if (o instanceof HomeBean.DataBean.WallliveBean) {
            return TYPE4;
        } else if (o instanceof HomeBean.DataBean.ChinaliveBean) {
            return TYPE5;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE1:
                View view1 = inflater.inflate(R.layout.home_pandaobserver_main, parent, false);
                viewHolder = new ViewHolder(context, view1);
                break;
            case TYPE2:
                View view2 = inflater.inflate(R.layout.home_liveplay_main, parent, false);
                viewHolder = new ViewHolder(context, view2);
                break;
            case TYPE3:
                View view3 = inflater.inflate(R.layout.home_splendid_main, parent, false);
                viewHolder = new ViewHolder(context, view3);
                break;
            case TYPE4:
                View view4 = inflater.inflate(R.layout.home_ggvideo_main, parent, false);
                viewHolder = new ViewHolder(context, view4);
                break;
            case TYPE5:
                View view5 = inflater.inflate(R.layout.home_livechina_main, parent, false);
                viewHolder = new ViewHolder(context, view5);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE1:
                ObserverViewHolder observerViewHolder = new ObserverViewHolder(holder.itemView);
                HomeBean.DataBean.PandaeyeBean pandaeyeBean = (HomeBean.DataBean.PandaeyeBean) objects.get(position);
                observerViewHolder.setDate(pandaeyeBean);
                break;
            case TYPE2:
                LivePlayViewHolder livePlayViewHolder = new LivePlayViewHolder(holder.itemView);
                HomeBean.DataBean.PandaliveBean pandaliveBean = (HomeBean.DataBean.PandaliveBean) objects.get(position);
                livePlayViewHolder.setDate(pandaliveBean);
                break;
            case TYPE3:
                SplendidViewHolder splendidViewHolder = new SplendidViewHolder(holder.itemView);
                HomeBean.DataBean.AreaBean area = (HomeBean.DataBean.AreaBean) objects.get(position);
                splendidViewHolder.setDate(area);
                break;
            case TYPE4:
                
                GGViewHolder ggViewHolder = new GGViewHolder(holder.itemView);
                HomeBean.DataBean.WallliveBean wallliveBean = (HomeBean.DataBean.WallliveBean) objects.get(position);
                ggViewHolder.setDate(wallliveBean);

                break;
            case TYPE5:
                LiveChinaViewHolder liveChinaViewHolder = new LiveChinaViewHolder(holder.itemView);
                HomeBean.DataBean.ChinaliveBean chinalive = (HomeBean.DataBean.ChinaliveBean) objects.get(position);
                liveChinaViewHolder.setDate(chinalive);
                break;


            case 0:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return objects.isEmpty() ? 0 : objects.size();
    }

    //精彩一刻
    class SplendidViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {
        private GridView homeSplendidGridView;
        private List<HomeBean.DataBean.AreaBean.ListscrollBean> listscrol2;

        public SplendidViewHolder(View itemView) {
            super(itemView);
            homeSplendidGridView = (GridView) itemView.findViewById(R.id.home_splendid_gridView);
        }

        public void setDate(HomeBean.DataBean.AreaBean area) {
            List<HomeBean.DataBean.AreaBean.ListscrollBean> listscroll = area.getListscroll();

            listscrol2 = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                listscrol2.add(listscroll.get(i));
            }
            HomeSplendidAdapter splendidAdapter = new HomeSplendidAdapter(context, listscrol2);
            homeSplendidGridView.setAdapter(splendidAdapter);
            homeSplendidGridView.setOnItemClickListener(this);

        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HomeBean.DataBean.AreaBean.ListscrollBean listscrollBean = listscrol2.get(position);
            String pid = listscrollBean.getPid();
            String title = listscrollBean.getTitle();

            PandaTvBean pandaTvBean=new PandaTvBean();
            pandaTvBean.setImageView(listscrollBean.getImage());
            pandaTvBean.setContent(listscrollBean.getTitle());
            pandaTvBean.setVideoTime(listscrollBean.getVideoLength());
            pandaTvBean.setPid(listscrollBean.getPid());
            String vid = listscrollBean.getVid();
            if (!vid.equals("")&& vid!=null){
                pandaTvBean.setVid(vid);
            }else {
                pandaTvBean.setVid(listscrollBean.getPid());
            }

            pandaTvBean.setType("1");
            SaveDataToSD addcollect = SaveDataToSD.getInsent();
            addcollect.addcollect(pandaTvBean);

            Intent intent = new Intent(context, PlayViedoActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("pid", pid);
            context.startActivity(intent);
        }
    }

    class LivePlayViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {
        private GridView homePlayLive;
        private List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeans = new ArrayList<>();

        public LivePlayViewHolder(View itemView) {
            super(itemView);
            homePlayLive = (GridView) itemView.findViewById(R.id.home_playLive);
        }

        public void setDate(HomeBean.DataBean.PandaliveBean pandaliveBean) {

            pandaliveBeans.addAll(pandaliveBean.getList());
            HomePandaLiveAdapter pandaLiveAdapter = new HomePandaLiveAdapter(context, pandaliveBeans);
            homePlayLive.setAdapter(pandaLiveAdapter);
            homePlayLive.setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HomeBean.DataBean.PandaliveBean.ListBean listBean = pandaliveBeans.get(position);
            String vid = listBean.getVid();
            String title = listBean.getTitle();

        }
    }

    class ObserverViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView btn1, btn2, title1, title2;
        private HomeBean.DataBean.PandaeyeBean pandaeye;

        public ObserverViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.home_pandaObserver_image);
            btn1 = (TextView) itemView.findViewById(R.id.home_observer_btn1);
            btn2 = (TextView) itemView.findViewById(R.id.home_observer_btn2);
            title1 = (TextView) itemView.findViewById(R.id.home_observer_title1);
            title2 = (TextView) itemView.findViewById(R.id.home_observer_title2);
        }

        public void setDate(HomeBean.DataBean.PandaeyeBean pandaeye) {
            this.pandaeye = pandaeye;
            String pandaeyelogo = pandaeye.getPandaeyelogo();
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(pandaeyelogo).into(imageView);
            btn1.setText(pandaeye.getItems().get(0).getBrief());
            btn2.setText(pandaeye.getItems().get(1).getBrief());

            title1.setText(pandaeye.getItems().get(0).getTitle());
            title2.setText(pandaeye.getItems().get(1).getTitle());

            title1.setOnClickListener(this);
            title2.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.home_observer_title1:
                    String pid = pandaeye.getItems().get(0).getPid();
                    String title = pandaeye.getItems().get(0).getTitle();
                    String id = pandaeye.getItems().get(0).getId();
                    String vid = pandaeye.getItems().get(0).getVid();
                    String url = pandaeye.getItems().get(0).getUrl();

                    SaveDataToSD addcollect = SaveDataToSD.getInsent();

                    Boolean isSave=false;

                    PandaTvBean bean = addcollect.getBean(vid);
                    if (bean!=null){
                        Boolean save = bean.getSave();
                        isSave=save;
                    }

                    PandaTvBean pandaTvBean=new PandaTvBean();
                    pandaTvBean.setImageView("");
                    pandaTvBean.setContent(title);
                    pandaTvBean.setVideoTime("");
                    pandaTvBean.setPid(pid);
                    if (!vid.equals("")){
                        pandaTvBean.setVid(vid);
                    }else {
                        pandaTvBean.setVid(id);
                    }
                    pandaTvBean.setType("1");
                    pandaTvBean.setUrl(url);
                    addcollect.addcollect(pandaTvBean);

                    Intent intent = new Intent(context, PlayViedoActivity.class);
                    intent.putExtra("isSave",isSave);
                    intent.putExtra("title", title);
                    intent.putExtra("pid", pid);
                    context.startActivity(intent);

                    break;
                case R.id.home_observer_title2:
                    String pid1 = pandaeye.getItems().get(1).getPid();
                    String title3 = pandaeye.getItems().get(1).getTitle();
                    String id1 = pandaeye.getItems().get(1).getId();
                    String vid1 = pandaeye.getItems().get(1).getVid();
                    String url1 = pandaeye.getItems().get(1).getUrl();

                    SaveDataToSD addcollect1 = SaveDataToSD.getInsent();

                    Boolean isSave1=false;

                    PandaTvBean bean1 = addcollect1.getBean(vid1);
                    if (bean1!=null){
                        Boolean save = bean1.getSave();
                        isSave1=save;
                    }

                    PandaTvBean pandaTvBean1=new PandaTvBean();
                    pandaTvBean1.setImageView("");
                    pandaTvBean1.setContent(title3);
                    pandaTvBean1.setVideoTime("");
                    pandaTvBean1.setPid(pid1);
                    if (!vid1.equals("")){
                        pandaTvBean1.setVid(vid1);
                    }else {
                        pandaTvBean1.setVid(id1);
                    }
                    pandaTvBean1.setType("1");
                    pandaTvBean1.setUrl(url1);

                    addcollect1.addcollect(pandaTvBean1);

                    Intent intent1 = new Intent(context, PlayViedoActivity.class);
                    intent1.putExtra("isSave",isSave1);
                    intent1.putExtra("title", title3);
                    intent1.putExtra("pid", pid1);
                    context.startActivity(intent1);
                    break;
            }
        }
    }

    class GGViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {

        private List<HomeBean.DataBean.WallliveBean.ListBeanX> wallliveBeanList;
        private ListView home_ggVedio_listView;

        public GGViewHolder(View itemView) {
            super(itemView);
            home_ggVedio_listView = (ListView) itemView.findViewById(R.id.home_ggVedio_listView);
        }

        public void setDate(HomeBean.DataBean.WallliveBean wallliveBean) {


            wallliveBeanList = new ArrayList<>();

            for (int i = 0; i < 6; i++) {
                wallliveBeanList.add(wallliveBean.getList().get(i));
            }
            HomeGGVideoAdapter splendidAdapter = new HomeGGVideoAdapter(context, wallliveBeanList);
            home_ggVedio_listView.setAdapter(splendidAdapter);
            home_ggVedio_listView.setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HomeBean.DataBean.WallliveBean.ListBeanX listBeanX = wallliveBeanList.get(position);
            String title = listBeanX.getTitle();
            String url = listBeanX.getUrl();
        }
    }

    class LiveChinaViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {

        private GridView homeLiveChinaGridView;
        private List<HomeBean.DataBean.ChinaliveBean.ListBeanXX> listBeanXXList;

        public LiveChinaViewHolder(View itemView) {
            super(itemView);
            homeLiveChinaGridView = (GridView) itemView.findViewById(R.id.home_liveChina_gridView);
        }

        public void setDate(HomeBean.DataBean.ChinaliveBean chinalive) {

            listBeanXXList = new ArrayList<>();
            listBeanXXList=chinalive.getList();
            HomeChinaLiveAdapter chinaLiveAdapter = new HomeChinaLiveAdapter(context, chinalive.getList());
            homeLiveChinaGridView.setAdapter(chinaLiveAdapter);
            homeLiveChinaGridView.setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HomeBean.DataBean.ChinaliveBean.ListBeanXX listBeanXX = listBeanXXList.get(position);
            String id1 = listBeanXX.getId();
            String vid = listBeanXX.getVid();
            String title = listBeanXX.getTitle();
        }
    }
}
