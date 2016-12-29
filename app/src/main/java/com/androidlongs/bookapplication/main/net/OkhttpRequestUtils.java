package com.androidlongs.bookapplication.main.net;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.Map;


/**
 * Created by androidlongs on 16/12/19.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class OkhttpRequestUtils {


    private OkHttpClient mOkHttpClient;

    private OkhttpRequestUtils() {
        initFunction();
    }

    private static class SingleOkhttp {
        private static OkhttpRequestUtils sOkhttpRequestUtils = new OkhttpRequestUtils();
    }

    public static OkhttpRequestUtils getInstance() {
        return SingleOkhttp.sOkhttpRequestUtils;
    }


    private void initFunction() {
        mOkHttpClient = new OkHttpClient();
    }


    /**
     * Get请求数据
     *  @param url      链接
     * @param callback 回调
     */
    public Call getRequest(String url, Callback callback) {
        OkHttpClient httpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();

        Request request = builder.get().url(url).build();

        Call call = httpClient.newCall(request);

        call.enqueue(callback);
        return call;
    }

    /**
     * Post上传普通数据
     *  @param url      链接
     * @param keyMap   所要上传的参数与值
     * @param callback 回调
     */
    public Call postRequest(String url, Map<String, String> keyMap, Callback callback) {

        //构建数据体
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        //封装参数
        if (keyMap != null && !keyMap.isEmpty()) {
            for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                formEncodingBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        //获取 RequestBody
        RequestBody requestBody = formEncodingBuilder.build();

        //获取Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).post(requestBody).build();
        //构建Call
        Call call = mOkHttpClient.newCall(request);
        //call.cancel();
        //添加到队列 中
        call.enqueue(callback);
        return call;
    }

    /**
     * 上传String 数据
     * 例如上传的 JSON数据
     *
     * @param url      链接
     * @param data     所要上传String
     * @param callback 回调
     */
    public void postRequest(String url, String data, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), data);
        //获取Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).post(requestBody).build();
        //构建Call
        Call call = mOkHttpClient.newCall(request);
        call.cancel();
        //添加到队列 中
        call.enqueue(callback);
    }

    /**
     * 上传文件
     *
     * @param url      链接
     * @param file     所要上传的文件
     * @param callback 回调
     */
    public void postRequest(String url, File file, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //获取Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).post(requestBody).build();
        //构建Call
        Call call = mOkHttpClient.newCall(request);
        call.cancel();
        //添加到队列 中
        call.enqueue(callback);
    }

    /**
     * 上传表单数据
     * 表单数据 中包括普通数据 与文件数据
     *
     * @param keyMap          参数列表
     * @param fileName        上传文件 被指定保存的文件名对应的Key
     * @param serviceFileName 上传的文件 保存在服务器的名字
     * @param file            上传的文件
     * @param callback        回调
     */
    public void postRequest(String url,
                            Map<String, String> keyMap,
                            String fileName,
                            String serviceFileName,
                            File file,
                            Callback callback) {

        MultipartBuilder multipartBuilder = new MultipartBuilder();

        MultipartBuilder builder = multipartBuilder.type(MultipartBuilder.FORM);


        //封装参数
        if (keyMap != null) {
            for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        RequestBody requestBody = builder.addFormDataPart(fileName, serviceFileName, RequestBody.create(MediaType.parse("application/octet-stream"), file)).build();
        //获取Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.url(url).post(requestBody).build();
        //构建Call
        Call call = mOkHttpClient.newCall(request);
        call.cancel();
        //添加到队列 中
        call.enqueue(callback);
    }
}














