package com.androidlongs.bookapplication.main.home.model;

import com.androidlongs.bookapplication.base.BaseModel;
import com.androidlongs.bookapplication.main.common.UserInfoModel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by androidlongs on 16/12/19.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

/**
 * @Entity注解 schema：告知GreenDao当前实体属于哪个schema
 * active：标记一个实体处于活动状态，活动实体有更新、删除和刷新方法
 * nameInDb：在数据中使用的别名，默认使用的是实体的类名
 * indexes：定义索引，可以跨越多个列
 * createInDb：标记创建数据库表
 */

public class BookModel extends BaseModel implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * 基础属性注解
     *  @Id :主键 Long型，可以通过@Id(autoincrement = true)设置自增长
     * @Property：设置一个非默认关系映射所对应的列名，默认是的使用字段名 举例：@Property (nameInDb="name")
     * @NotNul：设置数据库表当前列不能为空
     * @Transient ：添加次标记之后不会生成数据库表的列
     *
     * @Index：使用@Index作为一个属性来创建一个索引，通过name设置索引别名，也可以通过unique给索引添加约束
     *@Unique：向数据库列添加了一个唯一的约束
     */

    public Integer bid;
    public String uuid;
    public String bname;
    public String bauthor;
    public String bdesc;
    
    public Set<UserInfoModel> bUserSet = new HashSet<>();

    public Set<BookClassModel> bBookClassSet = new HashSet<>();


    public String bpath;
    public String filename;
}
