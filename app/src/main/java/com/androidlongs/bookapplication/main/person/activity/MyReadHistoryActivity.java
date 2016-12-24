package com.androidlongs.bookapplication.main.person.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.BaseActivity;

/**
 * Created by androidlongs on 16/12/24.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */


/**
 * 我的书架
 */
public class MyReadHistoryActivity extends BaseActivity {

    private LinearLayout mBackLinearLayout;

    @Override
    public int getContentView() {
        return R.layout.activity_person_my_read_history;
    }

    @Override
    public void initView() {

        mBackLinearLayout = (LinearLayout) findViewById(R.id.id_ll_my_book_read_history_back);
    }

    @Override
    public void commonFunction() {

        setViewClickFunction();
    }

    private void setViewClickFunction() {

        mBackLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyReadHistoryActivity.this.finish();
            }
        });
    }
}
