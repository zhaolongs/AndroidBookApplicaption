package com.androidlongs.bookapplication.base;

import com.androidlongs.bookapplication.main.util.SharedPreferencesUtil;
import com.google.gson.Gson;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class BaseFunction {
    public Gson mGson = new Gson();
    public SharedPreferencesUtil mSharedPreferencesUtil = SharedPreferencesUtil.getInstance();
}
