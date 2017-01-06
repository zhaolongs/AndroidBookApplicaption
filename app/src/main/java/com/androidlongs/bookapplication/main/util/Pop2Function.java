package com.androidlongs.bookapplication.main.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;

/**
 * Created by androidlongs on 17/1/5.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class Pop2Function {


    private Button mChoseButton;
    private Button mCanleButton;
    private View mCenterView;

    private Pop2Function(){

    }
    private static class  Single{
        private static Pop2Function sPop2Function = new Pop2Function();
    }
    public static Pop2Function getInstance(){
        return Single.sPop2Function;
    }

    private View getView(){
        return View.inflate(App.mContext, R.layout.pop2_main_layout,null);
    }

    private PopupWindow mPopupWindow;
    private TextView mMainContentTextView;

    public Pop2Function initFunction(){
        close();
        View view = getView();
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        mMainContentTextView = (TextView) view.findViewById(R.id.id_pop2_main_content);
        mChoseButton = (Button) view.findViewById(R.id.id_pop2_main_chose);
        mCanleButton = (Button) view.findViewById(R.id.id_pop2_main_canle);
        mCenterView = view.findViewById(R.id.id_pop2_main_center);

        //焦点设置
        mPopupWindow.setFocusable(true);
        //设置点击背景不消失
        mPopupWindow.setOutsideTouchable(true);
        // 设置透明背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(
                Color.TRANSPARENT));
        //设置动画

        mPopupWindow.setAnimationStyle(R.style.pop2_from_bottom_anim_style);

        return Single.sPop2Function;
    }
    public void show(View view){
        if (mPopupWindow == null) {
            return;
        }

        //显示
        mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);

    }

    //设置消息 内容主体
    public Pop2Function setContentTextView(String  content) {
        if (mMainContentTextView != null) {
            if (!TextUtils.isEmpty(content)) {
                mMainContentTextView.setText(content);
            }
        }
        return Single.sPop2Function;
    }
    //设置确认
    public Pop2Function setChoseButton(String  content) {
        if (mChoseButton != null) {
            if (!TextUtils.isEmpty(content)) {
                mChoseButton.setVisibility(View.VISIBLE);
                mChoseButton.setText(content);
                mChoseButton.setOnClickListener(mChoseOnClickListener);
            }
        }
        return Single.sPop2Function;
    }
    //设置取消
    public Pop2Function setCanleButton(String  content) {
        if (mCanleButton != null) {
            if (!TextUtils.isEmpty(content)) {
                mCenterView.setVisibility(View.VISIBLE);
                mCanleButton.setVisibility(View.VISIBLE);
                mCanleButton.setText(content);
                mCanleButton.setOnClickListener(mCanleOnClickListener);
            }
        }
        return Single.sPop2Function;
    }

    private void close(){
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }

    public interface OnPop2ChoseClickListener{
        void onChose();
    }
    private OnPop2ChoseClickListener mOnPop2ChoseClickListener;

    public Pop2Function setChoseClickListener(OnPop2ChoseClickListener onPop2ChoseClickListener) {
        mOnPop2ChoseClickListener = onPop2ChoseClickListener;
        return Single.sPop2Function;
    }

    private View.OnClickListener mChoseOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            close();
            if (mOnPop2ChoseClickListener != null) {
                mOnPop2ChoseClickListener.onChose();
            }
        }
    };

    public interface OnPop2CanleClickListener{
        void onCanle();
    }
    private OnPop2CanleClickListener mOnPop2CanleClickListener;

    public Pop2Function setCanleClickListener(OnPop2CanleClickListener canleClickListener) {
        mOnPop2CanleClickListener = canleClickListener;
        return Single.sPop2Function;
    }

    private View.OnClickListener mCanleOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            close();
            if (mOnPop2CanleClickListener != null) {
                mOnPop2CanleClickListener.onCanle();
            }
        }
    };
}
