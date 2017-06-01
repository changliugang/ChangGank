package com.movie.chang.changmovie.config;

import android.support.annotation.IntDef;

/**
 * Created by changliugang on 2017/5/31 14:44
 * mail：changliugang@sina.com
 */
public class Constant {

    public static final int ANDROID = 1;
    public static final int IOS = 2;
    public static final int WALFARE = 3;

    // 自定义一个注解ArticleType
    @IntDef({ANDROID, IOS, WALFARE})
    public @interface ArticleType {
    }


}
