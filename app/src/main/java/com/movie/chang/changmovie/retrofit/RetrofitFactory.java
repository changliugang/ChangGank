package com.movie.chang.changmovie.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.movie.chang.changmovie.BuildConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit配置
 * Created by chang on 2016/7/18.
 */
public class RetrofitFactory {


    private static Retrofit retrofit;//Retrofit核心配置
    // 配置Gson
    final static Gson gson = new GsonBuilder().serializeNulls().create();
    // okhttp3配置
    private static final OkHttpClient.Builder builder = new OkHttpClient.Builder();
    public static String OPERATION_PLATFORM_BASE_URL = "http://gank.io/api/";


    private static Map<Class<?>, Object> controller = new HashMap<>();


    RetrofitFactory(String baseUrl) {
        // 超时配置
        OkHttpClient httpClient = builder.connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();
        // Debug模式输出网络相关log
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }
        // 配置Retrofit
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    private <T> T createAPI(Class<T> cls) {
        return retrofit.create(cls);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getControllerSingleTonOperation(Class<T> controllerInterface) {
        return getControllerSingleTon(controllerInterface, OPERATION_PLATFORM_BASE_URL);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getControllerSingleTon(Class<T> controllerInterface, String baseUrl) {
        T t = (T) controller.get(controllerInterface);
        if (t == null) {
            synchronized (RetrofitFactory.class) {
                t = new RetrofitFactory(baseUrl).createAPI(controllerInterface);
                return t;
            }
        } else return t;
    }



}
