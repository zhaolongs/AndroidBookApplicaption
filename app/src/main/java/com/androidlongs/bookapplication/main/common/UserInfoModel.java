package com.androidlongs.bookapplication.main.common;

import com.androidlongs.bookapplication.main.home.model.BookModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class UserInfoModel {
    public Integer uid;
    public String userUUID;
    
    public String uname;
    public String uage;
    public String udesc;
    public String usex;
    public String upassword;
    //我的书架
    public Set<BookModel> uBookSet = new HashSet<>();
}
