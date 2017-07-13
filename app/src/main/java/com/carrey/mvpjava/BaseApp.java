package com.carrey.mvpjava;

import android.app.Application;

/**
 * 作者： carrey
 * 时间 2017/7/13
 * desc:
 */

public class BaseApp extends Application {
    public static BaseApp sApp ;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp=this;
    }
}
