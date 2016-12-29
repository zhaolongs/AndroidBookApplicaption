package com.androidlongs.bookapplication.main.forum.model;

import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.common.UserInfoModel;

import java.io.Serializable;

/**
 * Created by androidlongs on 16/12/29.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class ForumModel extends BaseModel implements Serializable {

    //表序号
    public Integer fuid;
    //内容uuid
    public String fuuid;
    //对应用户uuid
    public String userUuid;
    //内容描述
    public String fDesc;

    public UserInfoModel fUserModel;
}
