package com.androidlongs.bookapplication.main.login.frament;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.BaseFrament;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class WelcomeFrament extends BaseFrament {
    @Override
    public int getContentView() {
        return R.layout.activity_frament_welcome;
    }

    private TextView mSingTextView1;
    private TextView mSingTextView2;
    private TextView mSingTextView3;

    @Override
    public void initView(View view) {
        mSingTextView1 = (TextView) view.findViewById(R.id.id_tv_welcome_sing_1);
        mSingTextView2 = (TextView) view.findViewById(R.id.id_tv_welcome_sing_2);
        mSingTextView3 = (TextView) view.findViewById(R.id.id_tv_welcome_sing_3);
    }

    @Override
    public void commonFunction() {

        setTextViewAnimation();
    }

    private void setTextViewAnimation() {
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.0f, 1.2f);
        alphaAnimation1.setStartOffset(500);
        alphaAnimation1.setDuration(1000);

        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.2f);
        alphaAnimation2.setStartOffset(800);
        alphaAnimation2.setDuration(1000);

        AlphaAnimation alphaAnimation3 = new AlphaAnimation(0.0f, 1.2f);
        alphaAnimation3.setStartOffset(1000);
        alphaAnimation3.setDuration(1000);

        mSingTextView1.setAnimation(alphaAnimation1);
        mSingTextView2.setAnimation(alphaAnimation2);
        mSingTextView3.setAnimation(alphaAnimation3);


    }
}
