package com.movie.chang.changmovie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movie.chang.changmovie.config.Constant;
import com.movie.chang.changmovie.fragment.FragmentArticle;

/**
 * Created by changliugang on 2017/5/31 13:52
 * mail：changliugang@sina.com
 */
public class ArticlePageAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 3;

    public ArticlePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentArticle.newInstance(Constant.ANDROID);
            case 1:
                return FragmentArticle.newInstance(Constant.IOS);
            case 2:
                return FragmentArticle.newInstance(Constant.WALFARE);
            default:
                return FragmentArticle.newInstance(Constant.ANDROID);
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Android";
            case 1:
                return "iOS";
            case 2:
                return "福利";
            default:
                return "";
        }
    }

}
