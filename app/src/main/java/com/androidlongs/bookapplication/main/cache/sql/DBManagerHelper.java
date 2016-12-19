package com.androidlongs.bookapplication.main.cache.sql;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.main.util.LogUtils;

/**
 * Created by androidlongs on 16/12/19.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class DBManagerHelper extends SQLiteOpenHelper {


    public DBManagerHelper() {
        super(App.mContext, "book_cache", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createBookListSql =
                "create table books(uuid INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        " id varchar(100),name varchar(100),author varchar(20),description varchar(300)," +
                        "path varchar(100), filename varchar(20) ,categoryId varchar(100),addTime varchar(100))";
        LogUtils.d("构建建语句 ");
        String createBookClassSql =
                "create table book_class(uuid INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " id varchar(100) , name varchar(100),description varchar(500),path varchar(100)," +
                        " addTime varchar(100))";
        LogUtils.d("开始创建表");
        db.execSQL(createBookListSql);
        db.execSQL(createBookClassSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
