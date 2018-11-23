package com.odp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author ChenHh
 * @time 2018/10/19 14:01
 * @des 获取时间和时间的转换工具类
 **/
public class TimeUtil {
    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long lt) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 获取当前时间戳
     */
    public static long getStamp() {
        return System.currentTimeMillis();
    }

    public static String getCurrentYYYYMMdd() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        Date date = new Date(getStamp());
        return simpleDateFormat.format(date);
    }

    /**
     * 正常日期格式
     *
     * @param yyyyMMdd
     * @return
     */
    public static boolean isValidYYYYMMdd(String yyyyMMdd) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        format.setLenient(false);
        try {
            return format.parse(yyyyMMdd) != null;
        } catch (ParseException e) {
            return false;
        }
    }


    public static boolean isFeatureYYYYMMdd(String yyyyMMdd) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        try {
            return format.parse(yyyyMMdd).after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static long pastToLong(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        try {
            return sdf.parse(dateTime).getTime();
        } catch (Exception e) {
            return 0;
        }
    }


}
