package com.androidlongs.bookapplication.main.person.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.BaseActivity;
import com.androidlongs.bookapplication.main.common.UserInfoInformationFunction;
import com.androidlongs.bookapplication.main.home.activity.HomeActivity;
import com.androidlongs.bookapplication.main.login.activity.RegisetrActivity;
import com.androidlongs.bookapplication.main.login.activity.SmssLoginActivity;
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
 * Created by androidlongs on 16/12/24.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class PersonLoginActivity extends BaseActivity {

    private LinearLayout mBackLinearLayout;
    private EditText mUserNameEditText;
    private EditText mPasswrodEditText;
    private LinearLayout mloginLinearLayout;
    private TextView mRegisterTextView;
    private LinearLayout mMSSMSLoginLinearLayout;

    @Override
    public int getContentView() {
        return R.layout.activity_person_login;
    }



    private LoginType mcurrentLoginType = LoginType.WELCOME;
    private enum LoginType{
        WELCOME,HOME,
    }
    @Override
    public void initView() {
        //返回
        mBackLinearLayout = (LinearLayout) findViewById(R.id.id_ll_person_login_back);

        mUserNameEditText = (EditText) findViewById(R.id.id_et_login_user_name);
        mPasswrodEditText = (EditText) findViewById(R.id.id_et_login_password);
        mloginLinearLayout = (LinearLayout) findViewById(R.id.id_ll_login);
        mRegisterTextView = (TextView) findViewById(R.id.id_tv_login_register);


        mMSSMSLoginLinearLayout = (LinearLayout) findViewById(R.id.id_ll_ssms_login);
    }


    @Override
    public void commonFunction() {
        initParamFunction();


        mMSSMSLoginLinearLayout.setOnClickListener(mSSMSOnClickListener);
        mRegisterTextView.setOnClickListener(mRegistOnClickListener);
        mBackLinearLayout.setOnClickListener(mBakcOnClickListener);
        mloginLinearLayout.setOnClickListener(mSubmitOnClickListener);
    }

    //参数 处理
    private void initParamFunction() {
        Intent intent = getIntent();
        String tag = intent.getStringExtra("tag");
        if (!TextUtils.isEmpty(tag)) {
            if (TextUtils.equals(tag, "person_login_tag")) {
                //个人登录页面
               mcurrentLoginType = LoginType.HOME;
            } else if (TextUtils.equals(tag, "welcome_tag")) {
                //个人登录页面
               mcurrentLoginType = LoginType.WELCOME;
            }
        }else {
            mcurrentLoginType = LoginType.WELCOME;
        }
    }

    private View.OnClickListener mBakcOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LogUtils.d("person login page is finish ");
            Intent intent = new Intent();
            PersonLoginActivity.this.setResult(RESULT_OK, intent);
            PersonLoginActivity.this.finish();
        }
    };


    //短信登录
    private View.OnClickListener mSSMSOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SSMSLoginFunction();
        }
    };
    //短信登录
    private void SSMSLoginFunction() {
        Intent intent = new Intent(PersonLoginActivity.this, SmssLoginActivity.class);

        switch (mcurrentLoginType){
            case WELCOME:
                intent.putExtra("tag", "welcome");
                PersonLoginActivity.this.startActivity(intent);
                break;
            case HOME:
                intent.putExtra("tag", "home");
                PersonLoginActivity.this.startActivity(intent);
                PersonLoginActivity.this.finish();
                break;
            default:
                break;
        }


    }

    private Call mLoginRequestCall;
    private View.OnClickListener mSubmitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = HttpHelper.sLoginUrl;

            String userName = mUserNameEditText.getText().toString().trim();
            String password = mPasswrodEditText.getText().toString().trim();

            if (!TextUtils.isEmpty(userName)) {
                if (!TextUtils.isEmpty(password)) {
                    Map<String, String> keyMap = new HashMap<>();
                    keyMap.put("userName", userName);
                    keyMap.put("password", password);
                    mLoginRequestCall = OkhttpRequestUtils.getInstance().postRequest(url, keyMap, mLoginCallback);
                } else {
                    ToastUtils.show("密码不可为空");
                }
            } else {
                ToastUtils.show("用户名不可为空");
            }
        }
    };

    //注册页面进入
    private View.OnClickListener mRegistOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PersonLoginActivity.this, RegisetrActivity.class);


            switch (mcurrentLoginType){
                case WELCOME:
                    intent.putExtra("tag", "welcome");
                    PersonLoginActivity.this.startActivity(intent);
                    break;
                case HOME:
                    intent.putExtra("tag", "home");
                    PersonLoginActivity.this.startActivity(intent);
                    PersonLoginActivity.this.finish();
                    break;
                default:
                    break;
            }

        }
    };
    private Callback mLoginCallback = new Callback() {
        @Override
        public void onFailure(Request request, final IOException e) {
            LogUtils.e("登录失败 " + e.getMessage());
            App.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.show("登录失败 " + e.getMessage());

                }
            });
        }

        @Override
        public void onResponse(Response response) throws IOException {

            try {


                final String result = response.body().string();
                LogUtils.d("登录 请求 成功 " + result);
                final LoginResponseModel loginResponseModel = GsonUtil.parseJsonWithGson(result, LoginResponseModel.class);
                LogUtils.d("解析数据 " + loginResponseModel.toString());

                App.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.isEmpty(loginResponseModel.code)) {
                            LogUtils.d("登录  信息异常 " + result);
                            ToastUtils.show("登录  信息异常");
                        } else if (TextUtils.equals(loginResponseModel.code, "1000")) {


                            if (loginResponseModel.content == null) {
                                LogUtils.d("登录  登录失败 " + result);
                                ToastUtils.show("登录  登录失败 " + result);
                            } else {


                                switch (mcurrentLoginType){
                                    case WELCOME:
                                        LogUtils.d("登录  登录成功 " + result);
                                        App.sUserInfoModel = loginResponseModel.content;
                                        UserInfoInformationFunction.getInstance().saveUserInfoModel(loginResponseModel.content);
                                        Intent intent = new Intent(PersonLoginActivity.this, HomeActivity.class);
                                        PersonLoginActivity.this.startActivity(intent);
                                        PersonLoginActivity.this.finish();
                                        break;
                                    case HOME:
                                        LogUtils.d("登录  登录成功 " + result);
                                        App.sUserInfoModel = loginResponseModel.content;
                                        UserInfoInformationFunction.getInstance().saveUserInfoModel(loginResponseModel.content);
                                        PersonLoginActivity.this.setResult(RESULT_OK, new Intent());
                                        PersonLoginActivity.this.finish();
                                        break;
                                    default:
                                        break;
                                }


                            }


                        } else {
                            LogUtils.e("登录失败");
                            ToastUtils.show("登录  信息异常" + loginResponseModel.message);
                        }
                    }
                });

            } catch (Exception e) {
                LogUtils.e(" 解析异常 " + e.getMessage());
                App.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.show("登录异常");
                    }
                });
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoginRequestCall != null) {
            if (!mLoginRequestCall.isCanceled()) {
                mLoginRequestCall.cancel();
            }
        }

    }
}
