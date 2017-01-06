package com.androidlongs.bookapplication.main.cache.sql.dao;

import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.home.model.BookClassModel;
import com.androidlongs.bookapplication.main.home.model.BookModel;

import java.util.List;

/**
 * Created by androidlongs on 16/12/20.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public interface CommonBaseServiceInterface {
    void addBookModel(BookModel bookModel);
    BookModel queryBookModel(String name);
    List<BaseModel> queryAllBookModel();




    void addBookClassModel(BaseModel bookClassModel);
    BookClassModel queryBookClassModel(String name);
    List<BaseModel> queryAllBookClassModel();
}
