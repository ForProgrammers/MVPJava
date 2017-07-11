package com.carrey.mvpjava.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public class RetrofitFactory {

    public static String BASE_URL = "http://gateway.marvel.com/";

    private Retrofit mRetrofit;

    public static final RetrofitFactory INSTANCE = new RetrofitFactory();

    private RetrofitFactory() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SignInterceptor())
                .addInterceptor(new HttpLoggingInterceptor())
//                .connectTimeout()
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T createService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}
