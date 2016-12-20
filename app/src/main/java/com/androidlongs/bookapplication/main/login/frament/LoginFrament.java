package com.androidlongs.bookapplication.main.login.frament;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.BaseFrament;
import com.androidlongs.bookapplication.main.home.HomeActivity;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class LoginFrament extends BaseFrament {

    private LinearLayout mBackLinearLayout;
    private Button mLoginButton;
    private CheckBox mCheckBox;

    @Override
    public int getContentView() {
        return R.layout.activity_frament_login;
    }

    @Override
    public void initView(View view) {

        mBackLinearLayout = (LinearLayout) view.findViewById(R.id.id_login_frament_title_back);
        mLoginButton = (Button) view.findViewById(R.id.id_bt_frgament_login);

        mCheckBox = (CheckBox) view.findViewById(R.id.cb_save_username);

    }

    @Override
    public void commonFunction() {

        setViewClickFunction();
    }

    private void setViewClickFunction() {
        //返回
        mBackLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginFrament.this.getActivity() != null) {
                    if (LoginFrament.this.getActivity().getFragmentManager().getBackStackEntryCount() > 0) {
                        LoginFrament.this.getActivity().getFragmentManager().popBackStack();
                    }
                }
            }
        });
        //登录
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFrament.this.getActivity().startActivity(new Intent(LoginFrament.this.getActivity(), HomeActivity.class));
            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }
}
