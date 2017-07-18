package com.carrey.mvpjava.repository;

import com.carrey.mvpjava.entity.HomeEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public interface HomeDataSource {

    @POST("data/Android/10/1")
    @FormUrlEncoded
    Observable<HomeEntity> postHome1(@Field("uid") String uid, @Field("name") String name);

    @POST("data/Android/10/1")
    @FormUrlEncoded
    Observable<HomeEntity> postHome2(@FieldMap() Map<String,String>  map);


    @POST("data/Android/10/1")
    Observable<HomeEntity> postHome3(@Body() String uid);

    @POST("data/Android/10/1")
    Observable<HomeEntity> postHome4(@Body() Map<String,String>  map);
    @POST("data/Android/10/1")
    @Multipart
    Observable<HomeEntity> postHome5(@PartMap() Map<String,String>  map);

    @GET("data/Android/10/1")
    Observable<HomeEntity> getHome(@Query("uid") String uid, @Query("name") String name);


}
