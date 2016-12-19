package com.androidlongs.bookapplication.main.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androidlongs on 16/12/19.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class GsonUtil {
    // 将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    private static   Gson mGson = new Gson();
    // 将Json数组解析成相应的映射对象列表
    public static <T> List<T> parseJsonArrayWithGson(String jsonData,
                                                     final Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(jsonData).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }
}
