package com.androidlongs.bookapplication.main.person.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.main.home.model.BookModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androidlongs on 16/12/24.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class MyBookSelfsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  List<BookModel> mBookModelList;
    public MyBookSelfsAdapter(List<BookModel> bookModelList) {
        if (bookModelList==null) {
            bookModelList = new ArrayList<>();
        }
        this.mBookModelList = bookModelList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(App.mContext, R.layout.item_my_book_selfs,null);
        BookSelfsViewHolder holder = new BookSelfsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        BookModel bookModel = mBookModelList.get(position);
        ((BookSelfsViewHolder)holder).setData(bookModel,position);
    }

    @Override
    public int getItemCount() {
        return mBookModelList.size();
    }
    public void setDatas(List<BookModel> list){
        this.mBookModelList = list;
    }
    private  class  BookSelfsViewHolder extends RecyclerView.ViewHolder{

        public BookSelfsViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(BookModel bookModel, int position) {

        }
    }
}
