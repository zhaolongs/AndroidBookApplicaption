package com.androidlongs.bookapplication.main.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.AppConfigFile;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class SharedPreferencesUtil {

    private SharedPreferences mSharedPreferences;

    private SharedPreferencesUtil(){
        initFunction();
    }

    private void initFunction() {
        mSharedPreferences = App.mContext.getSharedPreferences(AppConfigFile.cacheFileName,
                Activity.MODE_PRIVATE);

    }

    private static class  SinglSharePreferendes{
        private static SharedPreferencesUtil sSharedPreferencesUtil = new SharedPreferencesUtil();
    }
    public static SharedPreferencesUtil getInstance(){
        return SinglSharePreferendes.sSharedPreferencesUtil;
    }


    public void saveData(String name,String value){
        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(value)) {
            //实例化SharedPreferences.Editor对象
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            //用putString的方法保存数据
            editor.putString(name, value);
            //提交当前数据
            editor.apply();
        }
    }

    public String getData(String name){
        if (!TextUtils.isEmpty(name)) {
            return mSharedPreferences.getString(name,"");
        }
        return null;
    }
    public void removeData(String name){
        if (!TextUtils.isEmpty(name)) {
            mSharedPreferences.edit().remove(name).commit();
        }
    }


}
