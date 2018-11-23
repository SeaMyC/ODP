package com.odp.http;

import android.os.Build;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ChenHh
 * @time 2018/11/16 14:48
 * @des 请求头拦截器
 **/
public final class ODPHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        final Request.Builder builder = request.newBuilder();
        builder.header("odp_version", "Android " + Build.VERSION.RELEASE)
                .header("client_version", "odp")
                .header("device_model", "odp")
                .header("device_id", "odp");

        return chain.proceed(builder.build());
    }
}
