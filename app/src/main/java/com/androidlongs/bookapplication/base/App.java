package com.androidlongs.bookapplication.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class App extends Application {
    public static Context mContext;

    public static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler(getMainLooper());
    }



}
