package com.odp.http;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ChenHh
 * @time 2018/10/14 12:16
 * @des 网络请求工具类
 **/
public enum ODPRetrofitClient {
    INSTANCE;

    public Retrofit.Builder getBuilder(String apiUrl) {
        return new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)).build());
    }

    /**
     *
     * @param apiUrl  url
     * @param client 自定义的client
     * @return builder
     */
    public Retrofit.Builder getBuilder(String apiUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client);
    }

}
