package com.odp.module.main.view;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.view.View;

import com.odp.R;
import com.odp.base.BaseFragment;
import com.odp.databinding.FragmentListBinding;
import com.odp.module.image.LoadImageActivity;
import com.odp.module.main.adapter.RecyclerItemDecoration;
import com.odp.module.main.adapter.WealAdapter;
import com.odp.module.main.viewmodel.WealViewModel;
import com.odp.util.storage.ODPStorageUtil;
import com.odp.util.storage.StorageCode;

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
public class WealFragment extends BaseFragment<FragmentListBinding> {

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
        return R.layout.fragment_list;
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
                    //存起来 用于其他页面图片无资源时占位
                    ODPStorageUtil.getDefault().push(StorageCode.Odp.ERROR_IMAGE_URL, bean.getResults().get(0).getUrl());
                    wealAdapter.setAdapterData(bean.getResults());
                    refreshLayout.setRefreshing(false);
                } else {
                    boolean flag = bean.getResults().size() > 0;
                    wealAdapter.updateList(flag ? bean.getResults() : null, flag);
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

        wealAdapter = new WealAdapter();
        binding.rvWeal.setAdapter(wealAdapter);

        wealAdapter.setItemClickListener(this::startImageActivity);
    }

    private void startImageActivity(String url, View view) {
        startActivity(new LoadImageActivity.Builder(getActivity()).putWebUrl(url),
                ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, "girl_image").toBundle());

    }

}
