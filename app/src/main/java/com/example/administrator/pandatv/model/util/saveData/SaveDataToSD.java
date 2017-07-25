package com.example.administrator.pandatv.model.util.saveData;

import android.os.Build;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.model.util.ACache;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/23.
 */

public class SaveDataToSD {
    private static SaveDataToSD saveDataToSD;
    private ACache collectACache = ACache.get(App.content, "collect");
    private JSONArray list = new JSONArray();
    private Map<String, PandaTvBean> map;

    private SaveDataToSD() {
    }

    public static SaveDataToSD getInsent() {
        if (saveDataToSD == null) {
            synchronized (SaveDataToSD.class) {
                if (saveDataToSD == null) {
                    saveDataToSD = new SaveDataToSD();
                }
            }
        }
        return saveDataToSD;
    }

    public void refreshMap() {
        collectACache = ACache.get(App.content, "collect");
        list = new JSONArray();
        list= collectACache.getAsJSONArray("vidList");
    }

    //收藏和历史记录
    public SaveDataToSD addcollect(PandaTvBean pandaTvBean) {
        if (list == null || list.length()==0) {
            list = new JSONArray();
        }
        collectACache.put(pandaTvBean.getVid(), pandaTvBean);
        list.put(pandaTvBean.getVid());

        collectACache.put("vidList", list);

        return this;
    }

    public PandaTvBean getBean(String vid) {

        PandaTvBean pandaTvBean = (PandaTvBean) collectACache.getAsObject(vid);

        return pandaTvBean;
    }

    public List<PandaTvBean> getHistoryConllect() {
        List<PandaTvBean> objectList = new ArrayList<>();
        if (list!=null && list.length() > 0) {

            for (int i=0;i<list.length();i++){
                try {
                    String vid = list.getString(i);
                    PandaTvBean pandaTvBean = (PandaTvBean) collectACache.getAsObject(vid);
                    objectList.add(pandaTvBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
        return objectList;
    }

    public List<PandaTvBean> getSaveCollect() {

        List<PandaTvBean> objectList = new ArrayList<>();
        if (list != null && list.length() > 0) {
            for (int i=0;i<list.length();i++) {
                try {
                    String key = list.getString(i);
                    PandaTvBean pandaTvBean = (PandaTvBean) collectACache.getAsObject(key);
                    if (pandaTvBean.getSave()) {
                        objectList.add(pandaTvBean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        return objectList;
    }

    public void removeConllect(String Vid) {

        for (int i = 0; i < list.length(); i++) {
            try {
                if (list.get(i).equals(Vid)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        list.remove(i);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        collectACache.put("vidList", list);
        collectACache.remove(Vid);
    }

    //观看历史
    public SaveDataToSD seeHostory(Map<String, Object> map) {
        return this;
    }

}
