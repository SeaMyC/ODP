package com.odp.module.main.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.odp.R
import com.odp.base.BaseFragment
import com.odp.bean.GankIoDataList
import com.odp.databinding.FragmentIosBinding
import com.odp.module.main.adapter.ListAdapter
import com.odp.module.main.viewmodel.IOSViewModel
import com.odp.module.web.ODPWebActivity

/**
 * @author  ChenHh
 * @time   2018/12/10 15:19
 * @des  ios页面
 **/
class IOSFragment : BaseFragment<FragmentIosBinding>() {

    private var layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
    private var listAdapter: ListAdapter = ListAdapter()
    private var lastVisibleItem: Int = 0
    private lateinit var viewModel: IOSViewModel

    override fun setContentView(): Int {
        return R.layout.fragment_ios
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IOSViewModel::class.java)
        viewModel.getGankList(true)
        initView()

        viewModel.gankIoDataList.observe(this, Observer<GankIoDataList> { bean ->
            if (bean.results != null) {
                if (viewModel.isRefresh) {
                    listAdapter.setAdapterData(bean.results)
                    binding.rflRefresh.isRefreshing = false
                } else {
                    val flag = bean.results.size > 0
                    listAdapter.updateList(if (flag) bean.results else null, flag)
                }
            }
        })

        binding.rvWeal.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // 在newState为滑到底部时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 如果没有隐藏footView，那么最后一个条目的位置就比我们的getItemCount少1，自己可以算一下
                    if (!listAdapter.isFadeTips && lastVisibleItem + 1 == listAdapter.itemCount) {
                        viewModel.getGankList(false)
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 在滑动完成后，拿到最后一个可见的item的位置
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            }
        })

        listAdapter.setItemClickListener { bean -> startActivity(ODPWebActivity.Builder(activity).putWebUrl(bean.url)) }
    }

    private fun initView() {
        binding.rflRefresh.setColorSchemeResources(android.R.color.holo_green_light)
        binding.rflRefresh.setOnRefreshListener {
            binding.rflRefresh.isRefreshing = true
            listAdapter.resetDatas()// 重置adapter的数据源为空
            viewModel.getGankList(true)
        }
        binding.rvWeal.layoutManager = layoutManager
        binding.rvWeal.itemAnimator = DefaultItemAnimator()
        binding.rvWeal.adapter = listAdapter
    }
}