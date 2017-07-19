package com.example.administrator.pandatv.module.pandaLive.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.administrator.pandatv.R;
import com.example.administrator.pandatv.base.BaseFragment;
import com.example.administrator.pandatv.model.entity.livechinaEntity.Watch;
import com.example.administrator.pandatv.module.pandaLive.fragment.adapter.ChatAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//边看边聊

public class ChatFragment extends BaseFragment  {
    private Watch watch;
   ArrayList<Watch.Bean> beanArrayList = new ArrayList<Watch.Bean>();
    @BindView(R.id.recycler_chat)
    PullToRefreshRecyclerView recyclerChat;
    Unbinder unbinder;
    private ChatAdapter adapter;
    private int page = 1;



    @Override
    protected int getViweId() {
        return R.layout.pandalive_chat;
    }

    @Override
    protected void initView(View view) {

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerChat.setLayoutManager(manager);

        recyclerChat.setLoadingMoreEnabled(false);
        recyclerChat.setPullRefreshEnabled(false);
    /*
        recyclerChat.setPullToRefreshListener(this);*/

    }

    @Override
    protected void loadDate() {

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://newcomment.cntv.cn/comment/list?app=ipandaApp&itemid=zhiboye_chat&nature=1&page="+page);
                    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                    huc.setDoInput(true);

                    InputStream inputStream = huc.getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    int re=0;
                    byte[] byt = new byte[1024];

                    while ((re=inputStream.read(byt))!=-1){
                        buffer.append(new String(byt,0,re));
                    }

                    inputStream.close();
                    huc.disconnect();

                    JSONObject object = new JSONObject(buffer.toString());
                    watch = new Watch();
                    watch.setTime(object.getInt("time"));
                    final JSONObject data = object.getJSONObject("data");
                    watch.setTotal(data.getString("total"));
                    JSONArray content = data.getJSONArray("content");
                    final ArrayList<Watch.Bean> arrayList = new ArrayList<>();
                    for (int i=0;i<content.length();i++){
                        JSONObject jsonObject = content.getJSONObject(i);
                        Watch.Bean bean = new Watch.Bean();
                        bean.setAuthor(jsonObject.getString("author"));
                        bean.setAuthorId(jsonObject.getString("authorid"));
                        bean.setDateline(jsonObject.getString("dateline"));
                        bean.setLocale(jsonObject.getString("locale"));
                        bean.setMessage(jsonObject.getString("message"));
                        bean.setPid(jsonObject.getString("pid"));
                        bean.setTid(jsonObject.getString("tid"));
                        arrayList.add(bean);
                    }
                    watch.setList(arrayList);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            arrayList.addAll(watch.getList());
                          //  Log.e("TAG",arrayList.size()+"");
                            adapter = new ChatAdapter(getContext(),arrayList);

                            recyclerChat.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    @Override
    protected void setListener() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

   /* @Override
    public void onRefresh() {

    }
*/
  /*  @Override
    public void onLoadMore() {
        recyclerChat.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                loadDate();
                adapter.notifyDataSetChanged();
                recyclerChat.setLoadMoreComplete();
            }
        },200);*/

 //  }
}
