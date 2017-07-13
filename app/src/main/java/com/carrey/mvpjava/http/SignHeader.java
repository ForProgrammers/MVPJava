package com.carrey.mvpjava.http;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.carrey.mvpjava.BaseApp;
import com.carrey.mvpjava.util.SystemUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

import okhttp3.Headers;

/**
 * 作者： carrey
 * 时间 2017/7/12
 * desc:
 */

public class SignHeader {
    public static final String API_VERSION = "1.0";

    // 需要加入签名的header
    private final ArrayList<NameValuePair> signHeaders = new ArrayList<>();

    private String mRequestTime;
    private String mRequestId;


    public SignHeader(Headers.Builder headersBuilder) {
//        setDefaultPostType(TYPE_RAW_JSON);
        Context context = BaseApp.sApp;
//        ;BaseApp.getApp();
        headersBuilder.set("version", API_VERSION);
        signHeaders.add(new NameValuePair("version", API_VERSION));

        headersBuilder.set("app-version", SystemUtil.getVersionName(context));
        signHeaders.add(new NameValuePair("app-version", SystemUtil.getVersionName(context)));

        headersBuilder.set("device", SystemUtil.getIMEI(context));
        signHeaders.add(new NameValuePair("device", SystemUtil.getIMEI(context)));

        headersBuilder.set("screen-width", SystemUtil.getScreenWidth() + "");
        signHeaders.add(new NameValuePair("screen-width", SystemUtil.getScreenWidth() + ""));

        headersBuilder.set("screen-height", SystemUtil.getScreenHeight() + "");
        signHeaders.add(new NameValuePair("screen-height", SystemUtil.getScreenHeight() + ""));

        headersBuilder.set("platform", "1");
        signHeaders.add(new NameValuePair("platform", "1"));

        headersBuilder.set("os-version", SystemUtil.getOSVersion());
        signHeaders.add(new NameValuePair("os-version", SystemUtil.getOSVersion()));

        headersBuilder.set("model", SystemUtil.getDeviceModel());
        signHeaders.add(new NameValuePair("model", SystemUtil.getDeviceModel()));


        String token = "token";
        headersBuilder.set("access-token", token);
        signHeaders.add(new NameValuePair("access-token", token));

        ApplicationInfo appInfo = null;
        String channel;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
//            channel = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (Exception e) {
            channel = "channel";
            e.printStackTrace();
        }
//        headersBuilder.set("channel", channel);
//        signHeaders.add(new NameValuePair("channel", channel));

        mRequestId = UUID.randomUUID().toString();
        headersBuilder.set("request-id", mRequestId);

        mRequestTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").format(new Date(System.currentTimeMillis()));
        headersBuilder.set("client-request-time", mRequestTime);

//        if (!TextUtils.isEmpty(BaseResponse.sTimeDiff)) {
//            headersBuilder.set("time-diff", BaseResponse.sTimeDiff);
//        }

//        // 只放在头里面，不用来签名
//        addHeader("app-name", SchemeUtil.getHost(context));
    }

    public String sortHeaders(){
        // 拼接header
        Collections.sort(signHeaders, new Comparator<NameValuePair>() {
            @Override
            public int compare(NameValuePair t0, NameValuePair t1) {
                return t0.getName().compareTo(t1.getName());
            }
        });
        StringBuilder paramsSb = new StringBuilder();
        for (NameValuePair item : signHeaders) {
            String value = TextUtils.isEmpty(item.getValue()) ? "" : item.getValue();
            paramsSb.append(item.getName()).append("=").append(value).append("&");
        }
        if (paramsSb.toString().endsWith("&")) {
            paramsSb.deleteCharAt(paramsSb.length() - 1);
        }

        return paramsSb.toString();
    }


}
