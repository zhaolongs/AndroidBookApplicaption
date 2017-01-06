package com.androidlongs.bookapplication.main.home.model;

import com.androidlongs.bookapplication.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by androidlongs on 17/1/7.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class BookClassResponeModel implements Serializable{


    /**
     * bookContentList : [{"bBookSet":[],"bcaddTime":"1483631861992","bcdesc":"小说可以一种很神奇的东西","bcid":19,"bclastUpdateTime":"1483631861992","bcname":"小说","bcuuid":"ba050d5f-4544-4404-a548-fab792bfd87f"},{"bBookSet":[{"bUserSet":[],"bauthor":"赵qq龙","bdesc":"基本显示文本控件 ","bid":27,"bname":"web","bpath":"/Users/androidlongs/Desktop/code/java/网上书店项目/projrect/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Book/html/2017-01-06/a7bc53df-dc29-4285-8053-beade19e60a8.jpg","bpname":"6c5f21b5-fc56-4161-b299-32a24c4ee38c.JPG","bppath":"/Users/androidlongs/Desktop/code/java/网上书店项目/projrect/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Book/images/2017-01-06/6c5f21b5-fc56-4161-b299-32a24c4ee38c.JPG","uuid":"3bbae344-9e80-4cab-a27d-72add47b40d2"}],"bcaddTime":"1483695814842","bcdesc":"科技领域 素材文章","bcid":21,"bclastUpdateTime":"1483695814842","bcname":"it","bcuuid":"7e5517b0-e129-47ad-858b-869a134d1065"},{"bBookSet":[],"bcaddTime":"1483720684514","bcdesc":"科技领域 素材文章","bcid":22,"bclastUpdateTime":"1483720684514","bcname":"it","bcuuid":"2a666b03-5c59-47f6-b2d2-ccfd7a57400f"},{"bBookSet":[],"bcaddTime":"1483720691025","bcdesc":"科技领域 素材文章","bcid":23,"bclastUpdateTime":"1483720691025","bcname":"it","bcuuid":"449c94cc-a5ac-4b58-9148-5f8cbfad96d9"},{"bBookSet":[],"bcaddTime":"1483720698424","bcdesc":"科技领域 素材文章","bcid":24,"bclastUpdateTime":"1483720698424","bcname":"it","bcuuid":"19280fd4-6f59-4720-bc14-93d8141f8f17"}]
     * code : 1000
     * content : null
     * contentList : [{"bBookSet":[],"bcaddTime":"1483631861992","bcdesc":"小说可以一种很神奇的东西","bcid":19,"bclastUpdateTime":"1483631861992","bcname":"小说","bcuuid":"ba050d5f-4544-4404-a548-fab792bfd87f"},{"bBookSet":[{"bUserSet":[],"bauthor":"赵qq龙","bdesc":"基本显示文本控件 ","bid":27,"bname":"web","bpath":"/Users/androidlongs/Desktop/code/java/网上书店项目/projrect/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Book/html/2017-01-06/a7bc53df-dc29-4285-8053-beade19e60a8.jpg","bpname":"6c5f21b5-fc56-4161-b299-32a24c4ee38c.JPG","bppath":"/Users/androidlongs/Desktop/code/java/网上书店项目/projrect/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Book/images/2017-01-06/6c5f21b5-fc56-4161-b299-32a24c4ee38c.JPG","uuid":"3bbae344-9e80-4cab-a27d-72add47b40d2"}],"bcaddTime":"1483695814842","bcdesc":"科技领域 素材文章","bcid":21,"bclastUpdateTime":"1483695814842","bcname":"it","bcuuid":"7e5517b0-e129-47ad-858b-869a134d1065"},{"bBookSet":[],"bcaddTime":"1483720684514","bcdesc":"科技领域 素材文章","bcid":22,"bclastUpdateTime":"1483720684514","bcname":"it","bcuuid":"2a666b03-5c59-47f6-b2d2-ccfd7a57400f"},{"bBookSet":[],"bcaddTime":"1483720691025","bcdesc":"科技领域 素材文章","bcid":23,"bclastUpdateTime":"1483720691025","bcname":"it","bcuuid":"449c94cc-a5ac-4b58-9148-5f8cbfad96d9"},{"bBookSet":[],"bcaddTime":"1483720698424","bcdesc":"科技领域 素材文章","bcid":24,"bclastUpdateTime":"1483720698424","bcname":"it","bcuuid":"19280fd4-6f59-4720-bc14-93d8141f8f17"}]
     * message : 添加成功
     */

    public String code;
    public Object content;
    public String message;
    public List<BaseModel> bookContentList;
    public List<BookClassModel> contentList;



}
