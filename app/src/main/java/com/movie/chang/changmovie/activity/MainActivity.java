package com.movie.chang.changmovie.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.movie.chang.changmovie.R;
import com.movie.chang.changmovie.adapter.ArticlePageAdapter;
import com.movie.chang.changmovie.retrofit.RetrofitFactory;
import com.movie.chang.changmovie.retrofit.api.GankApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArticlePageAdapter adapter = new ArticlePageAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
//        request();
    }


}