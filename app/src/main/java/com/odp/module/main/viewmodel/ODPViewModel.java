package com.odp.module.main.viewmodel;

import com.odp.base.GankTypeEnum;
import com.odp.http.GankIoDataList;
import com.odp.http.HttpServiceClient;
import com.odp.http.RxSchedulerHelper;
import com.orhanobut.logger.Logger;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable;

/**
 * @author ChenHh
 * @time 2018/11/2 16:41
 * @des 福利页面的viewModel
 **/
public abstract class ODPViewModel extends ViewModel {
    public MediatorLiveData<GankIoDataList> gankIoDataList = new MediatorLiveData<>();
    private Disposable wealDisposable;
    private static final int COUNT = 10;
    private static int prePage = 10;
    private static int page = 1;
    public boolean isRefresh = false;

    public ODPViewModel() {
        gankIoDataList.setValue(new GankIoDataList());
    }

    public void getGankList(boolean refresh) {

        isRefresh = refresh;
        if (refresh) {
            prePage = 10;
            page = 1;
        } else {
            prePage += COUNT;
            page += COUNT;
        }
        wealDisposable = HttpServiceClient.INSTANCE.getGankIOService()
                .getGankIoData(getDataType(), prePage, page)
                .compose(RxSchedulerHelper.iOThreadScheduler())
                .subscribe(data -> gankIoDataList.postValue(data),
                        e -> Logger.e(" message :%s", e.getMessage()));
    }

    private String getDataType() {
        return setType().getType();
    }

    protected abstract GankTypeEnum setType();

    @Override
    protected void onCleared() {
        super.onCleared();
        wealDisposable.dispose();
    }
}
