package com.odp.base;

import android.app.Application;

/**
 * @author ChenHh
 * @time 2018/11/23 16:48
 * @des 项目入口
 **/
public class ODPApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.init(this);
    }
}
