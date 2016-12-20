package com.androidlongs.bookapplication.main.cache.sql.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.cache.sql.DBManagerHelper;
import com.androidlongs.bookapplication.main.cache.sql.DBManagerUtils;
import com.androidlongs.bookapplication.main.cache.sql.dao.BookClassModelDao;
import com.androidlongs.bookapplication.main.home.model.BookClassModel;
import com.androidlongs.bookapplication.main.util.LogUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by androidlongs on 16/12/20.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class BookClassModelDaoImple implements BookClassModelDao {
    @Override
    public void inserModel(BaseModel model) {
        BookClassModel classModel = (BookClassModel) model;

        String inserSql = "insert into book_class (id,name,description,path,addTime) values (";
        StringBuilder builder = new StringBuilder();

        //查询是否已存
        BaseModel baseModel = queryModel(classModel.id);


        builder.append(inserSql);
        builder.append("  \"");
        builder.append(classModel.id);
        builder.append("\" , \"");
        builder.append(classModel.name);

        builder.append("\", \"");
        builder.append(classModel.path);
        builder.append("\", \"");
        builder.append(classModel.description);
        builder.append("\", \"");
        builder.append(new Date().getTime() + "");
        builder.append("\" )");


        if (baseModel == null) {
            //新增
            inserSql = builder.toString().trim();
            DBManagerUtils.getInstance().exeData(inserSql);
            LogUtils.d("数据库  新增书籍分类信息 "+inserSql);
        } else {

            //更新
            updateModel(classModel);


        }

    }

    @Override
    public BaseModel queryModel(String name) {

        String querySql = "select * from book_class where id =?";
        DBManagerHelper helper = DBManagerUtils.getInstance().getHelper();
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(querySql, new String[] {name});

        if (cursor != null) {
            BookClassModel bookClassModel = new BookClassModel();
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    bookClassModel.id = cursor.getString(cursor.getColumnIndex("id"));
                    bookClassModel.name = cursor.getString(cursor.getColumnIndex("name"));
                    bookClassModel.description = cursor.getString(cursor.getColumnIndex("description"));
                }
                LogUtils.d("数据库  查询书籍分类信息 "+bookClassModel.toString());
                return bookClassModel;
            }
            LogUtils.d("数据库  未查询到书籍分类信息 ");
        }
        return null;
    }

    @Override
    public List<BaseModel> queryAllModel() {
        return null;
    }

    @Override
    public void updateModel(BaseModel model) {
        BookClassModel classModel = (BookClassModel) model;

        String inserSql = "update  book_class set name = ?,description =?,path=?,addTime=? where id = ? ";


        String[] values =
                {
                        classModel.name,
                        classModel.description,
                        classModel.path,
                        new Date().getTime() + "",
                        classModel.id
                };


        LogUtils.d("数据库  更新书籍分类信息 "+values.toString());
        DBManagerUtils.getInstance().exeData(inserSql, values);
    }

    @Override
    public void deleteModel(String name) {

    }
}
