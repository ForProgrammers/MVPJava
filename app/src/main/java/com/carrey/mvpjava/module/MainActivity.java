package com.carrey.mvpjava.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.carrey.mvpjava.R;
import com.carrey.mvpjava.http.RetrofitFactory;
import com.carrey.mvpjava.repository.HomeRepository;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeRepository service = RetrofitFactory.INSTANCE.createService(HomeRepository.class);
        service.getHome().subscribe();
    }
}
