package com.odp.module.main.adapter;

import com.odp.bean.GankIoDataList;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author ChenHh
 * @time 2018/11/3 9:44
 * @des GankBindingAdapter
 **/
public class GankBindingAdapter {

    /**
     *
     * @param recyclerView view
     * @param dataList 福利数据
     */
    @BindingAdapter("wealListAdapterData")
    public static void setWealListAdapterData(RecyclerView recyclerView, GankIoDataList dataList) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof WealDataBinDingAdapter) {
            ((WealDataBinDingAdapter) adapter).setData(dataList);
        }
    }
}
