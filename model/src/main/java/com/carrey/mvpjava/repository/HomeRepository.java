package com.carrey.mvpjava.repository;

import com.carrey.mvpjava.entity.HomeEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public interface HomeRepository {
    @GET("data/Android/10/1")
    Observable<HomeEntity> getHome();
}
