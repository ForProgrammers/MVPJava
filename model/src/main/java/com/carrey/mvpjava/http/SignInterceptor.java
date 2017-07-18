package com.carrey.mvpjava.http;


import com.carrey.mvpjava.util.StringUtil;

import java.io.EOFException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
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
import okio.Buffer;

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
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static final String TYPE_URL_ENCODE = "application/x-www-form-urlencoded";
    public static final String TYPE_RAW_JSON = "application/json";
    public static final String TYPE_MULTI_PART = "multipart/form-data";


    private static final String SIGN_HEAD = "开始标志";
    private static final String APP_SECRET = "密钥";
    private static final String APP_KEY = "公钥";

    public SignInterceptor(SignHelper signHelper) {
        mSignHelper = signHelper;
    }

    SignHelper mSignHelper;

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

        ArrayList<NameValuePair> sortHeaders = mSignHelper.sortHeaders(headersBuilder);

        // 拼接header
        sortNameValuePair(sortHeaders);
        StringBuilder paramsSb = new StringBuilder();
        for (NameValuePair item : sortHeaders) {
            String value = StringUtil.isEmpty(item.getValue()) ? "" : item.getValue();
            paramsSb.append(item.getName()).append("=").append(value).append("&");
        }
        if (paramsSb.toString().endsWith("&")) {
            paramsSb.deleteCharAt(paramsSb.length() - 1);
        }


        stringBuilder.append(paramsSb.toString()).append("+");

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
                    queryParams.add(mSignHelper.createNameValuePair(querykey[0], ""));
                } else if (querykey.length == 2) {
                    queryParams.add(mSignHelper.createNameValuePair(querykey[0], querykey[1]));
                }
            }

            sortNameValuePair(queryParams);
            for (NameValuePair item : queryParams) {
                if (!StringUtil.isEmpty(item.getValue())) {
                    querySB.append(item.getName()).append("=").append(item.getValue()).append("&");
                }
            }
            if (querySB.toString().endsWith("&")) {
                querySB.deleteCharAt(querySB.length() - 1);
            }
            stringBuilder.append(querySB.toString());
        }
        //拼接body
        RequestBody requestBody = oldRequest.body();
        boolean hasRequestBody = requestBody != null;


        if (hasRequestBody) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();

            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            if (TYPE_URL_ENCODE.equals(contentType.toString())) {

                String bodyString = buffer.readString(charset);

                List<NameValuePair> bodyParams = new ArrayList<>();
                StringBuilder bodySB = new StringBuilder();

                String[] bodys = bodyString.split("&");
                for (String body : bodys) {
                    String[] querykey = body.split("=");
                    if (querykey.length == 1) {
                        bodyParams.add(mSignHelper.createNameValuePair(querykey[0], ""));
                    } else if (querykey.length == 2) {
                        bodyParams.add(mSignHelper.createNameValuePair(querykey[0], querykey[1]));
                    }
                }

                sortNameValuePair(bodyParams);
                for (NameValuePair item : bodyParams) {
                    if (!StringUtil.isEmpty(item.getValue())) {
                        bodySB.append(item.getName()).append("=").append(item.getValue()).append("&");
                    }
                }
                if (bodySB.toString().endsWith("&")) {
                    bodySB.deleteCharAt(bodySB.length() - 1);
                }

                stringBuilder.append(bodySB.toString());

            } else {
                if (isPlaintext(buffer)) {
                    String body = buffer.readString(charset);
                    stringBuilder.append(body);
                    System.out.println("body-> " + body);
                } else {
                    System.out.println("body-> isPlaintext false");
                }
            }
        }

        // 拼接key
        stringBuilder.append("+");
        stringBuilder.append(APP_SECRET);
        System.out.println("stringBuilder" + stringBuilder.toString());

        requestBuild.headers(headersBuilder.build());

        return chain.proceed(requestBuild.build());
    }

    private void sortNameValuePair(List<NameValuePair> bodyParams) {
        Collections.sort(bodyParams, new Comparator<NameValuePair>() {
            @Override
            public int compare(NameValuePair o1, NameValuePair o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
