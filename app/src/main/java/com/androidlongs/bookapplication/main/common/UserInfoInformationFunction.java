package com.androidlongs.bookapplication.main.common;

import com.androidlongs.bookapplication.base.AppConfigFile;
import com.androidlongs.bookapplication.base.BaseFunction;

/**
 * Created by androidlongs on 16/12/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class UserInfoInformationFunction extends BaseFunction {
    private UserInfoInformationFunction() {

    }

    private static class SlingUserInfoFunction {
        private static UserInfoInformationFunction sUserInfoInformationFunction = new UserInfoInformationFunction();
    }

    public static UserInfoInformationFunction getInstance() {
        return SlingUserInfoFunction.sUserInfoInformationFunction;
    }


    public static UserInfoModel sUserInfoModel;

    public void saveUserInfoModel(UserInfoModel model) {
        if (model != null) {
            String userInfation = mGson.toJson(model);
            //保存用户信息
            mSharedPreferencesUtil.saveData(AppConfigFile.spUserName, userInfation);
        }
    }

    /**
     * 获取用户信息
     * @param flag true 更新内存缓存
     */
    public UserInfoModel getUserInfoModel(boolean flag) {
        if (flag) {
            String data = mSharedPreferencesUtil.getData(AppConfigFile.spUserName);
            sUserInfoModel = mGson.fromJson(data, UserInfoModel.class);
            return sUserInfoModel;
        } else {
            if (sUserInfoModel != null) {
                return sUserInfoModel;
            } else {
                String data = mSharedPreferencesUtil.getData(AppConfigFile.spUserName);
                sUserInfoModel = mGson.fromJson(data, UserInfoModel.class);
                return sUserInfoModel;
            }
        }

    }
}
