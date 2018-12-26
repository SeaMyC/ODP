package com.odp.module.main.inerface

import com.odp.bean.SportBean

/**
 * @author  ChenHh
 * @time   2018/12/26 11:26
 * @des  运动的item监听
 **/
interface ISportItemListener {
    fun OnItemClick(bean: SportBean)
}