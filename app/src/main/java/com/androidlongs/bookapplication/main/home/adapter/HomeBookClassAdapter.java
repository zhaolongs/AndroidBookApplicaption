package com.androidlongs.bookapplication.main.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.main.home.inter.OnBookListItemClickLiserner;
import com.androidlongs.bookapplication.main.home.model.BookClassModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androidlongs on 16/12/19.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class HomeBookClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BookClassModel> mBookClassList;

    public HomeBookClassAdapter(List<BookClassModel> bookClassModels) {
        if (bookClassModels == null) {
            bookClassModels = new ArrayList<>();
        }
        this.mBookClassList = bookClassModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(App.mContext, R.layout.item_home_book_class, null);
        BookClassViewHolder bookClassViewHolder = new BookClassViewHolder(view);
        return bookClassViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BookClassViewHolder classViewHolder = (BookClassViewHolder) holder;

        classViewHolder.setDatas(mBookClassList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return this.mBookClassList.size();
    }

    public void setDatas(List<BookClassModel> bookClassModels) {
        this.mBookClassList = bookClassModels;
    }

    private class BookClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageView mTitleImageView;
        private final TextView mNameTextView;
        private final TextView mDesTextView;

        public BookClassViewHolder(View itemView) {
            super(itemView);
            mTitleImageView = (ImageView) itemView.findViewById(R.id.id_iv_book_class_item_title);
            mNameTextView = (TextView) itemView.findViewById(R.id.id_book_class_item_name);
            mDesTextView = (TextView) itemView.findViewById(R.id.id_book_class_item_des);

            itemView.setOnClickListener(this);
        }

        //当前点击的位置
        private int mPosition =0;
        public void setDatas(BookClassModel bookClassModel, int position) {
            String description = bookClassModel.description;
            String name = bookClassModel.name;
            if (TextUtils.isEmpty(name)) {
                name = "未知";
            }
            if (TextUtils.isEmpty(description)) {
                description = "无";
            }
            mNameTextView.setText(name);
            mDesTextView.setText(description);

            mPosition = position;
        }

        @Override
        public void onClick(View v) {
            if (mOnBookListItemClickLiserner != null) {
                mOnBookListItemClickLiserner.onNormalClick(v,mPosition);
            }
        }
    }

    public OnBookListItemClickLiserner mOnBookListItemClickLiserner;

    public void setOnBookListItemClickLiserner(OnBookListItemClickLiserner onBookListItemClickLiserner) {
        mOnBookListItemClickLiserner = onBookListItemClickLiserner;
    }
}
