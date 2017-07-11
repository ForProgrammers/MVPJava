//package com.carrey.mvpjava.repository.http;
//
//import com.carrey.mvpjava.entity.HomeEntity;
//import com.carrey.mvpjava.http.RetrofitFactory;
//import com.carrey.mvpjava.repository.HomeRepository;
//
//import io.reactivex.Observable;
//
///**
// * 作者： carrey
// * 时间 2017/7/11
// * desc:
// */
//
//public class HomeHttpRepository implements HomeRepository {
//
//    HomeRepository service;
//
//    public HomeHttpRepository() {
//
//
//        service = RetrofitFactory.INSTANCE.createService(HomeRepository.class);
//
//    }
//
//    @Override
//    public Observable<HomeEntity> getHome() {
//        return service.getHome();
//    }
//}
