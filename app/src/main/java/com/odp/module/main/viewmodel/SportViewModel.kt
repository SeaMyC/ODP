package com.odp.module.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.odp.base.Config
import com.odp.bean.SportList
import com.odp.http.HttpServiceClient
import com.odp.http.RxSchedulerHelper
import com.orhanobut.logger.Logger
import io.reactivex.disposables.Disposable

/**
 * @author  ChenHh
 * @time   2018/12/12 10:35
 * @des  运动的viewModel
 **/
class SportViewModel : ViewModel() {
     val sportList: MutableLiveData<SportList> by lazy {
         MutableLiveData<SportList>()
    }
    private lateinit var disposable: Disposable

    fun getSportData() {
        disposable = HttpServiceClient.INSTANCE.sportService
                .getSportData("tiyu", Config.TT_KEY)
                .compose(RxSchedulerHelper.iOThreadScheduler())
                .subscribe({ data ->
                    sportList.postValue(data) }
                        , { e -> Logger.e(" message :%s", e.message) })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}