package com.androidlongs.bookapplication.main.login.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.BaseActivity;
import com.androidlongs.bookapplication.main.common.UserInfoInformationFunction;
import com.androidlongs.bookapplication.main.common.UserInfoModel;
import com.androidlongs.bookapplication.main.home.HomeActivity;
import com.androidlongs.bookapplication.main.login.model.LoginResponseModel;
import com.androidlongs.bookapplication.main.net.HttpHelper;
import com.androidlongs.bookapplication.main.net.OkhttpRequestUtils;
import com.androidlongs.bookapplication.main.util.GsonUtil;
import com.androidlongs.bookapplication.main.util.LogUtils;
import com.androidlongs.bookapplication.main.util.ToastUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by androidlongs on 16/12/28.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class RegisetrActivity extends BaseActivity {

    private ImageView mBackImageView;
    private ImageView mTitleImageView;
    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private EditText mPassword2EditText;
    private LinearLayout mRegisterLinearLayout;
    private Call mCall;

    @Override
    public int getContentView() {
        return R.layout.activity_regist;
    }

    @Override
    public void initView() {

        mBackImageView = (ImageView) findViewById(R.id.id_iv_register_back);
        mTitleImageView = (ImageView) findViewById(R.id.id_iv_register_title);
        mUserNameEditText = (EditText) findViewById(R.id.id_et_register_user_name);
        mPasswordEditText = (EditText) findViewById(R.id.id_et_register_user_password);
        mPassword2EditText = (EditText) findViewById(R.id.id_et_register_user_password_2);
        mRegisterLinearLayout = (LinearLayout) findViewById(R.id.id_ll_regist);
    }

    @Override
    public void commonFunction() {

        mBackImageView.setOnClickListener(mBackOnClickListener);
        mTitleImageView.setOnClickListener(mSelectTitleImageOnClickListener);
        mRegisterLinearLayout.setOnClickListener(mRegisterOnClickListener);
    }


    private View.OnClickListener mBackOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RegisetrActivity.this.finish();
        }
    };
    private View.OnClickListener mSelectTitleImageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LogUtils.d("选择设置头像");
        }
    };

    private View.OnClickListener mRegisterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LogUtils.d("注册请求");
            regiseterFunction();
        }
    };

    //注册功能
    private void regiseterFunction() {


        String userName = mUserNameEditText.getText().toString().trim();
        String password1 = mPasswordEditText.getText().toString().trim();
        String password2 = mPassword2EditText.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            ToastUtils.show("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            ToastUtils.show("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(password2)) {
            ToastUtils.show("请再次输入密码");
            return;
        }
        if (TextUtils.equals(password1, password2)) {
            ToastUtils.show("两次输入的密码不一致");
            return;
        }

        //封装参数
        Map<String, String> keyParamMap = new HashMap<>();
        keyParamMap.put("userNmae", userName);
        keyParamMap.put("password", password1);


        String url = HttpHelper.sRegisterUrl;

        mCall = OkhttpRequestUtils.getInstance().postRequest(url, keyParamMap, mRegisterCallback);


    }


    private Callback mRegisterCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            LogUtils.e("注册异常 " + e.getMessage());
            registerFaileFunctino(null);
        }

        @Override
        public void onResponse(Response response) throws IOException {

            try{
                String string = response.body().string();
                if (TextUtils.isEmpty(string)) {
                    LogUtils.e("注册异常 ");
                    registerFaileFunctino(null);
                }else {
                    LoginResponseModel loginResponseModel = GsonUtil.parseJsonWithGson(string, LoginResponseModel.class);
                    if (loginResponseModel == null) {
                        registerFaileFunctino(null);
                    }else {
                        UserInfoModel content = loginResponseModel.content;
                        if (content == null) {
                            registerFaileFunctino(null);
                        }else {
                            //保存
                            UserInfoInformationFunction.getInstance().saveUserInfoModel(content);
                            App.mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    selectRegisterSuessFunction();
                                }
                            });
                        }
                    }
                }

            }catch (Exception e){
                LogUtils.e("注册异常 " + e.getMessage());
                registerFaileFunctino(null);
            }
        }
    };

    private void registerFaileFunctino(Exception e){
        String ex = "";
        if (e != null) {
            ex = e.getMessage();
        }

        final String finalEx = ex;
        App.mHandler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show("注册异常 "+ finalEx);
            }
        });


    }

    private REGISETR_TYPE mCurrentRegisType = REGISETR_TYPE.WELCOME_PAGE;
    private enum  REGISETR_TYPE{
        WELCOME_PAGE,HOME_PAGE
    }
    private void selectRegisterSuessFunction() {

        if (mCurrentRegisType == REGISETR_TYPE.WELCOME_PAGE){
            RegisetrActivity.this.startActivity(new Intent(RegisetrActivity.this, HomeActivity.class));
            RegisetrActivity.this.finish();
        }else if (mCurrentRegisType == REGISETR_TYPE.HOME_PAGE){
            RegisetrActivity.this.finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCall != null) {
            if (!mCall.isCanceled()) {
                mCall.cancel();
            }
        }
    }
}
