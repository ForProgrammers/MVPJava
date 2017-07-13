package com.carrey.mvpjava.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.carrey.mvpjava.R;
import com.carrey.mvpjava.entity.HomeEntity;
import com.carrey.mvpjava.http.RetrofitConfig;
import com.carrey.mvpjava.http.RetrofitFactory;
import com.carrey.mvpjava.http.SignInterceptor;
import com.carrey.mvpjava.repository.HomeRepository;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitFactory.initFactory(new RetrofitConfig.Builder()
                .setBaseUrl("http://gank.io/api/")
                .setLogDebugger(true)
                .setSignInterceptor(new SignInterceptor())
                .build());

        final Map<String,String> map =new HashMap<>();
               map.put("uid","124567");
               map.put("uid","124567");
               map.put("name","value");

//        TextUtils.isEmpty()
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeRepository service = RetrofitFactory.INSTANCE.createService(HomeRepository.class);
                service.getHome(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<HomeEntity>() {
                            @Override
                            public void accept(HomeEntity homeEntity) throws Exception {

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                                throwable.printStackTrace();

                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {

                            }
                        });
            }
        });
    }
}
