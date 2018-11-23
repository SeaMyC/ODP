package com.odp.module.main.viewmodel;

import com.odp.base.GankTypeEnum;

/**
 * @author ChenHh
 * @time 2018/11/2 16:42
 * @des android的viewModel
 **/
public class AndroidViewModel extends ODPViewModel {
    @Override
    protected GankTypeEnum setType() {
        return GankTypeEnum.ANDROID;
    }
}
