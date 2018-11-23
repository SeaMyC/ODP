package com.odp.base;

import android.app.Application;
import android.content.Context;

import com.tencent.mmkv.MMKV;

/**
 * @author ChenHh
 * @time 2018/11/23 16:49
 * @des 项目入口工具类
 **/
public class AppUtil {
    private static Application context;

    public static void init(Context context) {
        AppUtil.context = (Application) context;
        initMMKV();
    }

    private static void initMMKV() {
        MMKV.initialize(context);
    }

}
