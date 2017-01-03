package com.androidlongs.bookapplication.main.home.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.BaseActivity;

/**
 * Created by androidlongs on 17/1/3.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class StartReadingActivity extends BaseActivity {

    private LinearLayout mBackLinearLayout;

    @Override
    public int getContentView() {
        return R.layout.activity_start_reading_book;
    }

    @Override
    public void initView() {

        mBackLinearLayout = (LinearLayout) findViewById(R.id.id_ll_start_reading_book_back);
    }

    @Override
    public void commonFunction() {
        mBackLinearLayout.setOnClickListener(mBackOnClickListener);
    }

    private View.OnClickListener mBackOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StartReadingActivity.this.finish();
        }
    };
}
