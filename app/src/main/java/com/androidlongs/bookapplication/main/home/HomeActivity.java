package com.androidlongs.bookapplication.main.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
import com.androidlongs.bookapplication.base.App;
import com.androidlongs.bookapplication.base.BaseActivity;
import com.androidlongs.bookapplication.main.find.FindMainFrament;
import com.androidlongs.bookapplication.main.forum.ForumMainFrament;
import com.androidlongs.bookapplication.main.home.frament.HomeBookClassFrament;
import com.androidlongs.bookapplication.main.home.frament.HomeBookListFrament;
import com.androidlongs.bookapplication.main.person.PersonMainFrament;

/**
 * 首页
 */

public class HomeActivity extends BaseActivity {

    private LinearLayout mFooterHomeLinearLayout;
    private LinearLayout mFooterFindLinearLayout;
    private LinearLayout mFooterForumLinearLayout;
    private LinearLayout mFooterPersonLinearLayout;


    private LinearLayout mCommonHeaderLinearLayout;
    private LinearLayout mHomeHeaderLinearLayout;

    private TextView mCommHeaderTitleTextView;



    private int mHeaderLablePressColor;
    private int mHeaderLableNormalColor;


    private int mFootertLablePressColor;
    private int mFooterLableNormalColor;
    private TextView mFooterHomeTextView;
    private ImageView mFooterHomeImageView;
    private TextView mFooterFindTextView;
    private ImageView mFooterFindImageView;
    private TextView mFooterForumTextView;
    private ImageView mFooterForumImageView;
    private TextView mFooterPersonTextView;
    private ImageView mFooterPersonImageView;

    @Override
    public int getContentView() {
        return R.layout.activity_home;
    }

    private TextView mLeftTitleLableTextView;
    private TextView mRightTitleLableTextView;

    @Override
    public void initView() {


        mCommonHeaderLinearLayout = (LinearLayout) findViewById(R.id.id_common_header);
        mHomeHeaderLinearLayout = (LinearLayout) findViewById(R.id.id_home_header);

        LinearLayout footerLinearLayout = (LinearLayout) findViewById(R.id.id_home_footer);

        mFooterHomeLinearLayout = (LinearLayout) footerLinearLayout.findViewById(R.id.id_ll_home_footer_home);
        mFooterFindLinearLayout = (LinearLayout) footerLinearLayout.findViewById(R.id.id_ll_home_footer_find);
        mFooterForumLinearLayout = (LinearLayout) footerLinearLayout.findViewById(R.id.id_ll_home_footer_rus);
        mFooterPersonLinearLayout = (LinearLayout) footerLinearLayout.findViewById(R.id.id_ll_home_footer_person);


        mFooterHomeTextView = (TextView) footerLinearLayout.findViewById(R.id.id_tv_home_footer_home);
        mFooterHomeImageView = (ImageView) footerLinearLayout.findViewById(R.id.id_iv_home_footer_home);


        mFooterFindTextView = (TextView) footerLinearLayout.findViewById(R.id.id_tv_home_footer_find);
        mFooterFindImageView = (ImageView) footerLinearLayout.findViewById(R.id.id_iv_home_footer_find);


        mFooterForumTextView = (TextView) footerLinearLayout.findViewById(R.id.id_tv_home_footer_rus);
        mFooterForumImageView = (ImageView) footerLinearLayout.findViewById(R.id.id_iv_home_footer_rus);


        mFooterPersonTextView = (TextView) footerLinearLayout.findViewById(R.id.id_tv_home_footer_person);
        mFooterPersonImageView = (ImageView) footerLinearLayout.findViewById(R.id.id_iv_home_footer_person);



        mLeftTitleLableTextView = (TextView) findViewById(R.id.id_home_title_lable_left);
        mRightTitleLableTextView = (TextView) findViewById(R.id.id_home_title_lable_right);

        //设置标题默认
        mLeftTitleLableTextView.setSelected(true);
        mRightTitleLableTextView.setSelected(false);


        mCommHeaderTitleTextView = (TextView) mCommonHeaderLinearLayout.findViewById(R.id.id_comm_header_title);
        mCommHeaderTitleTextView.setText("首页");


    }

