package com.androidlongs.bookapplication.main.home.inter;

import android.view.View;

/**
 * Created by androidlongs on 16/12/31.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public interface OnBookListItemClickLiserner {
    void onNormalClick(View view,int postion);
    void onLongClick(View view,int postion);
}
