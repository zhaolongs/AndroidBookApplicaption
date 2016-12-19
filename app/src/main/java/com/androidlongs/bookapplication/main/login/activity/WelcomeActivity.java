package com.androidlongs.bookapplication.main.login.activity;

import android.content.Intent;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.BaseActivity;
import com.androidlongs.bookapplication.main.common.UserInfoModel;
import com.androidlongs.bookapplication.main.home.HomeActivity;
import com.androidlongs.bookapplication.main.common.UserInfoInformationFunction;
import com.androidlongs.bookapplication.main.login.frament.LoginFrament;
import com.androidlongs.bookapplication.main.login.frament.SelectLoginFrament;
import com.androidlongs.bookapplication.main.login.frament.WelcomeFrament;
import com.androidlongs.bookapplication.main.util.LogUtils;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class WelcomeActivity extends BaseActivity {


    private WelcomeFrament mWelcomeFrament;
    private SelectLoginFrament mSelectLoginFrament;
    private LoginFrament mLoginFrament;

    @Override
    public int getContentView() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {


    }

    @Override
    public void commonFunction() {


        mWelcomeFrament = new WelcomeFrament();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.welcome_frament_in, R.animator.welcome_frament_out, R.animator.welcome_frament_in, R.animator.welcome_frament_out)
                .addToBackStack("OtherFragment")
                .replace(R.id.id_fl_activity_main_content, mWelcomeFrament)
                .commit();


        App.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initFramentFunction();
                selectPageFunction();
            }
        }, 3000);


    }

    private void initFramentFunction() {
        //登录选择页面
        mSelectLoginFrament = new SelectLoginFrament();
        //登录页面
        mLoginFrament = new LoginFrament();
    }

    private void selectPageFunction() {

        UserInfoModel userInfoModel = UserInfoInformationFunction.getInstance().getUserInfoModel(true);
        Class clazz;
        if (userInfoModel == null) {
            //用户信息为null 跳转选择进入选择登录 页面
            LogUtils.d(" 跳转选择进入选择登录 页面");
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.animator.welcome_frament_in, R.animator.welcome_frament_out, R.animator.welcome_frament_in, R.animator.welcome_frament_out)
                    .addToBackStack("mSelectLoginFrament")
                    .replace(R.id.id_fl_activity_main_content, mSelectLoginFrament)
                    .commit();
        } else {
            //用户信息不为null 跳转主页面
            clazz = HomeActivity.class;
            LogUtils.d(" 跳转主页面");
            startActivity(new Intent(this, clazz));
            finish();
        }
    }

    /**
     * 跳转 登录页面
     */
    public void selectLoginFrament(){
        //用户信息为null 跳转选择进入选择登录 页面
        LogUtils.d(" 跳转选择进入登录 页面");
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.welcome_frament_in,
                        R.animator.welcome_frament_out,
                        R.animator.welcome_frament_in,
                        R.animator.welcome_frament_out)
                .addToBackStack("mLoginFrament")
                .replace(R.id.id_fl_activity_main_content, mLoginFrament)
                .commit();
    }

    /**
     * 跳转 主页面
     */
    public void selectHomeActivity(){
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }

    /**
     * 设置Fragment后退栈
     */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
