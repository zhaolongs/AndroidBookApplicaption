package com.androidlongs.bookapplication.main.home.frament;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.BaseFrament;
import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.home.adapter.HomeBookClassAdapter;
import com.androidlongs.bookapplication.main.home.model.BookClassModel;
import com.androidlongs.bookapplication.main.net.HttpHelper;
import com.androidlongs.bookapplication.main.net.OkhttpRequestUtils;
import com.androidlongs.bookapplication.main.util.GsonUtil;
import com.androidlongs.bookapplication.main.util.LogUtils;
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

public class HomeBookClassFrament extends BaseFrament {

    private RecyclerView mRecyclerView;
    private HomeBookClassAdapter mBookClassAdapter;

    @Override
    public int getContentView() {
        return R.layout.frament_home_class_main;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_rv_book_class_main);
    }

    @Override
    public void commonFunction() {

        setRecyListData();
        //加载本地数据
        loadLocationData();
    }

    private void loadLocationData() {
        List<BaseModel> baseModelList = mCommonBaseServiceInterface.queryAllBookClassModel();


        if (baseModelList != null) {
            for (BaseModel baseModel : baseModelList) {
                mBookClassModels.add((BookClassModel) baseModel);
            }
            setRecyListData();
        }



        //加载 网络数据
        loadNetDatas();
    }

    private void loadNetDatas() {
        String url = HttpHelper.sBaseUrl +"?tag=getBookClassList";
        OkhttpRequestUtils.getInstance().getRequest(url,mBookClassCallback);
    }

    private Callback mBookClassCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            LogUtils.e("加载异常  "+e.getMessage());
        }

        @Override
        public void onResponse(Response response) throws IOException {

            String string = response.body().string();
            LogUtils.d("加载完成 "+string);
            mBookClassModels = GsonUtil.parseJsonArrayWithGson(string, BookClassModel.class);
            LogUtils.d("解析完成 "+mBookClassModels);
            //更新本地数据
            for (BookClassModel bookClassModel : mBookClassModels) {
                mCommonBaseServiceInterface.addBookClassModel(bookClassModel);
            }
            //更新列表
            App.mHandler.post(new Runnable() {
                @Override
                public void run() {
               setRecyListData();
                }
            });
        }
    };
    private List<BookClassModel> mBookClassModels = new ArrayList<>();
    private void setRecyListData() {
        if (mBookClassAdapter==null) {
            mBookClassAdapter = new HomeBookClassAdapter(mBookClassModels);
            mRecyclerView.setAdapter(mBookClassAdapter);
            //设置布局模式
            GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(),2);
            //设置滚动模式为竖直方向
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);

        }else {
            mBookClassAdapter.setDatas(mBookClassModels);
            mBookClassAdapter.notifyDataSetChanged();
        }

    }
}
