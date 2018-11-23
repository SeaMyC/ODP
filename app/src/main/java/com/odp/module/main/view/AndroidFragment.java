package com.odp.module.main.view;

import android.os.Bundle;

import com.odp.R;
import com.odp.base.BaseFragment;
import com.odp.databinding.FragmentListBinding;
import com.odp.module.main.adapter.AndroidAdapter;
import com.odp.module.main.viewmodel.AndroidViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author ChenHh
 * @time 2018/11/2 17:40
 * @des android的页面
 **/
public class AndroidFragment extends BaseFragment<FragmentListBinding> {
    private static AndroidFragment androidFragment;
    private AndroidViewModel viewModel;
    private AndroidAdapter androidAdapter;
    private SwipeRefreshLayout refreshLayout;
    private int lastVisibleItem;
    private LinearLayoutManager layoutManager;

    public static AndroidFragment instance() {
        if (androidFragment == null) {
            androidFragment = new AndroidFragment();
        }
        return androidFragment;
    }


    @Override
    public int setContentView() {
        return R.layout.fragment_list;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(AndroidViewModel.class);
        viewModel.getGankList(true);
        initView();
        viewModel.gankIoDataList.observe(this, bean -> {
            if (bean.getResults() != null) {
                if (viewModel.isRefresh) {
                    androidAdapter.setAdapterData(bean.getResults());
                    refreshLayout.setRefreshing(false);
                } else {
                    boolean flag = bean.getResults().size() > 0;
                    androidAdapter.updateList(flag ? bean.getResults() : null, flag);
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
                    if (!androidAdapter.isFadeTips() && lastVisibleItem + 1 == androidAdapter.getItemCount()) {
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
            androidAdapter.resetDatas();// 重置adapter的数据源为空
            viewModel.getGankList(true);
        });

        layoutManager = new LinearLayoutManager(getActivity());
        binding.rvWeal.setLayoutManager(layoutManager);
        binding.rvWeal.setItemAnimator(new DefaultItemAnimator());

        androidAdapter = new AndroidAdapter();
        binding.rvWeal.setAdapter(androidAdapter);
    }

}
