package com.androidlongs.bookapplication.main.home.frament;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.AppConfigFile;
import com.androidlongs.bookapplication.base.BaseFrament;
import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.home.adapter.HomeBookClassAdapter;
import com.androidlongs.bookapplication.main.home.inter.OnBookListItemClickLiserner;
import com.androidlongs.bookapplication.main.home.model.BookClassModel;
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
        if (AppConfigFile.sIsTest) {
            loadTestDataFunction();

        }else {
            //加载本地数据
            loadLocationData();
        }

        mBookClassAdapter.setOnBookListItemClickLiserner(mBookClassClickLiserner);
    }

    private OnBookListItemClickLiserner mBookClassClickLiserner = new OnBookListItemClickLiserner() {
        @Override
        public void onNormalClick(View view, int postion) {
            LogUtils.d("class item click "+postion);
        }

        @Override
        public void onLongClick(View view, int postion) {

        }
    };

    private void loadTestDataFunction() {
        for (int i = 0; i < 10; i++) {
            BookClassModel model = new BookClassModel();
            model.name = "小说";
            model.description="\t走进纷扰的红尘，我们一不留神就走进苍茫的大地里，那里有黄昏的寂寞，也有日暮苍山远的清凉，在冷落的空气中，更多的时候是与自然相伴，与天地相互融合，走是一种心灵的追求，不管是遭遇生离死别，还是人生的阴晴圆缺，我们都不得停留，在行走在人性的高度，孤独掩饰的是一种大爱无痕，是一种真情的留恋，也是一种天涯咫尺的思念";

            mBookClassModels.add(model);
        }
        setRecyListData();
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
        String url = HttpHelper.sBaseUrl + "?tag=getBookClassList";
        OkhttpRequestUtils.getInstance().getRequest(url, mBookClassCallback);
    }

    private Callback mBookClassCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            LogUtils.e("加载异常  " + e.getMessage());
        }

        @Override
        public void onResponse(Response response) throws IOException {

            try {


                String string = response.body().string();
                LogUtils.d("加载完成 " + string);
                mBookClassModels = GsonUtil.parseJsonArrayWithGson(string, BookClassModel.class);
                LogUtils.d("解析完成 " + mBookClassModels);
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

            } catch (Exception e) {
                LogUtils.e("解析 异常 " + e.getMessage());
                App.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.show("解析 异常 home book class ");
                    }
                });
            }
        }
    };
    private List<BookClassModel> mBookClassModels = new ArrayList<>();

    private void setRecyListData() {
        if (mBookClassAdapter == null) {
            mBookClassAdapter = new HomeBookClassAdapter(mBookClassModels);
            mRecyclerView.setAdapter(mBookClassAdapter);
            //设置布局模式
            GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(), 2);
            //设置滚动模式为竖直方向
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);

        } else {
            mBookClassAdapter.setDatas(mBookClassModels);
            mBookClassAdapter.notifyDataSetChanged();
        }

    }
}
