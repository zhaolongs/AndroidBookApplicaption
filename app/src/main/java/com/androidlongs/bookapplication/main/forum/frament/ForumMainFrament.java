package com.androidlongs.bookapplication.main.forum.frament;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.AppConfigFile;
import com.androidlongs.bookapplication.base.BaseFrament;
import com.androidlongs.bookapplication.main.forum.adapter.ForumMainAdapter;
import com.androidlongs.bookapplication.main.forum.model.ForumModel;
import com.androidlongs.bookapplication.main.forum.model.FroumResponseModel;
import com.androidlongs.bookapplication.main.net.HttpHelper;
import com.androidlongs.bookapplication.main.net.OkhttpRequestUtils;
import com.androidlongs.bookapplication.main.util.GsonUtil;
import com.androidlongs.bookapplication.main.util.LogUtils;
import com.androidlongs.bookapplication.main.util.PopFunction;
import com.androidlongs.bookapplication.main.util.ToastUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class ForumMainFrament extends BaseFrament {

    private RecyclerView mRecyclerView;
    private Call mCall;

    @Override
    public int getContentView() {
        return R.layout.frament_forum_main;
    }

    @Override
    public void initView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_rv_forum_all_list);
    }

    @Override
    public void commonFunction() {

        if (AppConfigFile.sIsTest) {
            testFunction();
        } else {
            //加载网络数据
            PopFunction.getInstance().fromBottomShow(App.mContext, mRecyclerView);
            PopFunction.getInstance().setCloseLiserner(mOnProgressCloseLiserner);
            mPreLoadTime = System.currentTimeMillis();
            requestDataFroumNet();
        }

    }

    private long mPreLoadTime = 0;

    private void testFunction() {
        for (int i = 0; i < 20; i++) {
            ForumModel model = new ForumModel();
            model.fuuid = UUID.randomUUID().toString();
            model.fDesc = "this is a test ";
            mForumModelList.add(model);
        }

        //加载网络数据
        PopFunction.getInstance().fromBottomShow(App.mContext, mRecyclerView);
        PopFunction.getInstance().setCloseLiserner(mOnProgressCloseLiserner);
        mPreLoadTime = System.currentTimeMillis();
        setRecyclerListData();
    }

    private void requestDataFroumNet() {

        String url = HttpHelper.sForumListUrl;

        mCall = OkhttpRequestUtils.getInstance().getRequest(url, mRequestForumListCallback);

    }

    private Callback mRequestForumListCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            loadFaile(e);
        }

        @Override
        public void onResponse(Response response) throws IOException {
            try {

                //请求响应结果
                String string = response.body().string();
                //日志
                LogUtils.d("" + string);
                //加载失败
                if (TextUtils.isEmpty(string)) {
                    loadFaile(null);
                    return;
                } else {
                    //加载响应成功 并解析
                    FroumResponseModel loginResponseModel = GsonUtil.parseJsonWithGson(string, FroumResponseModel.class);
                    //解析数据成功
                    if (loginResponseModel != null) {
                        loadSuccess(loginResponseModel);
                    } else {
                        //解析失败
                        loadFaile(null);
                    }
                }

            } catch (Exception e) {
                //异常
                loadFaile(e);
            } finally {
                long currentTime = System.currentTimeMillis();
                long flagTime = currentTime - mPreLoadTime;
                if (flagTime > 2000) {
                    App.mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            PopFunction.getInstance().close(false);
                        }
                    });
                } else {
                    long delyTime = 2000 - flagTime;
                    App.mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PopFunction.getInstance().close(false);
                        }
                    }, delyTime);
                }
            }
        }
    };

    private void loadFaile(Exception e) {
        String message = "";
        if (e != null) {
            message = e.getMessage();
        }
        final String finalMessage = message;
        App.mHandler.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e(" 加载数据异常 " + finalMessage);
                ToastUtils.show("加载论坛数据异常");
            }
        });
        long currentTime = System.currentTimeMillis();
        long flagTime = currentTime - mPreLoadTime;
        if (flagTime > 2000) {
            App.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    PopFunction.getInstance().close(false);
                }
            });
        } else {
            long delyTime = 2000 - flagTime;
            App.mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    PopFunction.getInstance().close(false);
                }
            }, delyTime);
        }
    }

    private List<ForumModel> mForumModelList = new ArrayList<>();

    private void loadSuccess(final FroumResponseModel loginResponseModel) {

        App.mHandler.post(new Runnable() {
            @Override
            public void run() {

                String code = loginResponseModel.code;
                if (TextUtils.isEmpty(code)) {
                    ToastUtils.show(" 加载 异常 ");
                } else if (!TextUtils.equals(code, "1000")) {
                    ToastUtils.show("" + loginResponseModel.message);
                } else {
                    //加载成功
                    mForumModelList.clear();
                    mForumModelList = loginResponseModel.contentList;

                    //刷新列表
                    setRecyclerListData();
                }
            }
        });
    }

    private ForumMainAdapter mForumMainAdapter;

    private void setRecyclerListData() {
        if (mForumMainAdapter == null) {
            mForumMainAdapter = new ForumMainAdapter(mForumModelList);
            mRecyclerView.setAdapter(mForumMainAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(ForumMainFrament.this.getActivity()));
        } else {
            mForumMainAdapter.setListData(mForumModelList);
            mForumMainAdapter.notifyDataSetChanged();
        }
    }

    private PopFunction.OnProgressCloseLiserner mOnProgressCloseLiserner = new PopFunction.OnProgressCloseLiserner() {
        @Override
        public void onClose(boolean flag) {
            if (flag) {
                LogUtils.d("加载完成");
                ToastUtils.show("加载完成");
            } else {
                LogUtils.d("取消加载");
                ToastUtils.show("取消加载");
            }

            App.mHandler.removeCallbacksAndMessages(0);

            if (mCall != null) {
                if (!mCall.isCanceled()) {
                    mCall.cancel();
                }
            }

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCall != null) {
            if (!mCall.isCanceled()) {
                mCall.cancel();
                mCall = null;
            }
        }
        App.mHandler.removeCallbacksAndMessages(0);

    }
}
