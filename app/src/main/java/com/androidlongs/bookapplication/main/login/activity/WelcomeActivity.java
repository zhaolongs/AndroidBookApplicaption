package com.androidlongs.bookapplication.main.login.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.text.TextUtils;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.BaseActivity;
import com.androidlongs.bookapplication.main.common.UserInfoInformationFunction;
import com.androidlongs.bookapplication.main.common.UserInfoModel;
import com.androidlongs.bookapplication.main.home.activity.HomeActivity;
import com.androidlongs.bookapplication.main.login.frament.SelectLoginFrament;
import com.androidlongs.bookapplication.main.login.frament.WelcomeFrament;
import com.androidlongs.bookapplication.main.person.activity.PersonLoginActivity;
import com.androidlongs.bookapplication.main.util.LogUtils;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class WelcomeActivity extends BaseActivity {


    private FragmentManager mFragmentManager;

    @Override
    public int getContentView() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {


    }
    private String mWelcomeTag = "welcomeTag";
    private String mSelectLoginTag = "selectloginTag";
    private String mLoginTag = "loginTag";

    @Override
    public void commonFunction() {



        mFragmentManager = getFragmentManager();

        selectPageFunction(mWelcomeTag);

        App.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                selectPageFunction();
            }
        }, 3000);


    }


    private  void selectPageFunction(String tag){
        //从管理栈中取出对应标签的Frament

        //欢迎页面
        Fragment welcomeFrament = mFragmentManager.findFragmentByTag(mWelcomeTag);
        //选择登录页面
        Fragment selectFrament = mFragmentManager.findFragmentByTag(mSelectLoginTag);
        //登录页面
        Fragment loginFrament = mFragmentManager.findFragmentByTag(mLoginTag);

        //获取
        FragmentTransaction beginTransaction = mFragmentManager.beginTransaction();

        //设置fragment切换动画
        beginTransaction.setCustomAnimations(
                R.animator.from_right,
                R.animator.to_left,
                R.animator.back_from_in,
                R.animator.back_from_out);
        //隐藏所有的fragment

        if (welcomeFrament != null) {
            beginTransaction.hide(welcomeFrament);
        }

        if (selectFrament != null) {
            beginTransaction.hide(selectFrament);
        }

        if (loginFrament != null) {
            beginTransaction.hide(loginFrament);
        }

        //显示或者创建当前要显示的页面
        if (TextUtils.equals(tag,mWelcomeTag)){
            //欢迎页面
            if (welcomeFrament == null) {
                welcomeFrament = new WelcomeFrament();
                beginTransaction.add(R.id.id_fl_activity_main_content, welcomeFrament, mWelcomeTag);
            }else {
                beginTransaction.show(loginFrament);
            }

        }else if(TextUtils.equals(tag,mSelectLoginTag)){
            //选择登录页面
            if (selectFrament==null){
                selectFrament = new SelectLoginFrament();
                beginTransaction.add(R.id.id_fl_activity_main_content, selectFrament, mSelectLoginTag).addToBackStack(mSelectLoginTag);
            }else {
                beginTransaction.show(selectFrament);
            }
        }else {
            //登录页面
            Intent intent  = new Intent(WelcomeActivity.this, PersonLoginActivity.class);
            intent.putExtra("tag","welcome_tag");
            WelcomeActivity.this.startActivity(intent);
            WelcomeActivity.this.finish();
            return;
        }

        //提交事务
        beginTransaction.commit();
    }


    private void selectPageFunction() {

        UserInfoModel userInfoModel = UserInfoInformationFunction.getInstance().getUserInfoModel(true);
        Class clazz;
        if (userInfoModel == null) {
            //用户信息为null 跳转选择进入选择登录 页面
            selectPageFunction(mSelectLoginTag);
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
        selectPageFunction(mLoginTag);
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
