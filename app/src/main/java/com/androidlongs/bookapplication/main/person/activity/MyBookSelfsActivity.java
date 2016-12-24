package com.androidlongs.bookapplication.main.person.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.BaseActivity;
import com.androidlongs.bookapplication.main.home.model.BookModel;
import com.androidlongs.bookapplication.main.person.adapter.MyBookSelfsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androidlongs on 16/12/24.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */


/**
 * 我的书架
 */
public class MyBookSelfsActivity extends BaseActivity {

    private LinearLayout mBackLinearLayout;
    private RecyclerView mRecyclerView;

    @Override
    public int getContentView() {
        return R.layout.activity_person_my_book_selefs;
    }

    @Override
    public void initView() {

        mBackLinearLayout = (LinearLayout) findViewById(R.id.id_ll_my_book_selfs_back);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_rv_my_book_selfs_list);
    }

    @Override
    public void commonFunction() {

        mBookModelList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            BookModel bookModel = new BookModel();
            mBookModelList.add(bookModel);
        }
        setViewClickFunction();
    }

    private void setViewClickFunction() {

        mBackLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBookSelfsActivity.this.finish();
            }
        });

        setRecyDataList();
    }
    private List<BookModel> mBookModelList;
    private MyBookSelfsAdapter mBookSelfsAdapter;
    private void setRecyDataList(){
        if (mBookSelfsAdapter==null) {
            mBookSelfsAdapter = new MyBookSelfsAdapter(mBookModelList);
            mRecyclerView.setAdapter(mBookSelfsAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else {
            mBookSelfsAdapter.setDatas(mBookModelList);
            mBookSelfsAdapter.notifyDataSetChanged();
        }
    }
}
