package com.example.administrator.pandatv.model.util.saveData;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.example.administrator.pandatv.app.App;
import com.example.administrator.pandatv.model.entity.PandaObserverBean;
import com.example.administrator.pandatv.model.util.ACache;

import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/23.
 */

public class SaveDataToSD {
    private static SaveDataToSD saveDataToSD;
    private static Context context;
    private ACache collectACache;
    private ArrayList<PandaTvBean> arrayList = new ArrayList<>();
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
        this.map = new ArrayMap<>();
        Map<String, PandaTvBean> map2 = (Map<String, PandaTvBean>) collectACache.getAsJSONObject("collect");
        if (map2 != null && map2.size() > 0) {
            this.map = map2;
        }


    }

    //收藏和历史记录
    public SaveDataToSD addcollect(PandaTvBean pandaTvBean) {

        collectACache = ACache.get(App.content, "collect");
        refreshMap();
        String vid = pandaTvBean.getVid();
        map.put(vid, pandaTvBean);
        try {
            collectACache.put("collect", map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    public PandaTvBean getBean(String vid){
        collectACache = ACache.get(App.content, "collect");
        refreshMap();
        Map<String, PandaTvBean> map = null;
        map = (Map<String, PandaTvBean>) collectACache.getAsJSONObject("collect");
        PandaTvBean pandaTvBean=null;
        if (map!=null){

            pandaTvBean = map.get(vid);
        }

        return pandaTvBean;
    }

    public List<PandaTvBean> getHistoryConllect() {
        Map<String, PandaTvBean> map = null;

        map = (Map<String, PandaTvBean>) collectACache.getAsJSONObject("collect");

        if (!map.isEmpty() && map.size() > 0) {
            Set<String> strings = map.keySet();
            List<PandaTvBean> objectList = new ArrayList<>();
            for (String o : strings) {
                PandaTvBean pandaTvBean = map.get(o);
                objectList.add(pandaTvBean);
            }
            return objectList;
        }
        return null;
    }

    public List<PandaTvBean> getSaveCollect() {
        collectACache = ACache.get(App.content, "collect");
        refreshMap();
        Set<String> strings = map.keySet();
        List<PandaTvBean> pandaTvBeanList = new ArrayList<>();
        for (String key : strings) {
            PandaTvBean pandaTvBean = map.get(key);
            if (pandaTvBean.getSave()) {
                pandaTvBeanList.add(pandaTvBean);
            }
        }

        return pandaTvBeanList;
    }

    public void removeConllect(String Vid) {
        collectACache = ACache.get(App.content, "collect");
        refreshMap();
        map.remove(Vid);
        try {
            collectACache.put("collect", map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //观看历史
    public SaveDataToSD seeHostory(Map<String, Object> map) {
        return this;
    }

}
