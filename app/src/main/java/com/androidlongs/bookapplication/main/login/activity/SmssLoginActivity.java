package com.androidlongs.bookapplication.main.login.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.BaseActivity;
import com.androidlongs.bookapplication.main.util.LogUtils;
import com.androidlongs.bookapplication.main.util.SharedPreferencesUtil;
import com.androidlongs.bookapplication.main.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by androidlongs on 16/12/24.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class SmssLoginActivity extends BaseActivity {


    private ImageView mBackImageView;
    private EditText mPhoneEditText;
    private TextView mGetVerTextView;
    private EditText mInputEditText;
    private LinearLayout mNextLinearLayout;

    @Override
    public int getContentView() {
        return R.layout.activity_smss_login;
    }

    @Override
    public void initView() {

        mBackImageView = (ImageView) findViewById(R.id.id_iv_smss_login_back);
        mPhoneEditText = (EditText) findViewById(R.id.id_et_smss_login_phone);
        mGetVerTextView = (TextView) findViewById(R.id.id_et_smss_get_login_password);
        mInputEditText = (EditText) findViewById(R.id.id_et_smss_login_password);
        mNextLinearLayout = (LinearLayout) findViewById(R.id.id_ll_next);
    }

    @Override
    public void commonFunction() {
        //注册短信回调
        SMSSDK.registerEventHandler(mEventHandler);


        mBackImageView.setOnClickListener(mBackOnClickListener);
        mGetVerTextView.setOnClickListener(mGetSmssOnClickListener);
        mNextLinearLayout.setOnClickListener(mSubmitSmssOnClickListener);

    }

   //退出
    private View.OnClickListener mBackOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SmssLoginActivity.this.finish();
        }
    };
    //获取验证码
    private View.OnClickListener mGetSmssOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           getSmssFunction();
        }
    };

    private void getSmssFunction() {
        String phoneString = mPhoneEditText.getText().toString();
        if (TextUtils.isEmpty(phoneString)) {
            ToastUtils.show("请输入手机号");
            return;
        }

        //SMSSDK.getSupportedCountries();
        SMSSDK.getVerificationCode("86", phoneString,mOnSendMessageHandler);

    }
    //提交验证
    private View.OnClickListener mSubmitSmssOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            submitVeriFunction();
        }
    };
    private void submitVeriFunction(){

        String phoneString = mPhoneEditText.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            ToastUtils.show("请输入手机号");
            return;
        }
        String verString = mInputEditText.getText().toString().trim();
        if (TextUtils.isEmpty(verString)) {
            ToastUtils.show("请输入验证码");
            return;
        }


       SMSSDK.submitVerificationCode("86",phoneString, verString);

    }
    private  EventHandler mEventHandler=new EventHandler(){

        @Override
        public void afterEvent(final int event, final int result, final Object data) {

            App.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //回调完成
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            ToastUtils.show("提交验证码成功");
                            //提交验证码成功
                        }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                            //true为智能验证，false为普通下发短信
                            //获取验证码成功
                            ToastUtils.show("获取验证码成功");
                        }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                            //返回支持发送验证码的国家列表
                            ArrayList<HashMap<String,Object>> list = (ArrayList<HashMap<String, Object>>) data;
                        }
                    }else{
                        //
                        ((Throwable)data).printStackTrace();
                        ToastUtils.show("验证码输入失败");
                    }
                }
            });

        }
    };
    /**
     * 发送短信前的回调
     */
    private OnSendMessageHandler mOnSendMessageHandler = new OnSendMessageHandler() {
        @Override
        public boolean onSendMessage(String country, String phone) {
            LogUtils.d("send message to country "+country +" "+phone);
            return false;
        }
    };
    public interface OnSendMessageHandler extends cn.smssdk.OnSendMessageHandler {

        //#if def{lang} == cn
        /**
         * 此方法在发送验证短信前被调用，传入参数为接收者号码
         * 返回true表示此号码无须实际接收短信
         */
        //#elif def{lang} == en
        /**
         * This method will be called before verification message being to sent,
         * input params are the message receiver
         * return true means this number won't actually receive the message
         */
        //#endif
        boolean onSendMessage(String country, String phone);

    }

    private String mMSMMSendFinishTag = "send-smss-finish";
    private String mMSMMNoSendFinishTag = "no-send-smss-finish";
    private long mMsmmSendTime = 0;
    private String mMsmmSendTimeTag = "smss-time-tag";
    private String mMsmmStatueTag = "smss-tag";
    private String mCurrentMsmmSendStatue = mMSMMNoSendFinishTag;


    private long mFlagTime = 0;

    private void setSmssFunction(){
        mCurrentMsmmSendStatue = SharedPreferencesUtil.getInstance().getData(mMsmmStatueTag);
        if (TextUtils.isEmpty(mCurrentMsmmSendStatue)) {
            mCurrentMsmmSendStatue = mMSMMNoSendFinishTag;
        }else {
            String sendTime = SharedPreferencesUtil.getInstance().getData(mMsmmSendTimeTag);
            if (TextUtils.isEmpty(sendTime)) {
                mMsmmSendTime =0;
            }else {
                mMsmmSendTime = Long.valueOf(sendTime);
            }

            Long currentTime = System.currentTimeMillis();
            mFlagTime = (5*60*1000) -(currentTime - mMsmmSendTime );
            if (mFlagTime>=0){
                mCurrentMsmmSendStatue =mMSMMNoSendFinishTag;
            }else {
                mCurrentMsmmSendStatue = mMSMMSendFinishTag;
                App.mHandler.post(mRunnable);
            }
        }
    }
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mFlagTime>0){
                mFlagTime -=1000;
                App.mHandler.postDelayed(mRunnable,1000);
                App.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mGetVerTextView.setText((mFlagTime/1000)+"秒后发送");
                    }
                });
            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销短信回调
        SMSSDK.unregisterEventHandler(mEventHandler);

    }
}
