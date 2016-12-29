package com.androidlongs.bookapplication.main.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.main.home.model.BookModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androidlongs on 16/12/19.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class HomeBookListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BookModel> mBookModelList;

    public HomeBookListAdapter(List<BookModel> bookModelList) {
        if (bookModelList == null) {
            bookModelList = new ArrayList<>();
        }
        this.mBookModelList = bookModelList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(App.mContext, R.layout.item_home_book_list, null);
        BookListViewHoler bookListViewHoler = new BookListViewHoler(view);
        return bookListViewHoler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        BookListViewHoler viewHoler = (BookListViewHoler) holder;
        BookModel bookModel = this.mBookModelList.get(position);
        viewHoler.setData(bookModel, position);
    }

    @Override
    public int getItemCount() {
        return mBookModelList.size();
    }

    public void setListData(List<BookModel> bookModelList) {
        this.mBookModelList = bookModelList;
    }


    private class BookListViewHoler extends RecyclerView.ViewHolder {

        private final ImageView mTitleImageView;
        private final TextView mTitleTextView;
        private final TextView mDesTextView;

        public BookListViewHoler(View itemView) {
            super(itemView);
            mTitleImageView = (ImageView) itemView.findViewById(R.id.id_iv_book_list_item_title);
            mTitleTextView = (TextView) itemView.findViewById(R.id.id_book_list_item_name);
            mDesTextView = (TextView) itemView.findViewById(R.id.id_book_list_item_des);
        }

        public void setData(BookModel bookModel, int position) {

            String name = bookModel.bname;
            String description = bookModel.bdesc;

            if (TextUtils.isEmpty(name)) {
                name = "无";
            }

            if (TextUtils.isEmpty(description)) {
                name = "无";
            }

            mTitleTextView.setText(name);
            mDesTextView.setText(description);
        }
    }
}
