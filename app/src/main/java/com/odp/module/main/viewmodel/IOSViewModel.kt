package com.odp.module.main.viewmodel

import com.odp.base.GankTypeEnum

/**
 * @author  ChenHh
 * @time   2018/12/10 15:43
 * @des  ios的viewModel
 **/
class IOSViewModel : ODPViewModel() {

    override fun setType(): GankTypeEnum {

        return GankTypeEnum.IOS
    }
}