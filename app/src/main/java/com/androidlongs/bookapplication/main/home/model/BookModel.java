package com.androidlongs.bookapplication.main.home.model;

import java.io.Serializable;

/**
 * Created by androidlongs on 16/12/19.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class BookModel implements Serializable {
    public String id;
    public String name;
    public String author;
    public String description;
    public String path;
    public String filename;

    public BookClassModel category;

}
