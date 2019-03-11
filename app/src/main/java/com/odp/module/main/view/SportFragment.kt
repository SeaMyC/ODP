package com.odp.module.main.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.odp.R
import com.odp.base.BaseFragment
import com.odp.bean.SportBean
import com.odp.bean.SportList
import com.odp.databinding.FragmentSportBinding
import com.odp.module.main.adapter.SportAdapter
import com.odp.module.main.inerface.ISportItemListener
import com.odp.module.main.viewmodel.SportViewModel
import com.odp.module.web.ODPWebActivity

/**
 * @author  ChenHh
 * @time   2018/12/12 10:29
 * @des  sportFragment
 **/
class SportFragment : BaseFragment<FragmentSportBinding>() {
    private lateinit var layoutManager: LinearLayoutManager
    private  var sportAdapter = SportAdapter()
    private lateinit var viewModel: SportViewModel

    override fun setContentView(): Int {
        return R.layout.fragment_sport
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SportViewModel::class.java)
        binding.setLifecycleOwner(this)
        viewModel.getSportData()
        initView()
        viewModel.sportList.observe(this, Observer<SportList> { bean ->
            sportAdapter.setData(bean.result.data)
            binding.rflRefresh.isRefreshing = false
        })

        sportAdapter.onItemOnclickListener(object : ISportItemListener {
            override fun OnItemClick(bean: SportBean) =
                    startActivity(ODPWebActivity.Builder(activity).putWebUrl(bean.url))
        })
    }

    private fun initView() {
        binding.rflRefresh.setColorSchemeResources(android.R.color.holo_green_light)
        binding.rflRefresh.setOnRefreshListener {
            binding.rflRefresh.isRefreshing = true
            viewModel.getSportData()
        }
        layoutManager = LinearLayoutManager(context)
        binding.rvSport.layoutManager = layoutManager
        binding.rvSport.itemAnimator = DefaultItemAnimator()
        binding.rvSport.adapter = sportAdapter
    }
}