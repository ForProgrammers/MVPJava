package com.carrey.mvpjava.repository;

import com.carrey.mvpjava.entity.HomeEntity;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * 作者： carrey
 * 时间 2017/7/14
 * desc:
 */

public class HomeRepository implements HomeDataSource {

    private HomeDataSource mDataSource;

    public HomeRepository(HomeDataSource dataSource) {
        mDataSource = dataSource;
    }

    @Override
    public Observable<HomeEntity> postHome1(@Field("uid") String uid, @Field("name") String name) {
        return mDataSource.postHome1(uid,name).concatMap(new Function<HomeEntity, ObservableSource<? extends HomeEntity>>() {
            @Override
            public ObservableSource<? extends HomeEntity> apply(HomeEntity homeEntity) throws Exception {
                return null;
            }
        });
    }

    @Override
    public Observable<HomeEntity> postHome2(@FieldMap() Map<String, String> map) {
        return mDataSource.postHome2(map).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends HomeEntity>>() {
            @Override
            public ObservableSource<? extends HomeEntity> apply(Throwable throwable) throws Exception {
//                throwable.getc
                return null;
            }
        });
    }

    @Override
    public Observable<HomeEntity> postHome3(@Body() String uid) {
        return null;
    }

    @Override
    public Observable<HomeEntity> postHome4(@Body() Map<String, String> map) {
        return null;
    }

    @Override
    public Observable<HomeEntity> postHome5(@PartMap() Map<String, String> map) {
        return null;
    }

    @Override
    public Observable<HomeEntity> getHome(@Query("uid") String uid, @Query("name") String name) {
        return null;
    }
}
