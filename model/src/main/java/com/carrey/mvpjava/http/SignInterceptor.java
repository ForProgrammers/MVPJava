package com.carrey.mvpjava.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public class SignInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();
        Request.Builder requestBuild = oldRequest.newBuilder();


        return chain.proceed(requestBuild.build());
    }
}
