package com.movie.chang.changmovie;

import android.app.Application;

import com.changlg.cn.tapechat.log.Loglg;

/**
 * 全局应用
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        // 分包的配置，因为加了环信的UI
//        try {
//            MultiDex.install(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        super.onCreate();
        // app崩溃信息收集 报错信息保存在 sd根目录/crashLog 下
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);
        // 极光推送设置debug模式
        //JPushInterface.setDebugMode(BuildConfig.DEBUG);
        // 极光推送初始化
        //JPushInterface.init(this);
        // log文件输出设置
        Loglg.init(BuildConfig.DEBUG);
        // 初始化环信
//        initEasemob();
        //开发时设置为true，发布时设置为false
        //CrashReport.initCrashReport(getApplicationContext(), "553a56dd28", true);
//        initGalleryFinal();
//        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        // 分包的配置，因为加了环信的UI
//        MultiDex.install(this);
//    }

    /**
     * 初始化sdk的一些操作，封装在 MLEasemobHelper 类中
     */
//    private void initEasemob() {
//        EasemobHelper.getInstance().initEasemob(context);
//    }


//    private void initGalleryFinal() {
//        cn.finalteam.galleryfinal.ImageLoader imageLoader = new GlideImageLoader();
//
//        //设置主题
//        //ThemeConfig.CYAN
//        ThemeConfig theme = new ThemeConfig.Builder()
//                .setTitleBarBgColor(getResources().getColor(R.color.green_one))//标题栏背景颜色
////                .setTitleBarTextColor(Color.BLACK)//标题栏文本字体颜色
////                .setTitleBarIconColor(Color.BLACK)
//                .setFabNornalColor(getResources().getColor(R.color.green_one))//设置Floating按钮Nornal状态颜色
//                .setFabPressedColor(getResources().getColor(R.color.green_two))//设置Floating按钮Pressed状态颜色
////                .setCheckNornalColor(Color.WHITE)//选择框未选颜色
//                .setCheckSelectedColor(getResources().getColor(R.color.green_one))//选择框选中颜色
//                .build();
//
//        //配置功能
//        FunctionConfig functionConfig = new FunctionConfig.Builder()
//                .setEnableCamera(true)
//                .setEnableEdit(true)
//                .setEnableCrop(true)
//                .setEnableRotate(true)
//                .setCropSquare(true)
//                .setEnablePreview(true)
//                .build();
//
//        CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, theme)
//                .setFunctionConfig(functionConfig)
////                .setPauseOnScrollListener(pauseOnScrollListener)
//                .build();
//        GalleryFinal.init(coreConfig);
//
//    }
}
