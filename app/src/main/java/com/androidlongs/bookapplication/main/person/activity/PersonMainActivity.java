package com.androidlongs.bookapplication.main.person.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.BaseActivity;
import com.androidlongs.bookapplication.main.common.UserInfoInformationFunction;
import com.androidlongs.bookapplication.main.common.UserInfoModel;
import com.androidlongs.bookapplication.main.util.LogUtils;
import com.androidlongs.bookapplication.main.util.ToastUtils;

/**
 * Created by androidlongs on 16/12/28.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class PersonMainActivity extends BaseActivity {

    private LinearLayout mLoginLinearLayout;
    private TextView mLoginTextView;
    private LinearLayout mMyBookListLinearLayout;
    private LinearLayout mMyReadHistoryLinearLayout;
    private TextView mUserNameTextView;
    private TextView mDescTextView;
    private TextView mReadHistoryTextView;
    private TextView mBookListTextView;

    @Override
    public int getContentView() {
        return R.layout.activity_person_main;
    }

    @Override
    public void initView() {

        mLoginLinearLayout = (LinearLayout) findViewById(R.id.id_ll_activity_person_login);
        mLoginTextView = (TextView) findViewById(R.id.id_tv_activity_person_login);

        mMyBookListLinearLayout = (LinearLayout) findViewById(R.id.id_ll_activity_person_my_book_list);
        mMyReadHistoryLinearLayout = (LinearLayout) findViewById(R.id.id_ll_activity_person_my_read_history);


        mUserNameTextView = (TextView) findViewById(R.id.id_ll_activity_person_my_name);
        mDescTextView = (TextView) findViewById(R.id.id_ll_activity_person_my_desc);
        mReadHistoryTextView = (TextView)findViewById(R.id.id_tv_activity_person_my_read_history);
        mBookListTextView = (TextView)findViewById(R.id.id_tv_activity_person_my_book_list);
    }

    @Override
    public void commonFunction() {
        mLoginLinearLayout.setOnClickListener(mOnClickListener);
        mMyBookListLinearLayout.setOnClickListener(mBookListOnClickListener);
        mMyReadHistoryLinearLayout.setOnClickListener(mReadHistoryOnClickListener);


        selectPageShowFunction();
    }

    private void selectPageShowFunction() {
        UserInfoModel userInfoModel = UserInfoInformationFunction.sUserInfoModel;

        if (userInfoModel == null) {
            mCurrentStatue = USER_LOGIN_STATUE.NOLOGIN;
            mLoginTextView.setText("登录");

            mUserNameTextView.setText("");
            mUserNameTextView.setHint("请登录");

            mDescTextView.setText("");
            mDescTextView.setHint("个性签名");

            mReadHistoryTextView.setText("");
            mReadHistoryTextView.setHint("我的阅读记录");


            mBookListTextView.setText("");
            mBookListTextView.setHint("我的阅读记录");

        } else {

            String userName = userInfoModel.uname;
            String desc = userInfoModel.udesc;

            mCurrentStatue = USER_LOGIN_STATUE.LOOGIN;
            mLoginTextView.setText("退出登录");
            mUserNameTextView.setText(""+userName);
            mDescTextView.setText(""+desc);

            mReadHistoryTextView.setText("我的阅读记录");
            mBookListTextView.setText("我的书架");
        }

    }

    private USER_LOGIN_STATUE mCurrentStatue = USER_LOGIN_STATUE.NOLOGIN;

    private enum USER_LOGIN_STATUE {
        LOOGIN, NOLOGIN
    }

    private void loginFunction() {
        LogUtils.d("打开登录页面");
        Intent intent = new Intent(PersonMainActivity.this, PersonLoginActivity.class);
        intent.putExtra("tag", "person_login_tag");
        PersonMainActivity.this.startActivityForResult(intent, 1001);
    }

    private void outLoginFunction() {
        UserInfoInformationFunction.getInstance().clearUserInfo();
        ToastUtils.show("已退出登录");
        selectPageShowFunction();
    }

    private void queryMyBookListFunction() {

    }

    private void queryMyReadHistoryFunction() {
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mCurrentStatue) {
                case NOLOGIN:
                    loginFunction();
                    break;
                case LOOGIN:
                    outLoginFunction();
                    break;
            }
        }
    };
    private View.OnClickListener mBookListOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mCurrentStatue) {
                case NOLOGIN:
                    ToastUtils.show("请登录");
                    break;
                case LOOGIN:
                    queryMyBookListFunction();
                    break;
            }
        }
    };


    private View.OnClickListener mReadHistoryOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mCurrentStatue) {
                case NOLOGIN:
                    ToastUtils.show("请登录");
                    break;
                case LOOGIN:
                    queryMyReadHistoryFunction();
                    break;
            }
        }


    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            LogUtils.d("person result is function ");
            if (requestCode == 1001) {
                LogUtils.d("person login result data ");
                LogUtils.d("刷新个人页面数据");
                //刷新个人页面数据
                selectPageShowFunction();
            }
        }

    }
}
