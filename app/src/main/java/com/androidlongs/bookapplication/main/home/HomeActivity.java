package com.androidlongs.bookapplication.main.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidlongs.bookapplication.R;
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
    private int mHeaderLablePressColor;
    private int mHeaderLableNormalColor;
    private TextView mCommHeaderTitleTextView;

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


        mHeaderLablePressColor = getResources().getColor(R.color.home_header_lable_text_press_color);
        mHeaderLableNormalColor = getResources().getColor(R.color.home_header_lable_text_normal_color);
        //默认页面
        onCheckedChanged(mTagHomeBookList,false);


    }


    private void setViewClickFunction() {

        mLeftTitleLableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftTitleLableTextView.setSelected(true);
                mRightTitleLableTextView.setSelected(false);

                mLeftTitleLableTextView.setTextColor(mHeaderLablePressColor);
                mRightTitleLableTextView.setTextColor(mHeaderLableNormalColor);

                onCheckedChanged(mTagHomeBookList,true);

            }
        });
        mRightTitleLableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftTitleLableTextView.setSelected(false);
                mRightTitleLableTextView.setSelected(true);
                mLeftTitleLableTextView.setTextColor(mHeaderLableNormalColor);
                mRightTitleLableTextView.setTextColor(mHeaderLablePressColor);
                onCheckedChanged(mTagHomeBookClass,true);
            }
        });

        mFooterHomeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftTitleLableTextView.setTextColor(mHeaderLablePressColor);
                mRightTitleLableTextView.setTextColor(mHeaderLableNormalColor);

                mCommonHeaderLinearLayout.setVisibility(View.GONE);
                mHomeHeaderLinearLayout.setVisibility(View.VISIBLE);

                onCheckedChanged(mTagHomeBookList,true);


            }
        });
        mFooterFindLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommonHeaderLinearLayout.setVisibility(View.VISIBLE);
                mHomeHeaderLinearLayout.setVisibility(View.GONE);
                onCheckedChanged(mTagFindFrament,false);
                mCommHeaderTitleTextView.setText("发现");
            }
        });
        mFooterForumLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommonHeaderLinearLayout.setVisibility(View.VISIBLE);
                mHomeHeaderLinearLayout.setVisibility(View.GONE);
                onCheckedChanged(mTagFroumFrament,false);
                mCommHeaderTitleTextView.setText("论坛");
            }
        });
        mFooterPersonLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommonHeaderLinearLayout.setVisibility(View.VISIBLE);
                mHomeHeaderLinearLayout.setVisibility(View.GONE);
                onCheckedChanged(mTagPersonFrament,false);
                mCommHeaderTitleTextView.setText("个人中心");

            }
        });
    }

    private String mTagHomeBookList = "home_book_list";
    private String mTagHomeBookClass = "home_bo0k_class";

    private String mTagFindFrament = "find_frament";
    private String mTagFroumFrament = "forum_frament";

    private String mTagPersonFrament = "person_frament";

    //默认值
    private String mCurrentTagFrament = mTagHomeBookList;

    public void onCheckedChanged(String tag,boolean flag) {
        //重复点击 加载页面
        if (TextUtils.equals(tag,mCurrentTagFrament)) {
            return;
        }
        //当前页面
        mCurrentTagFrament = tag;

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //设置切换动画
        if (flag){
            if (TextUtils.equals(tag,mTagHomeBookList)) {
                ft.setCustomAnimations(
                        R.animator.home_book_list_show,
                        R.animator.home_book_list_hide,
                        R.animator.back_from_in,
                        R.animator.back_from_out);
            }else {
                ft.setCustomAnimations(
                        R.animator.from_right,
                        R.animator.to_left,
                        R.animator.back_from_in,
                        R.animator.back_from_out);
            }

        }

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

