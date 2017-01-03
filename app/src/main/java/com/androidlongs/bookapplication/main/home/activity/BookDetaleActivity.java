package com.androidlongs.bookapplication.main.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.BaseActivity;
import com.androidlongs.bookapplication.main.util.LogUtils;
import com.androidlongs.bookapplication.main.util.ToastUtils;

/**
 * Created by androidlongs on 17/1/3.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class BookDetaleActivity extends BaseActivity {

    private LinearLayout mBackLinearLayout;
    //添加到书架
    private TextView mAddBookClassTextView;
    private TextView mStartReadTextView;

    @Override
    public int getContentView() {
        return R.layout.activity_book_detale;
    }

    @Override
    public void initView() {
        mBackLinearLayout = (LinearLayout) findViewById(R.id.id_ll_book_back);

        mAddBookClassTextView = (TextView) findViewById(R.id.id_read_add_book_selfs);
        mStartReadTextView = (TextView) findViewById(R.id.id_read_start_reading);

    }

    @Override
    public void commonFunction() {

        mBackLinearLayout.setOnClickListener(mBackClickListener);
        mAddBookClassTextView.setOnClickListener(mAddBookClassClickListener);
        mStartReadTextView.setOnClickListener(mReadBookingClickListener);
    }

    //退出
    private View.OnClickListener mBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BookDetaleActivity.this.finish();
        }
    };

    //添加到书架
    private View.OnClickListener mAddBookClassClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addBookClassFunction();
        }
    };

    //添加到书架
    private void addBookClassFunction() {
        LogUtils.d("添加到书架");
        ToastUtils.show("已添加到书架");
        mAddBookClassTextView.setText("已添加到书架");

    }

    //开始阅读
    private View.OnClickListener mReadBookingClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           startReadingFunction();
        }
    };

    //开始阅读
    private void startReadingFunction() {
        LogUtils.d("开始阅读");
        Intent intent = new Intent(BookDetaleActivity.this,StartReadingActivity.class);
        startActivity(intent);
    }
}
