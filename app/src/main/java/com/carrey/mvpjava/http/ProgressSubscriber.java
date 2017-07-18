package com.carrey.mvpjava.http;

import android.app.ProgressDialog;

import com.carrey.mvpjava.module.MainActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.ref.SoftReference;

/**
 * 作者： carrey
 * 时间 2017/7/14
 * desc:
 */

public class ProgressSubscriber<T> implements Subscriber<T> {

    /*是否弹框*/
    private boolean showPorgress = true;
    /* 软引用回调接口*/
    private SoftReference<String> mSubscriberOnNextListener;
    /*软引用反正内存泄露*/
    private SoftReference<MainActivity> mActivity;
    /*加载框可自己定义*/
    private ProgressDialog pd;
    /*请求数据*/
//    private BaseApi api;



    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
