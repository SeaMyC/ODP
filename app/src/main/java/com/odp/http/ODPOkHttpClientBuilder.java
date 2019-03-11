package com.odp.http;

import com.odp.base.Config;

import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author ChenHh
 * @time 2018/11/16 14:44
 * @des okHttp client
 **/
public enum ODPOkHttpClientBuilder {
    INSTANCE;

    public OkHttpClient buildDefault() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(CookieManager.getDefault()))
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .connectTimeout(4000, TimeUnit.MILLISECONDS)
                .addInterceptor(new ODPHeaderInterceptor());
        if (Config.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }
}
