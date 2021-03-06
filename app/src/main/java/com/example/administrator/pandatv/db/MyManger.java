package com.example.administrator.pandatv.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.pandatv.model.entity.livechinaEntity.LoginEntity;

import java.util.ArrayList;
import java.util.List;


public class MyManger {
    private MyHepler myHepler;
    private SQLiteDatabase mDB;
    private Context context;
    private final String DB_NAME = "lisuyun.db";
    private final int DB_VERSION = 1;

    public MyManger(Context context) {
        this.context = context;
        myHepler = new MyHepler(context, DB_NAME, DB_VERSION);
        mDB = myHepler.getWritableDatabase();
    }

    /**
     * 插入数据库的内容
     *
     * @return
     */
    public boolean insert(String coodie, String name, String pwd) {
        boolean boo;
        ContentValues values = new ContentValues();
        values.put("uid", coodie);
        values.put("name", name);
        values.put("pwd", pwd);

        long insert = mDB.insert("lisuyun", null, values);

        if (insert > 0) {
            boo = true;
        } else {
            boo = false;
        }
        return boo;
    }

    /**
     * 查询数据库
     */
    public List<LoginEntity> QueryAll() {
        List<LoginEntity> mList = new ArrayList<>();
        Cursor cursor = mDB.query("lisuyun", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            LoginEntity bean = new LoginEntity();
//            bean.getUsrid().(cursor.getString(cursor.getColumnIndex("name")));
//            bean.getUser().setFans(cursor.getString(cursor.getColumnIndex("pwd")));
//            bean.getUser().setUid(cursor.getString(cursor.getColumnIndex("uid")));
            mList.add(bean);
        }

        return mList;
    }

    public String QueryUid() {
        String str = new String();
        Cursor chao = mDB.query("lisuyun", null, null, null, null, null, null);
        boolean id = str.contains(chao.getString(chao.getColumnIndex("id")));
        return str;
    }


}
