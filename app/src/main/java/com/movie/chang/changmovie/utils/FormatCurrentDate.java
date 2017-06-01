package com.movie.chang.changmovie.utils;

import android.content.Context;
import android.text.format.DateUtils;

import java.util.Date;

/**
 * Created by changliugang on 2017/6/1 15:44
 * mail：changliugang@sina.com
 */
public class FormatCurrentDate {

    /**
     * 设置每个阶段时间
     */
    private static final int seconds_of_1minute = 60;

    private static final int seconds_of_30minutes = 30 * 60;

    private static final int seconds_of_1hour = 60 * 60;

    private static final int seconds_of_1day = 24 * 60 * 60;

    /**
     * 格式化时间
     *
     * @param date
     * @return
     */
    public static String getTimeRange(Context context, Date date) {
        /**获取当前时间*/
//        Date curDate = new Date(System.currentTimeMillis());
        long cur = System.currentTimeMillis();
        long pu = date.getTime();
        /**除以1000是为了转换成秒*/
        long between = (cur - pu) / 1000;
        int elapsedTime = (int) (between);
        if (elapsedTime < seconds_of_1minute) {

            return "刚刚";
        }
        if (elapsedTime < seconds_of_30minutes) {

            return elapsedTime / seconds_of_1minute + "分钟前";
        }
        if (elapsedTime < seconds_of_1hour) {

            return "半小时前";
        }
        if (elapsedTime < seconds_of_1day) {

            return elapsedTime / seconds_of_1hour + "小时前";
        }
        return DateUtils.getRelativeTimeSpanString(context, date.getTime()).toString();
    }

}
