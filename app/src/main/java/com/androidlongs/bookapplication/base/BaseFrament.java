package com.androidlongs.bookapplication.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public abstract class BaseFrament extends Fragment {
    public Gson mGson = new Gson();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(App.mContext, getContentView(), null);
        initView(view);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        commonFunction();
    }


    /**
     * 页面文件
     */
    public abstract int getContentView();

    /**
     * 初始化控件
     *
     * @param view
     */
    public abstract void initView(View view);

    /**
     * 常用功能
     */
    public abstract void commonFunction();
}
