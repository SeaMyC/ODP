package com.odp.module.main.view;

import android.os.Bundle;

import com.odp.R;
import com.odp.base.BaseFragment;
import com.odp.databinding.FragmentWealBinding;
import com.odp.module.main.adapter.RecyclerItemDecoration;
import com.odp.module.main.adapter.WealAdapter;
import com.odp.module.main.viewmodel.WealViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author ChenHh
 * @time 2018/11/2 15:31
 * @des 福利页面
 **/
public class WealFragment extends BaseFragment<FragmentWealBinding> {

    private WealViewModel viewModel;
    private static WealFragment wealFragment;
    private SwipeRefreshLayout refreshLayout;
    private WealAdapter wealAdapter;
    private int lastVisibleItem;
    private GridLayoutManager layoutManager;

    public static WealFragment instance() {
        if (wealFragment == null) {
            wealFragment = new WealFragment();
        }
        return wealFragment;
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_weal;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(WealViewModel.class);
        viewModel.getGankList(true);
        initView();
        viewModel.gankIoDataList.observe(this, bean -> {
            if (bean.getResults() != null) {
                if (viewModel.isRefresh) {
                    wealAdapter = new WealAdapter(bean.getResults());
                    //                    wealAdapter = new wealAdapter(bean.getResults(), true);
                    binding.rvWeal.setAdapter(wealAdapter);
                    refreshLayout.setRefreshing(false);
                } else {
                    if (bean.getResults().size() > 0) {
                        wealAdapter.updateList(bean.getResults(), true);
                    } else {
                        wealAdapter.updateList(null, false);
                    }
                }
            }
        });


        binding.rvWeal.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 在newState为滑到底部时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 如果没有隐藏footView，那么最后一个条目的位置就比我们的getItemCount少1，自己可以算一下
                    if (!wealAdapter.isFadeTips() && lastVisibleItem + 1 == wealAdapter.getItemCount()) {
                        viewModel.getGankList(false);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 在滑动完成后，拿到最后一个可见的item的位置
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void initView() {
        refreshLayout = binding.rflRefresh;
        refreshLayout.setColorSchemeResources(android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(() -> {
            refreshLayout.setRefreshing(true);
            wealAdapter.resetDatas();// 重置adapter的数据源为空
            viewModel.getGankList(true);
        });

        layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rvWeal.addItemDecoration(new RecyclerItemDecoration(10, 2));
        binding.rvWeal.setLayoutManager(layoutManager);
        binding.rvWeal.setItemAnimator(new DefaultItemAnimator());
    }

}
