package com.androidlongs.bookapplication.main.home.frament;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.AppConfigFile;
import com.androidlongs.bookapplication.base.BaseFrament;
import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.home.activity.BookDetaleActivity;
import com.androidlongs.bookapplication.main.home.adapter.HomeBookListAdapter;
import com.androidlongs.bookapplication.main.home.inter.OnBookListItemClickLiserner;
import com.androidlongs.bookapplication.main.home.model.BookModel;
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

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class HomeBookListFrament extends BaseFrament {

    private RecyclerView mRecyclerView;
    private Call mGetBookListCall;

    @Override
    public int getContentView() {
        return R.layout.frament_home_list_main;
    }

    @Override
    public void initView(View view) {
        mBookModelList = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_rv_book_list_main);
    }

    @Override
    public void commonFunction() {

        setRecyclerListData();


        App.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppConfigFile.sIsTest) {
                    testDataLoadFuncation();
                }else {
                    //加载本地数据
                    loadDbCacheDatas();
                }
            }
        },700);

        mHomeBookListAdapter.setOnBookListItemClickLiserner(mItemClickLiserner);

    }

    private OnBookListItemClickLiserner mItemClickLiserner = new OnBookListItemClickLiserner() {
        @Override
        public void onNormalClick(View view, int postion) {
            LogUtils.d("item click  "+postion);
            Intent intent = new Intent(HomeBookListFrament.this.getActivity(), BookDetaleActivity.class);
            HomeBookListFrament.this.getActivity().startActivity(intent);
        }

        @Override
        public void onLongClick(View view, int postion) {

        }
    };

    private void testDataLoadFuncation() {
        for (int i = 0; i <30 ; i++) {
            BookModel bookModel = new BookModel();
            bookModel.bname = "人性的孤独";
            bookModel.bauthor="赵子龙";
            bookModel.bdesc = "孤独是一种唯美的体验和感觉，也是一种人性的高度，在茫茫人海里，我们时常找不到自我的位置，迷失着自我的方向，从寂寞的夜色中，从孤冷的翡翠的宫殿中，徘徊着来来去去的人生曲线，升华或沉淀，都在孤独的灵魂深处提升着自我的清纯";
            mBookModelList.add(bookModel);
        }
        setRecyclerListData();


    }

    private HomeBookListAdapter mHomeBookListAdapter;
    private List<BookModel> mBookModelList;

    private void setRecyclerListData() {
        if (mHomeBookListAdapter == null) {
            mHomeBookListAdapter = new HomeBookListAdapter(mBookModelList);
            mRecyclerView.setAdapter(mHomeBookListAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        } else {
            mHomeBookListAdapter.setListData(mBookModelList);
            mHomeBookListAdapter.notifyDataSetChanged();
        }
    }

    private void getBookListFromNet() {
        LogUtils.d("加载网络数据");
        String url = HttpHelper.sHomeBookListUrl;
        mGetBookListCall = OkhttpRequestUtils.getInstance().getRequest(url, mGetBookListCallback);
    }

    private Callback mGetBookListCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            LogUtils.e("net faile " + e.getMessage());
            long currentTime = System.currentTimeMillis();
            long flagTime = currentTime - mPreLoadTime;
            if (flagTime > 2000) {
                App.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        PopFunction.getInstance().close(false);
                    }
                });
            }else {
                long delyTime = 2000 - flagTime;
                App.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PopFunction.getInstance().close(false);
                    }
                },delyTime);
            }
        }

        @Override
        public void onResponse(Response response) throws IOException {


            String string = response.body().string();
            LogUtils.d(string);
            if (!TextUtils.isEmpty(string)) {
                try {
                    mBookModelList = GsonUtil.parseJsonArrayWithGson(string, BookModel.class);
                    //保存到数据库
//                    for (BookModel bookModel : mBookModelList) {
//                        mCommonBaseServiceInterface.addBookModel(bookModel);
//                    }
                    App.mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setRecyclerListData();
                        }
                    });

                } catch (final Exception e) {
                    App.mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            LogUtils.e(" 解析异常 " + e.getMessage());
                            ToastUtils.show("解析异常 home:book");
                        }
                    });

                }finally {
                    long currentTime = System.currentTimeMillis();
                    long flagTime = currentTime - mPreLoadTime;
                    if (flagTime > 2000) {
                        App.mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                PopFunction.getInstance().close(false);
                            }
                        });
                    }else {
                        long delyTime = 2000 - flagTime;
                        App.mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                PopFunction.getInstance().close(false);
                            }
                        },delyTime);
                    }
                }
            }
        }
    };


    private long mPreLoadTime = 0;
    //加载数据库中的数据
    private void loadDbCacheDatas() {
        LogUtils.d("加载本地数据");

        List<BaseModel> baseModels = mCommonBaseServiceInterface.queryAllBookModel();
        for (BaseModel baseModel : baseModels) {
            mBookModelList.add((BookModel) baseModel);
        }
        setRecyclerListData();

        //加载网络数据
        PopFunction.getInstance().fromBottomShow(App.mContext,mRecyclerView);
        PopFunction.getInstance().setCloseLiserner(mOnProgressCloseLiserner);
        mPreLoadTime = System.currentTimeMillis();
        getBookListFromNet();
    }

    private PopFunction.OnProgressCloseLiserner mOnProgressCloseLiserner=new PopFunction.OnProgressCloseLiserner() {
        @Override
        public void onClose(boolean flag) {
            if (flag) {
                LogUtils.d("加载完成");
                ToastUtils.show("加载完成");
            }else {
                LogUtils.d("取消加载");
                ToastUtils.show("取消加载");
            }

            App.mHandler.removeCallbacksAndMessages(0);

            if (mGetBookListCall != null) {
                if (!mGetBookListCall.isCanceled()) {
                    mGetBookListCall.cancel();
                }
            }

        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        App.mHandler.removeCallbacksAndMessages(0);

        if (mGetBookListCall != null) {
            if (!mGetBookListCall.isCanceled()) {
                mGetBookListCall.cancel();
            }
        }
    }
}
