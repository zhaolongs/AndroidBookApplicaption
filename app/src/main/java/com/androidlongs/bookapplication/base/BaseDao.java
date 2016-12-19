package com.androidlongs.bookapplication.base;

import java.util.List;

/**
 * Created by androidlongs on 16/12/20.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public interface BaseDao {
    void inserModel(BaseModel model);
    BaseModel queryModel(String name);
    List<BaseModel> queryAllModel();
    void updateModel(BaseModel model);
    void deleteModel(String name);
}
