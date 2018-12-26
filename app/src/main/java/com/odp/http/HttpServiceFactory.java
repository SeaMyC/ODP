package com.odp.http;


import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * @author ChenHh
 * @time 2018/10/14 12:06
 **/
public enum HttpServiceFactory {
    INSTANCE;
    private Map<String, Object> map;

    public <T> T create(@NonNull Class<T> clazz, @NonNull String type) {
        if (map == null) {
            map = new HashMap<>();
        }
        String key = type + "_" + clazz.getSimpleName();
        T service = map.containsKey(key) ? (T) map.get(key) : null;
        if (service == null) {
            service = ODPRetrofitClient.INSTANCE.getBuilder(type).build().create(clazz);
            map.put(key, service);
        }

        return service;
    }
}
