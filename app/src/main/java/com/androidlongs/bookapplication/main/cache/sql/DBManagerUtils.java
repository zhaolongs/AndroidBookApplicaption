package com.androidlongs.bookapplication.main.cache.sql;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

/**
 * Created by androidlongs on 16/12/20.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class DBManagerUtils {

    private final DBManagerHelper mHelper;


    private DBManagerUtils() {
        mHelper = new DBManagerHelper();
    }

    private static class SingleDBManager {
        private static DBManagerUtils sDBManagerUtils = new DBManagerUtils();
    }

    public static DBManagerUtils getInstance() {
        return SingleDBManager.sDBManagerUtils;
    }

    public void exeData(String sql) {
        if (TextUtils.isEmpty(sql)) {
            return;
        }

        SQLiteDatabase mWritableDatabase = mHelper.getWritableDatabase();


        mWritableDatabase.execSQL(sql);

        mWritableDatabase.close();

    }

    public void exeData(String sql,String[] values) {
        if (TextUtils.isEmpty(sql)) {
            return;
        }

        SQLiteDatabase mWritableDatabase = mHelper.getWritableDatabase();


        mWritableDatabase.rawQuery(sql,values);

        mWritableDatabase.close();

    }
    public void exeDataTranscation(String sql) {
        if (TextUtils.isEmpty(sql)) {
            return;
        }

        SQLiteDatabase mWritableDatabase = mHelper.getWritableDatabase();


        mWritableDatabase.beginTransaction();//开启事务
        try {
            mWritableDatabase.execSQL(sql);
            mWritableDatabase.setTransactionSuccessful();//设置事务的标志为True
        } catch (Exception e) {

        } finally {
            mWritableDatabase.endTransaction();//结束事务,有两种情况：commit,rollback,
            //事务的提交或回滚是由事务的标志决定的,如果事务的标志为True，事务就会提交，否侧回滚,默认情况下事务的标志为False
        }


        mWritableDatabase.close();

    }

    public DBManagerHelper getHelper() {
        return mHelper;
    }
}
