package com.carrey.mvpjava.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc: 拦截请求统一添加请求头 加密的等
 * 加密规则
 * ${开始标志!}\n
 * ${请求id}@${时间戳}\n
 * ${请求路径}+${请求头}+${请求参数}\n
 * ${密钥}
 */

public class SignInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();
        Request.Builder requestBuild = oldRequest.newBuilder();

        System.out.printf("SignInterceptor");


        return chain.proceed(requestBuild.build());
    }
}
