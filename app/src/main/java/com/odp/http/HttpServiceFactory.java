package com.odp.http;



import androidx.annotation.NonNull;

/**
 * @author ChenHh
 * @time 2018/10/14 12:06
 **/
public enum  HttpServiceFactory {
    INSTANCE;
    private Object gankHttps;

    public <T> T create(@NonNull Class<T> clazz, @NonNull String type) {
        if (gankHttps == null) {
            synchronized (HttpServiceFactory.class) {
                if (gankHttps == null) {
                    gankHttps = ODPRetrofitClient.INSTANCE.getBuilder(type).build().create(clazz);
                }
            }
        }
        return (T) gankHttps;
    }
}
