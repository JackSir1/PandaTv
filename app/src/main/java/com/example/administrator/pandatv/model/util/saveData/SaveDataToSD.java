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
    private ACache collectACache = ACache.get(App.content,"collect");
    private List<String> list=new ArrayList<>();
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
        collectACache = ACache.get(App.content,"collect");
        list=new ArrayList<>();
        list= (List<String>) collectACache.getAsJSONObject("vidList");
    }

    //收藏和历史记录
    public SaveDataToSD addcollect(PandaTvBean pandaTvBean) {
        if (list==null  || list.size()==0){
            list=new ArrayList<>();
        }
        collectACache.put(pandaTvBean.getVid(),pandaTvBean);
        list.add(pandaTvBean.getVid());
        collectACache.put("vidList",list);

        return this;
    }

    public PandaTvBean getBean(String vid){

        PandaTvBean pandaTvBean= (PandaTvBean) collectACache.getAsObject(vid);

        return pandaTvBean;
    }

    public List<PandaTvBean> getHistoryConllect() {
        List<PandaTvBean> objectList = new ArrayList<>();

        if (!list.isEmpty() && list.size() > 0) {
            for (String vid:list){
                PandaTvBean pandaTvBean= (PandaTvBean) collectACache.getAsObject(vid);
                objectList.add(pandaTvBean);
            }
            return objectList;
        }
        return null;
    }

    public List<PandaTvBean> getSaveCollect() {

        List<PandaTvBean> objectList = new ArrayList<>();
        if (list!=null&&list.size()>0) {
            for (String key : list) {
                PandaTvBean pandaTvBean = (PandaTvBean) collectACache.getAsObject(key);
                if (pandaTvBean.getSave()) {
                    objectList.add(pandaTvBean);
                }
            }
        }

        return objectList;
    }

    public void removeConllect(String Vid) {

        for (int i=0;i<list.size();i++){
            if (list.get(i).equals(Vid)){
                list.remove(i);
            }
        }
        collectACache.put("vidList",list);
        collectACache.remove(Vid);
    }

    //观看历史
    public SaveDataToSD seeHostory(Map<String, Object> map) {
        return this;
    }

}
