package com.androidlongs.bookapplication.main.home.frament;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.BaseFrament;
import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.home.adapter.HomeBookListAdapter;
import com.androidlongs.bookapplication.main.home.model.BookModel;
import com.androidlongs.bookapplication.main.net.HttpHelper;
import com.androidlongs.bookapplication.main.net.OkhttpRequestUtils;
import com.androidlongs.bookapplication.main.util.GsonUtil;
import com.androidlongs.bookapplication.main.util.LogUtils;
import com.androidlongs.bookapplication.main.util.ToastUtils;
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
        //加载本地数据
        loadDbCacheDatas();

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
        String url = HttpHelper.sBaseUrl + "?tag=getBookList";
        OkhttpRequestUtils.getInstance().getRequest(url, mGetBookListCallback);
    }

    private Callback mGetBookListCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            LogUtils.e("net faile " + e.getMessage());
        }

        @Override
        public void onResponse(Response response) throws IOException {


            String string = response.body().string();
            LogUtils.d(string);
            if (!TextUtils.isEmpty(string)) {
                try {
                    mBookModelList = GsonUtil.parseJsonArrayWithGson(string, BookModel.class);
                    //保存到数据库
                    for (BookModel bookModel : mBookModelList) {
                        mCommonBaseServiceInterface.addBookModel(bookModel);
                    }
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

                }
            }
        }
    };


    //加载数据库中的数据
    private void loadDbCacheDatas() {
        LogUtils.d("加载本地数据");

        List<BaseModel> baseModels = mCommonBaseServiceInterface.queryAllBookModel();
        for (BaseModel baseModel : baseModels) {
            mBookModelList.add((BookModel) baseModel);
        }
        setRecyclerListData();

        getBookListFromNet();
    }

}
