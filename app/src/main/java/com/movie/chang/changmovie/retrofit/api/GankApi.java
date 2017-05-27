package com.movie.chang.changmovie.retrofit.api;

import com.movie.chang.changmovie.entity.GankArticle;
import com.movie.chang.changmovie.entity.Page;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 电影相关网络请求
 * Created by changliugang on 2017/5/27 09:24
 * mail：changliugang@sina.com
 */
public interface GankApi {


    /**
     * 获取文章集合
     *
     * @param type      数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * @param pagesize  请求个数： 数字，大于0
     * @param pageindex 第几页：数字，大于0
     * @return 文章集合
     */
    //http://gank.io/api/data/Android/10/1
    //http://gank.io/api/day/2015/08/06
    //http://gank.io/api/random/data/Android/20
    //http://gank.io/api/search/query/listview/category/Android/count/10/page/1
    @GET("data/{type}/{pagesize}/{pageindex}")
    Observable<Page<GankArticle>> getGankArticles(@Path("type") String type, @Path("pagesize") int pagesize, @Path("pageindex") int pageindex);


}
