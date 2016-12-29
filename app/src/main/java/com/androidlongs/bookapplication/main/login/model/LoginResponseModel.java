package com.androidlongs.bookapplication.main.login.model;

import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.common.UserInfoModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by androidlongs on 16/12/23.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class LoginResponseModel implements Serializable {

    public String code;
    public String message;
    public UserInfoModel content;
    public List<BaseModel> contentList;

    @Override
    public String toString() {
        return "LoginResponseModel{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}' + '\'' +contentList;
    }
}
