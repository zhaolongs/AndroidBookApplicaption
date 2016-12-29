package com.androidlongs.bookapplication.main.forum.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.main.forum.model.ForumModel;

import java.util.List;

/**
 * Created by androidlongs on 16/12/29.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class ForumMainAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<ForumModel> mForumModelList;

    public ForumMainAdapter(List<ForumModel> forumModelList) {
        this.mForumModelList = forumModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(App.mContext, R.layout.item_forum_main,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ((Holder)holder).setDatas(position);
    }

    @Override
    public int getItemCount() {
        return mForumModelList.size();
    }

    public void setListData(List<ForumModel> forumModelList) {
        this.mForumModelList = forumModelList;
    }

private class Holder extends  ViewHolder{

    private final TextView mUserNameTextView;
    private final TextView mDescTextView;

    public Holder(View itemView) {
        super(itemView);
        mUserNameTextView = (TextView) itemView.findViewById(R.id.id_tv_forum_user_name);
        mDescTextView = (TextView) itemView.findViewById(R.id.id_tv_forum_desc);
        TextView timeTextView = (TextView) itemView.findViewById(R.id.id_tv_forum_time);
    }

    public void setDatas(int position) {
        ForumModel forumModel = mForumModelList.get(position);
        String fDesc = forumModel.fDesc;
        Integer fuid = forumModel.fuid;

        mUserNameTextView.setText(""+fuid);
        mDescTextView.setText(""+fDesc);
    }
}
}
