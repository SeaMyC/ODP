package com.odp.module.video

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.odp.R
import com.odp.base.BaseActivity
import com.odp.bean.GankIoDataList
import com.odp.databinding.ActivityVideoBinding
import com.odp.module.video.adapter.VideoAdapter
import com.odp.module.video.viewmodel.VideoViewModel

/**
 * @author  ChenHh
 * @time   2018/12/29 15:34
 * @des  video 页面
 **/
class VideoActivity : BaseActivity<ActivityVideoBinding>() {

    private var layoutManager: LinearLayoutManager = LinearLayoutManager(this)
    private var lastVisibleItem: Int = 0
    private var listAdapter: VideoAdapter = VideoAdapter()
    private lateinit var viewModel: VideoViewModel

    override fun getLayout(): Int {
        return R.layout.activity_video
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        viewModel.getVideoList(true)
        initView()
        viewModel.videoList.observe(this, Observer<GankIoDataList>{ bean ->
            if (bean.results != null && bean.results.size > 0) {
                if (viewModel.isRefresh) {
                    listAdapter.setAdapterData(bean.results)
                    binding.rflRefresh.isRefreshing = false
                } else {
                    val flag = bean.results.size > 0
                    listAdapter.updateList(if (flag) bean.results else null, flag)

                }
            }
        })

        binding.rvVideo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // 在newState为滑到底部时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //停止滚动的时候获取当前position
                    val seePosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    listAdapter.setVideoPlay(seePosition)


                    // 如果没有隐藏footView，那么最后一个条目的位置就比我们的getItemCount少1，自己可以算一下
                    if (!listAdapter.isFadeTips && lastVisibleItem + 1 == listAdapter.itemCount) {
                        viewModel.getVideoList(false)
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 在滑动完成后，拿到最后一个可见的item的位置
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            }
        })
    }

    private fun initView() {
        binding.rflRefresh.setColorSchemeResources(android.R.color.holo_green_light)
        binding.rflRefresh.setOnRefreshListener {
            binding.rflRefresh.isRefreshing = true
            listAdapter.resetDatas()// 重置adapter的数据源为空
            viewModel.getVideoList(true)
        }
        binding.rvVideo.layoutManager = layoutManager
        binding.rvVideo.itemAnimator = DefaultItemAnimator()
        binding.rvVideo.adapter = listAdapter

    }
}