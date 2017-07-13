package com.carrey.mvpjava.http;

import android.text.TextUtils;

import com.carrey.mvpjava.util.StringUtil;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc: 拦截请求统一添加请求头 加密的等
 * 加密规则
 * ${开始标志!}+
 * ${请求id}+${时间戳}+
 * ${请求路径}+${请求头}+${请求参数}+
 * ${密钥}
 */

public class SignInterceptor implements Interceptor {

    public static final String TYPE_URL_ENCODE = "application/x-www-form-urlencoded";
    public static final String TYPE_RAW_JSON = "application/json";
    public static final String TYPE_MULTI_PART = "multipart/form-data";


    private static final String SIGN_HEAD = "开始标志";
    private static final String APP_SECRET = "密钥";
    private static final String APP_KEY = "公钥";

    private String mRequestTime;
    private String mRequestId;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");

    @Override
    public Response intercept(Chain chain) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SIGN_HEAD);
        mRequestId = UUID.randomUUID().toString();
        mRequestTime = sdf.format(new Date(System.currentTimeMillis()));

        stringBuilder.append("+");
        stringBuilder.append("request-id").append(mRequestId)
                .append("+").append("request-time").append(mRequestTime).append("+");

        Request oldRequest = chain.request();
        Request.Builder requestBuild = oldRequest.newBuilder();
        //请求路径和params
        HttpUrl oldHttpUrl = oldRequest.url();
        URI uri = oldHttpUrl.uri();
        String path = uri.getPath();
        if (!StringUtil.isEmpty(path)) {
            int index = path.indexOf("/api");//默认api 后面的为路径
            if (index > 0) {
                path = path.substring(index);
            }
            stringBuilder.append(path.toLowerCase());
        }
        stringBuilder.append("+");

        //请求头header
        Headers oldHeaders = oldRequest.headers();

        Headers.Builder headersBuilder = oldHeaders.newBuilder();
        SignHeader signHeader = new SignHeader(headersBuilder);
        String sortHeaders = signHeader.sortHeaders();

        stringBuilder.append(sortHeaders).append("+");

        // 拼接 query
//        System.out.println("path-> " + path);
        String rawQuery = uri.getRawQuery();

        List<NameValuePair> queryParams = new ArrayList<>();

        if (!StringUtil.isEmpty(rawQuery)) {
            StringBuilder querySB = new StringBuilder();

            String[] querys = rawQuery.split("&");
            for (String query : querys) {
                String[] querykey = query.split("=");
                if (querykey.length == 1) {
                    queryParams.add(new NameValuePair(querykey[0], ""));
                } else if (querykey.length == 2) {
                    queryParams.add(new NameValuePair(querykey[0], querykey[1]));
                }
            }

            Collections.sort(queryParams, new Comparator<NameValuePair>() {
                @Override
                public int compare(NameValuePair o1, NameValuePair o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            for (NameValuePair item : queryParams) {
                if (!TextUtils.isEmpty(item.getValue())) {
                    querySB.append(item.getName()).append("=").append(item.getValue()).append("&");
                }
            }
            if (querySB.toString().endsWith("&")) {
                querySB.deleteCharAt(querySB.length() - 1);
            }
            stringBuilder.append(querySB.toString());
        }
        //拼接body
        RequestBody body = oldRequest.body();
        String mediaType = body.contentType().type();
//        if (TYPE_MULTI_PART.equals(mediaType)){
//
//            oldRequest.body().n
//
//        }


        // 拼接key
        stringBuilder.append("+");
        stringBuilder.append(APP_SECRET);


//        System.out.println("stringBuilder-> " + stringBuilder.toString());
//        stringBuilder.toString() 加密
        headersBuilder.set("signature", stringBuilder.toString());

        requestBuild.headers(headersBuilder.build());

        return chain.proceed(requestBuild.build());
    }
}
