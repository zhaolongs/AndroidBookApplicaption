package com.androidlongs.bookapplication.main.home.model;

import com.androidlongs.bookapplication.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by androidlongs on 16/12/19.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */


public class BookClassModel extends BaseModel implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * bBookSet : []
     * bcaddTime : 1483631861992
     * bcdesc : 小说可以一种很神奇的东西
     * bcid : 19
     * bclastUpdateTime : 1483631861992
     * bcname : 小说
     * bcuuid : ba050d5f-4544-4404-a548-fab792bfd87f
     */

    public String bcaddTime;
    public String bcdesc;
    public int bcid;
    public String bclastUpdateTime;
    public String bcname;
    public String bcuuid;
    public List<BookModel> bBookSet;

}
