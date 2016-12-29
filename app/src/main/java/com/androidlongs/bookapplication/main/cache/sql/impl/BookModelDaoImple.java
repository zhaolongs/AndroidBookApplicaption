package com.androidlongs.bookapplication.main.cache.sql.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.cache.sql.DBManagerHelper;
import com.androidlongs.bookapplication.main.cache.sql.DBManagerUtils;
import com.androidlongs.bookapplication.main.cache.sql.dao.BookClassModelDao;
import com.androidlongs.bookapplication.main.cache.sql.dao.BookModelDao;
import com.androidlongs.bookapplication.main.home.model.BookModel;
import com.androidlongs.bookapplication.main.util.LogUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by androidlongs on 16/12/20.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class BookModelDaoImple implements BookModelDao {
    private BookClassModelDao mBookClassModelDao = new BookClassModelDaoImple();

    @Override
    public void inserModel(BaseModel model) {
        BookModel bookModel = (BookModel) model;



        String inserSql =
                "insert into books(id,name,author,description,path,filename,categoryId,addTime)" +
                        " values( ";
        StringBuilder builder = new StringBuilder();
        builder.append(inserSql);
        builder.append(" \"");
        builder.append(bookModel.uuid);
        builder.append("\", \"");
        builder.append(bookModel.bname);
        builder.append("\", \"");
        builder.append(bookModel.bauthor);
        builder.append("\", \"");
        builder.append(bookModel.bdesc);
        builder.append("\", \"");
        builder.append(bookModel.bpath);
        builder.append("\", \"");
        builder.append(bookModel.filename);
        builder.append("\", \"");
        builder.append("");
        builder.append("\", \"");
        builder.append(new Date().getTime() + "");
        builder.append("\" )");

        inserSql = builder.toString().trim();
        BaseModel baseModel = queryModel(bookModel.bname);
        if (baseModel == null) {
            LogUtils.d("数据库 新增书籍 "+inserSql);
            DBManagerUtils.getInstance().exeData(inserSql);

        } else {
            updateModel(bookModel);
        }


    }

    @Override
    public BaseModel queryModel(String name) {
        LogUtils.d("数据库 查询 "+name+"  操作");
        String querySql = "select  * from books where name = ?";
        DBManagerHelper helper = DBManagerUtils.getInstance().getHelper();
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(querySql, new String[] {name});

        if (cursor != null) {
            BookModel bookModel = new BookModel();
            while (cursor.moveToNext()) {
                bookModel.uuid = cursor.getString(cursor.getColumnIndex("id"));
                bookModel.bname = cursor.getString(cursor.getColumnIndex("name"));
                bookModel.bauthor = cursor.getString(cursor.getColumnIndex("author"));
                bookModel.bdesc = cursor.getString(cursor.getColumnIndex("description"));
                bookModel.filename = cursor.getString(cursor.getColumnIndex("filename"));
                bookModel.bpath = cursor.getString(cursor.getColumnIndex("path"));
                String categoryId = cursor.getString(cursor.getColumnIndex("categoryId"));
                if (!TextUtils.isEmpty(categoryId)) {
//                    BookClassModel baseModel = (BookClassModel) mBookClassModelDao.queryModel(bookModel.uuid);
//                    bookModel.category = baseModel;
                }

            }

            return bookModel;
        }
        return null;
    }

    @Override
    public List<BaseModel> queryAllModel() {
        LogUtils.d("数据库 查询所有数据 操作");

        String auerySql = "select * from books ";
        DBManagerHelper helper = DBManagerUtils.getInstance().getHelper();
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(auerySql, null);

        List<BaseModel> list = new ArrayList<>();
        if (cursor != null) {
            int count = cursor.getCount();
            while (cursor.moveToNext()) {
                BookModel bookModel = new BookModel();
                bookModel.uuid = cursor.getString(cursor.getColumnIndex("id"));
                bookModel.bname = cursor.getString(cursor.getColumnIndex("name"));
                bookModel.bauthor = cursor.getString(cursor.getColumnIndex("author"));
                bookModel.bdesc= cursor.getString(cursor.getColumnIndex("description"));
                bookModel.filename = cursor.getString(cursor.getColumnIndex("filename"));
                bookModel.bpath = cursor.getString(cursor.getColumnIndex("path"));
                String categoryId = cursor.getString(cursor.getColumnIndex("categoryId"));
//                if (!TextUtils.isEmpty(categoryId)) {
//                    BookClassModel baseModel = (BookClassModel) mBookClassModelDao.queryModel(categoryId);
//                    bookModel.category = baseModel;
//                }

                list.add(bookModel);

            }

            cursor.close();
        }
        readableDatabase.close();


        return list;
    }

    @Override
    public void updateModel(BaseModel model) {
        LogUtils.d("数据库 书籍信息更新操作");
        BookModel bookModel = (BookModel) model;
        String inserSql =
                "update  books  set name = ?,author =?," +
                        "description = ?,path = ?,filename = ?," +
                        "categoryId = ?,addTime= ? where id = ?";

        String categoryId = "";


        String[] values =
                {
                        bookModel.bname,
                        bookModel.bauthor,
                        bookModel.bdesc,
                        bookModel.bpath,
                        bookModel.filename,
                        categoryId,
                        new Date().getTime() + "",
                        ""

                };

        DBManagerUtils.getInstance().exeData(inserSql, values);

    }

    @Override
    public void deleteModel(String name) {
        LogUtils.d("数据库 书籍信息删除操作");
        String inserSql = "";
        DBManagerUtils.getInstance().exeData(inserSql);
    }
}
