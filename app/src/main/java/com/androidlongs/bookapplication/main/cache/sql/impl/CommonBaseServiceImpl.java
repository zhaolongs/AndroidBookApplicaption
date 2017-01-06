package com.androidlongs.bookapplication.main.cache.sql.impl;

import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.cache.sql.dao.BookClassModelDao;
import com.androidlongs.bookapplication.main.cache.sql.dao.BookModelDao;
import com.androidlongs.bookapplication.main.cache.sql.dao.CommonBaseServiceInterface;
import com.androidlongs.bookapplication.main.home.model.BookClassModel;
import com.androidlongs.bookapplication.main.home.model.BookModel;

import java.util.List;

/**
 * Created by androidlongs on 16/12/20.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class CommonBaseServiceImpl implements CommonBaseServiceInterface {
    private BookModelDao mBookModelDao = new BookModelDaoImple();
    private BookClassModelDao mBookClassModelDao = new BookClassModelDaoImple();

    @Override
    public void addBookModel(BookModel bookModel) {
        mBookModelDao.inserModel(bookModel);
    }

    @Override
    public BookModel queryBookModel(String name) {

        return (BookModel) mBookModelDao.queryModel(name);
    }

    @Override
    public List<BaseModel> queryAllBookModel() {
        return mBookModelDao.queryAllModel();
    }

    @Override
    public void addBookClassModel(BaseModel bookClassModel) {
        mBookClassModelDao.inserModel(bookClassModel);
    }

    @Override
    public BookClassModel queryBookClassModel(String name) {
        return (BookClassModel) mBookClassModelDao.queryModel(name);
    }

    @Override
    public List<BaseModel> queryAllBookClassModel() {
        return mBookClassModelDao.queryAllModel();
    }

}
