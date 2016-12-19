package com.androidlongs.bookapplication.main.login.frament;

import android.view.View;
import android.widget.Button;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.BaseFrament;
import com.androidlongs.bookapplication.main.login.activity.WelcomeActivity;
import com.androidlongs.bookapplication.main.util.LogUtils;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class SelectLoginFrament extends BaseFrament {

    private Button mToLoginButton;
    private Button mToHomeButton;

    @Override
    public int getContentView() {
        return R.layout.activity_frament_select_login;
    }

    @Override
    public void initView(View view) {

        mToLoginButton = (Button) view.findViewById(R.id.id_frament_bt_to_login);
        mToHomeButton = (Button) view.findViewById(R.id.id_frament_bt_to_home);
    }

    @Override
    public void commonFunction() {

        setOnClickFunction();
    }

    private void setOnClickFunction() {
        mToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("跳转登录页面");
                ((WelcomeActivity)(SelectLoginFrament.this.getActivity())).selectLoginFrament();
            }
        });
        mToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("跳转Home页面");
                ((WelcomeActivity)(SelectLoginFrament.this.getActivity())).selectHomeActivity();
            }
        });
    }
}
