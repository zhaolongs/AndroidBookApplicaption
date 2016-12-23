package com.androidlongs.bookapplication.main.login.frament;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.BaseFrament;
import com.androidlongs.bookapplication.main.common.UserInfoInformationFunction;
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
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class LoginFrament extends BaseFrament {

    private LinearLayout mBackLinearLayout;
    private Button mLoginButton;
    private CheckBox mCheckBox;
    private EditText mUserNameEditText;
    private EditText mPasswordEditText;

    @Override
    public int getContentView() {
        return R.layout.activity_frament_login;
    }

    @Override
    public void initView(View view) {

        mBackLinearLayout = (LinearLayout) view.findViewById(R.id.id_login_frament_title_back);
        mLoginButton = (Button) view.findViewById(R.id.id_bt_frgament_login);

        mCheckBox = (CheckBox) view.findViewById(R.id.cb_save_username);

        mUserNameEditText = (EditText) view.findViewById(R.id.id_et_login_frament_username);
        mPasswordEditText = (EditText) view.findViewById(R.id.id_et_fragment_login_passwort);
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
                loginFunction();
                // LoginFrament.this.getActivity().startActivity(new Intent(LoginFrament.this.getActivity(), HomeActivity.class));
            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }


    private Call mLoginRequestCall;
    private void loginFunction() {
        String url = HttpHelper.sBaseUrl + "?tag=login";

        String userName = mUserNameEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();

        if (!TextUtils.isEmpty(userName)) {
            if (!TextUtils.isEmpty(password)) {
                Map<String, String> keyMap = new HashMap<>();
                keyMap.put("username", userName);
                keyMap.put("password", password);

                mLoginRequestCall = OkhttpRequestUtils.getInstance().postRequest(url, keyMap, loginCallback);


            } else {
                Toast.makeText(App.mContext, "密码不可为空", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(App.mContext, "用户名不可为空", Toast.LENGTH_SHORT).show();
        }

    }

    private Callback loginCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            LogUtils.e("登录失败 " + e.getMessage());
        }

        @Override
        public void onResponse(Response response) throws IOException {
            final String result = response.body().string();
            LogUtils.d("登录 请求 成功 " + result);
            final LoginResponseModel loginResponseModel = GsonUtil.parseJsonWithGson(result, LoginResponseModel.class);
            LogUtils.d("解析数据 " + loginResponseModel.toString());

            App.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (TextUtils.isEmpty(loginResponseModel.code)) {
                        LogUtils.d("登录  信息异常 " + result);
                        ToastUtils.show( "登录  信息异常");
                    } else if (TextUtils.equals(loginResponseModel.code, "1000")) {

                        if (loginResponseModel.content == null) {
                            LogUtils.d("登录  登录失败 " + result);
                            ToastUtils.show("登录  登录失败 " + result);
                        } else {
                            LogUtils.d("登录  登录成功 " + result);
                            App.sUserInfoModel = loginResponseModel.content;
                            UserInfoInformationFunction.getInstance().saveUserInfoModel(loginResponseModel.content);
                            LoginFrament.this.getActivity().startActivity(new Intent(LoginFrament.this.getActivity(), HomeActivity.class));
                            LoginFrament.this.getActivity().finish();
                        }


                    } else {
                        LogUtils.e("登录失败");
                        ToastUtils.show( "登录  信息异常" + loginResponseModel.message);
                    }
                }
            });

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消登录
        if (mLoginRequestCall != null) {
            if (!mLoginRequestCall.isCanceled()) {
                mLoginRequestCall.cancel();
            }
        }
    }
}
