package com.odp.http;


import com.odp.base.Config;

/**
 * @author ChenHh
 * @time 2018/10/14 12:02
 * @des 网络请求类
 **/
public enum HttpServiceClient {
    INSTANCE;

    public GankIOService getGankIOService() {
        return HttpServiceFactory.INSTANCE.create(GankIOService.class, Config.HTTP_API_URL);
    }
}
