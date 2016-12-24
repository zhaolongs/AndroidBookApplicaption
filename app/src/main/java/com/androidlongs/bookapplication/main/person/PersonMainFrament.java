package com.androidlongs.bookapplication.main.person;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.BaseFrament;
import com.androidlongs.bookapplication.main.common.UserInfoInformationFunction;
import com.androidlongs.bookapplication.main.common.UserInfoModel;
import com.androidlongs.bookapplication.main.util.LogUtils;
import com.androidlongs.bookapplication.main.util.ToastUtils;

import static android.app.Activity.RESULT_OK;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class PersonMainFrament extends BaseFrament {

    private TextView mUserNameTextView;
    private TextView mDescTextView;
    private LinearLayout mLoginHeaderLinearLayout;
    private LinearLayout mNoLoginHeaderLinearLayout;
    private Button mOutLoginButton;
    private LinearLayout mMyClassListLinearLayout;
    private LinearLayout mMyHistoryLinearLayout;

    @Override
    public int getContentView() {
        return R.layout.frament_person_main;
    }

    @Override
    public void initView(View view) {

        ImageView titleImageView = (ImageView) view.findViewById(R.id.id_iv_person_user_title);
        mUserNameTextView = (TextView) view.findViewById(R.id.id_tv_person_user_name);
        mDescTextView = (TextView) view.findViewById(R.id.id_tv_person_user_desc);
        mMyClassListLinearLayout = (LinearLayout) view.findViewById(R.id.id_ll_person_my_book_list);
        mMyHistoryLinearLayout = (LinearLayout) view.findViewById(R.id.id_ll_person_my_read_history);

        mLoginHeaderLinearLayout = (LinearLayout) view.findViewById(R.id.id_ll_person_login_header);
        mNoLoginHeaderLinearLayout = (LinearLayout) view.findViewById(R.id.id_ll_person_no_login_header);

        mOutLoginButton = (Button) view.findViewById(R.id.id_bt_person_out);
    }

    @Override
    public void commonFunction() {

        selectPageShowFunction();
        setViewClickFunction();


    }

    private void setViewClickFunction() {
        mOutLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoInformationFunction.getInstance().clearUserInfo();
                ToastUtils.show("已退出登录");
                selectPageShowFunction();
            }
        });

        //我的书架
        mMyClassListLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonMainFrament.this.startActivity(new Intent(PersonMainFrament.this.getActivity(), MyBookSelfsActivity.class));
            }
        });
        //我的阅读记录
        mMyHistoryLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonMainFrament.this.startActivity(new Intent(PersonMainFrament.this.getActivity(), MyReadHistoryActivity.class));
            }
        });

        mNoLoginHeaderLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("打开登录页面");
                Intent intent = new Intent(PersonMainFrament.this.getActivity(), PersonLoginActivity.class);
                intent.putExtra("tag","person_login_tag");
                PersonMainFrament.this.startActivityForResult(intent, 1001);
            }
        });

    }

    private void selectPageShowFunction() {

        UserInfoModel userInfoModel = UserInfoInformationFunction.sUserInfoModel;
        if (userInfoModel == null) {
            mNoLoginHeaderLinearLayout.setVisibility(View.VISIBLE);
            mLoginHeaderLinearLayout.setVisibility(View.GONE);
            mOutLoginButton.setVisibility(View.GONE);
            mMyClassListLinearLayout.setVisibility(View.GONE);
            mMyHistoryLinearLayout.setVisibility(View.GONE);
        } else {
            mNoLoginHeaderLinearLayout.setVisibility(View.GONE);
            mLoginHeaderLinearLayout.setVisibility(View.VISIBLE);
            mMyClassListLinearLayout.setVisibility(View.VISIBLE);
            mMyHistoryLinearLayout.setVisibility(View.VISIBLE);
            mOutLoginButton.setVisibility(View.VISIBLE);

            String userName = userInfoModel.userName;
            String desc = userInfoModel.desc;

            mUserNameTextView.setText("" + userName);
            mDescTextView.setText("" + desc);
        }
    }

    //刷新页面
    //个人页面 未登录时，当登录完成后执行方法
    public void updatePages() {
        selectPageShowFunction();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            LogUtils.d("person result is function ");
            if (requestCode == 1001) {
                LogUtils.d("person login result data ");
                LogUtils.d("刷新个人页面数据");
                //刷新个人页面数据
                updatePages();
            }
        }

    }
}
