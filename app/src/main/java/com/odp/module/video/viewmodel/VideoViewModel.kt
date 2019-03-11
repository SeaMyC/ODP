package com.odp.module.video.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.odp.bean.GankIoDataList
import com.odp.http.HttpServiceClient
import com.odp.http.RxSchedulerHelper
import com.orhanobut.logger.Logger
import io.reactivex.disposables.Disposable

/**
 * @author  ChenHh
 * @time   2018/12/29 15:42
 * @des  video viewModel
 **/
class VideoViewModel : ViewModel() {
    private var count: Int = 10
    var isRefresh: Boolean = true
    private var page: Int = 1
    private var disposable: Disposable? = null

    val videoList: MutableLiveData<GankIoDataList> by lazy {
        MutableLiveData<GankIoDataList>()
    }

    fun getVideoList(refresh: Boolean) {
        isRefresh = refresh
        if (refresh) {
            count = 10
            page = 1
        } else {
            count += count
            page += count
        }
        disposable = HttpServiceClient.INSTANCE
                .gankIOService.getVideoData(count, page)
                .compose(RxSchedulerHelper.iOThreadScheduler())
                .subscribe({ bean ->
                    videoList.postValue(bean)
                }, { error -> Logger.e(" message :%s", error.message) })


    }

    override fun onCleared() {
        super.onCleared()
        disposable!!.dispose()
    }
}