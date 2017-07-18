package com.carrey.mvpjava.http;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.carrey.mvpjava.BaseApp;
import com.carrey.mvpjava.util.SystemUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import okhttp3.Headers;

/**
 * 作者： carrey
 * 时间 2017/7/12
 * desc:
 */

public class SignHeader implements SignHelper {
    public static final String API_VERSION = "1.0";
    @Override
    public ArrayList<NameValuePair> sortHeaders(Headers.Builder headersBuilder) {
        // 需要加入签名的header
         final ArrayList<NameValuePair> signHeaders = new ArrayList<>();
         String mRequestTime;
         String mRequestId;
//        setDefaultPostType(TYPE_RAW_JSON);
        Context context = BaseApp.sApp;
//        ;BaseApp.getApp();
        headersBuilder.set("version", API_VERSION);
        signHeaders.add(new BaseNameValuePair("version", API_VERSION));

        headersBuilder.set("app-version", SystemUtil.getVersionName(context));
        signHeaders.add(new BaseNameValuePair("app-version", SystemUtil.getVersionName(context)));

        headersBuilder.set("device", SystemUtil.getIMEI(context));
        signHeaders.add(new BaseNameValuePair("device", SystemUtil.getIMEI(context)));

        headersBuilder.set("screen-width", SystemUtil.getScreenWidth() + "");
        signHeaders.add(new BaseNameValuePair("screen-width", SystemUtil.getScreenWidth() + ""));

        headersBuilder.set("screen-height", SystemUtil.getScreenHeight() + "");
        signHeaders.add(new BaseNameValuePair("screen-height", SystemUtil.getScreenHeight() + ""));

        headersBuilder.set("platform", "1");
        signHeaders.add(new BaseNameValuePair("platform", "1"));

        headersBuilder.set("os-version", SystemUtil.getOSVersion());
        signHeaders.add(new BaseNameValuePair("os-version", SystemUtil.getOSVersion()));

        headersBuilder.set("model", SystemUtil.getDeviceModel());
        signHeaders.add(new BaseNameValuePair("model", SystemUtil.getDeviceModel()));


        String token = "token";
        headersBuilder.set("access-token", token);
        signHeaders.add(new BaseNameValuePair("access-token", token));

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
//        signHeaders.add(new BaseNameValuePair("channel", channel));

        mRequestId = UUID.randomUUID().toString();
        headersBuilder.set("request-id", mRequestId);

        mRequestTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").format(new Date(System.currentTimeMillis()));
        headersBuilder.set("client-request-time", mRequestTime);

        return signHeaders;
    }

    @Override
    public NameValuePair createNameValuePair(String name, String value) {
        return new BaseNameValuePair(name, value);
    }
}
