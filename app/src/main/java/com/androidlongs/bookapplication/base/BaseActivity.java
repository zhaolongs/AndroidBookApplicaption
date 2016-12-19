package com.androidlongs.bookapplication.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public abstract class BaseActivity extends Activity {
    public Context mActivityContext;
    public Context mAppContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentView());
        initView();
        setParamValue();
        commonFunction();
    }

    private void setParamValue() {
        this.mActivityContext = this;
        this.mAppContext = this.getApplicationContext();
    }

    /**
     * 页面文件
     */
    public abstract int getContentView();
    /**
     * 初始化控件
     */
    public abstract void initView();
    /**
     * 常用功能
     */
    public abstract  void commonFunction();
}
