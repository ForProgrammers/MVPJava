package com.carrey.mvpjava.http;


import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public class RetrofitFactory {

//    private final String BASE_URL = "http://gank.io/api/";

    private Retrofit mRetrofit;

    public static final RetrofitFactory INSTANCE = new RetrofitFactory();


    private RetrofitConfig mConfig;

    public static void initFactory(RetrofitConfig config) {
        if (config == null) {
            throw new RuntimeException("RetrofitFactory need RetrofitConfig object");
        }
        INSTANCE.mConfig = config;
    }


    private RetrofitFactory() {
    }

    private void init() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        httpLoggingInterceptor.setLevel(mConfig.mLogDebugger ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.MINUTES))
                .addInterceptor(httpLoggingInterceptor);
        if (mConfig.mSignInterceptor != null) {
            builder.addInterceptor(mConfig.mSignInterceptor);
        }
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mConfig.mBaseUrl)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T createService(Class<T> clazz) {
        if (mRetrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofit == null) {
                    init();
                }
            }
        }

        return mRetrofit.create(clazz);
    }

}
