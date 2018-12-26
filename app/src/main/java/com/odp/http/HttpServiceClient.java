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
        return HttpServiceFactory.INSTANCE.create(GankIOService.class, Config.HTTP_GANK_API_URL);
    }

    public SportService getSportService() {
        return HttpServiceFactory.INSTANCE.create(SportService.class, Config.HTTP_TT_API_URL);
    }
}
