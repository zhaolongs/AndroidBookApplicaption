package com.androidlongs.bookapplication.main.home.model;

import com.androidlongs.bookapplication.base.BaseModel;

import java.io.Serializable;

/**
 * Created by androidlongs on 16/12/19.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */


public class BookClassModel extends BaseModel implements Serializable{
    private static final long serialVersionUID = 1L;
    public String id;
    public String name;
    public String description;
    public String path="";

}
