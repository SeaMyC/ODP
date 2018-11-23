package com.odp.http;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : ChenHh
 * @version : 1.0.0
 * @since : 2018/10/16
 * Rx 线程调度
 */
public class RxSchedulerHelper {

    public static <T> ObservableTransformer<T, T> iOThreadScheduler() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    public static <T> ObservableTransformer<T, T> newThreadScheduler() {
        return observable -> observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.newThread());
    }

    public static <T> ObservableTransformer<T, T> mainThreadScheduler() {
        return observable -> observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread());
    }
}
