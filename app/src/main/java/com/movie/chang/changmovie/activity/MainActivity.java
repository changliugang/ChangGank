package com.movie.chang.changmovie.activity;

import android.os.Bundle;

import com.movie.chang.changmovie.R;
import com.movie.chang.changmovie.retrofit.RetrofitFactory;
import com.movie.chang.changmovie.retrofit.api.GankApi;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request();
    }

    private void request() {
        RetrofitFactory.getControllerSingleTonOperation(GankApi.class).getGankArticles("Android", 10, 1)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(page -> {

                },throwable -> {

                });
    }
}