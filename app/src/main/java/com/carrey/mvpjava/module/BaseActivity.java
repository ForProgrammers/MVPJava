package com.carrey.mvpjava.module;

import android.support.v7.app.AppCompatActivity;

import com.carrey.mvpjava.Contract;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public class BaseActivity extends AppCompatActivity implements Contract.IView {


    private Contract.IPresenter mPresenter;



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
