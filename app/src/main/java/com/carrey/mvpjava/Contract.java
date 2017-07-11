package com.carrey.mvpjava;

import android.view.View;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public interface Contract {
    interface IPresenter {

        void onStart();

        void onStop();

        void onPause();

        void attachView(View v);

        void onCreate();
    }

    interface IView {
        void showProgress();

        void hideProgress();

    }
}
