package com.androidlongs.bookapplication.main.util;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;

/**
 * Created by androidlongs on 16/12/24.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class ToastUtils {
    public static void show(String msg ){

        ToastHelper
                .makeText(App.mContext, msg,ToastHelper.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
    }
}