    @Override
    public void commonFunction() {

        setViewClickFunction();
        //加载资源文件
        mHeaderLablePressColor = getResources().getColor(R.color.home_header_lable_text_press_color);
        mHeaderLableNormalColor = getResources().getColor(R.color.home_header_lable_text_normal_color);

        mFootertLablePressColor = getResources().getColor(R.color.home_footer_lable_text_normal_color);
        mFooterLableNormalColor= getResources().getColor(R.color.home_footer_lable_text_press_color);

        //延迟加载 Frament 页面
        App.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //默认页面
                onCheckedChanged(mTagHomeBookList, false);

            }
        }, 500);


        //设置 底部labale默认颜色
        mFooterHomeTextView.setTextColor(mFootertLablePressColor);
        mFooterHomeImageView.setSelected(true);


    }


    private void setViewClickFunction() {

        mLeftTitleLableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftTitleLableTextView.setSelected(true);
                mRightTitleLableTextView.setSelected(false);

                mLeftTitleLableTextView.setTextColor(mHeaderLablePressColor);
                mRightTitleLableTextView.setTextColor(mHeaderLableNormalColor);

                onCheckedChanged(mTagHomeBookList, true);

            }
        });
        mRightTitleLableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftTitleLableTextView.setSelected(false);
                mRightTitleLableTextView.setSelected(true);
                mLeftTitleLableTextView.setTextColor(mHeaderLableNormalColor);
                mRightTitleLableTextView.setTextColor(mHeaderLablePressColor);
                onCheckedChanged(mTagHomeBookClass, true);
            }
        });

        mFooterHomeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftTitleLableTextView.setTextColor(mHeaderLablePressColor);
                mRightTitleLableTextView.setTextColor(mHeaderLableNormalColor);

                mCommonHeaderLinearLayout.setVisibility(View.GONE);
                mHomeHeaderLinearLayout.setVisibility(View.VISIBLE);

                onCheckedChanged(mTagHomeBookList, true);

                mFooterHomeTextView.setTextColor(mFootertLablePressColor);
                mFooterHomeImageView.setSelected(true);

                mFooterFindTextView.setTextColor(mFooterLableNormalColor);
                mFooterFindImageView.setSelected(false);

                mFooterForumTextView.setTextColor(mFooterLableNormalColor);
                mFooterForumImageView.setSelected(false);
                mFooterPersonTextView.setTextColor(mFooterLableNormalColor);
                mFooterPersonImageView.setSelected(false);

            }
        });
        mFooterFindLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommonHeaderLinearLayout.setVisibility(View.VISIBLE);
                mHomeHeaderLinearLayout.setVisibility(View.GONE);
                onCheckedChanged(mTagFindFrament, false);
                mCommHeaderTitleTextView.setText("发现");

                mFooterHomeTextView.setTextColor(mFooterLableNormalColor);
                mFooterHomeImageView.setSelected(false);
                mFooterFindTextView.setTextColor(mFootertLablePressColor);
                mFooterFindImageView.setSelected(true);

                mFooterForumTextView.setTextColor(mFooterLableNormalColor);
                mFooterForumImageView.setSelected(false);
                mFooterPersonTextView.setTextColor(mFooterLableNormalColor);
                mFooterPersonImageView.setSelected(false);
            }
        });
        mFooterForumLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommonHeaderLinearLayout.setVisibility(View.VISIBLE);
                mHomeHeaderLinearLayout.setVisibility(View.GONE);
                onCheckedChanged(mTagFroumFrament, false);
                mCommHeaderTitleTextView.setText("论坛");

                mFooterHomeTextView.setTextColor(mFooterLableNormalColor);
                mFooterHomeImageView.setSelected(false);

                mFooterFindTextView.setTextColor(mFooterLableNormalColor);
                mFooterFindImageView.setSelected(false);
                mFooterForumTextView.setTextColor(mFootertLablePressColor);
                mFooterForumImageView.setSelected(true);
                mFooterPersonTextView.setTextColor(mFooterLableNormalColor);
                mFooterPersonImageView.setSelected(false);
            }
        });
        mFooterPersonLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommonHeaderLinearLayout.setVisibility(View.VISIBLE);
                mHomeHeaderLinearLayout.setVisibility(View.GONE);
                onCheckedChanged(mTagPersonFrament, false);
                mCommHeaderTitleTextView.setText("个人中心");

                mFooterHomeTextView.setTextColor(mFooterLableNormalColor);
                mFooterHomeImageView.setSelected(false);


                mFooterFindTextView.setTextColor(mFooterLableNormalColor);
                mFooterFindImageView.setSelected(false);
                mFooterForumTextView.setTextColor(mFooterLableNormalColor);
                mFooterForumImageView.setSelected(false);
                mFooterPersonTextView.setTextColor(mFootertLablePressColor);
                mFooterPersonImageView.setSelected(true);

            }
        });
    }

    private String mTagHomeBookList = "home_book_list";
    private String mTagHomeBookClass = "home_bo0k_class";

    private String mTagFindFrament = "find_frament";
    private String mTagFroumFrament = "forum_frament";

    private String mTagPersonFrament = "person_frament";

    //默认值
    private String mCurrentTagFrament = "";

    public void onCheckedChanged(String tag, boolean flag) {
        //重复点击 加载页面
        if (TextUtils.equals(tag, mCurrentTagFrament)) {
            return;
        }


        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //设置切换动画
        if (flag) {
            if (TextUtils.equals(tag, mTagHomeBookList)) {
                ft.setCustomAnimations(
                        R.animator.home_book_list_show,
                        R.animator.home_book_list_hide,
                        R.animator.back_from_in,
                        R.animator.back_from_out);
            } else {
                ft.setCustomAnimations(
                        R.animator.from_right,
                        R.animator.to_left,
                        R.animator.back_from_in,
                        R.animator.back_from_out);
            }

        }

        //当前页面
        mCurrentTagFrament = tag;
        Fragment mHomeBookListFrament = fm.findFragmentByTag(mTagHomeBookList);
        Fragment mHomeBookClassFrament = fm.findFragmentByTag(mTagHomeBookClass);
        Fragment mFindMainFrament = fm.findFragmentByTag(mTagFindFrament);
        Fragment mForumMainFrament = fm.findFragmentByTag(mTagFroumFrament);
        Fragment mPersonMainFrament = fm.findFragmentByTag(mTagPersonFrament);


        if (mHomeBookListFrament != null) {
            ft.hide(mHomeBookListFrament);
        }
        if (mHomeBookClassFrament != null) {
            ft.hide(mHomeBookClassFrament);
        }
        if (mFindMainFrament != null) {
            ft.hide(mFindMainFrament);
        }
        if (mForumMainFrament != null) {
            ft.hide(mForumMainFrament);
        }

        if (mPersonMainFrament != null) {
            ft.hide(mPersonMainFrament);
        }


        if (TextUtils.equals(tag, mTagHomeBookList)) {
            if (mHomeBookListFrament == null) {
                mHomeBookListFrament = new HomeBookListFrament();
                ft.add(R.id.id_fl_main_content, mHomeBookListFrament, mTagHomeBookList).addToBackStack(mTagHomeBookList);
            } else {
                ft.show(mHomeBookListFrament);
            }

        } else if (TextUtils.equals(tag, mTagHomeBookClass)) {

            if (mHomeBookClassFrament == null) {
                mHomeBookClassFrament = new HomeBookClassFrament();
                ft.add(R.id.id_fl_main_content, mHomeBookClassFrament, mTagHomeBookClass).addToBackStack(mTagHomeBookClass);
            } else {
                ft.show(mHomeBookClassFrament);
            }
        } else if (TextUtils.equals(tag, mTagFindFrament)) {


            if (mFindMainFrament == null) {
                mFindMainFrament = new FindMainFrament();
                ft.add(R.id.id_fl_main_content, mFindMainFrament, mTagFindFrament).addToBackStack(mTagFindFrament);
            } else {
                ft.show(mFindMainFrament);
            }
        } else if (TextUtils.equals(tag, mTagFroumFrament)) {
            if (mForumMainFrament == null) {
                mForumMainFrament = new ForumMainFrament();
                ft.add(R.id.id_fl_main_content, mForumMainFrament, mTagFroumFrament).addToBackStack(mTagFroumFrament);
            } else {
                ft.show(mForumMainFrament);
            }

        } else if (TextUtils.equals(tag, mTagPersonFrament)) {
            if (mPersonMainFrament == null) {
                mPersonMainFrament = new PersonMainFrament();
                ft.add(R.id.id_fl_main_content, mPersonMainFrament, mTagPersonFrament).addToBackStack(mTagPersonFrament);
            } else {
                ft.show(mPersonMainFrament);
            }

        }


        ft.commit();
    }
}

