package com.odp.module.main.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odp.R
import com.odp.bean.SportBean
import com.odp.databinding.ItemFragementSportBinding
import com.odp.databinding.ItemFragementSportThreeBinding
import com.odp.module.main.inerface.ISportItemListener

/**
 * @author  ChenHh
 * @time   2018/12/12 14:21
 * @des  运动适配器
 **/
class SportAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var listener: ISportItemListener

    var sportList = arrayListOf<SportBean>()

    private val ONE_IMAGE: Int = 0

    private val THREE_IMAGE: Int = 1

    fun setData(list: List<SportBean>) {
        if (sportList.size > 0) {
            sportList.clear()
        }
        sportList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            sportList[position].thumbnail_pic_s02.isEmpty() -> ONE_IMAGE
            else -> THREE_IMAGE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SportAdapter.SportHolder -> holder.bindData(position)
            is SportAdapter.ThreeImageHolder -> holder.bindImageData(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ONE_IMAGE -> SportHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                    R.layout.item_fragement_sport, parent, false))
            else -> ThreeImageHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                    R.layout.item_fragement_sport_three, parent, false))
        }
    }

    override fun getItemCount(): Int = sportList.size

    fun onItemOnclickListener(listener: ISportItemListener) {
        this.listener = listener
    }

    inner class SportHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        val binding = binding as ItemFragementSportBinding

        fun bindData(position: Int) {
            binding.viewModel = sportList[position]
            Glide.with(binding.root.context)
                    .load(sportList[position].thumbnail_pic_s)
                    .placeholder(R.drawable.img_default_meizi)
                    .error(R.drawable.load_err)
                    .into(binding.ivBg)
            binding.cdItem.setOnClickListener { listener.OnItemClick(sportList[position]) }
        }
    }

    inner class ThreeImageHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = binding as ItemFragementSportThreeBinding

        fun bindImageData(position: Int) {
            binding.viewModel = sportList[position]
            binding.cdItem.setOnClickListener { listener.OnItemClick(sportList[position]) }
            Glide.with(binding.root.context)
                    .load(sportList[position].thumbnail_pic_s)
                    .placeholder(R.drawable.img_default_meizi)
                    .error(R.drawable.load_err)
                    .into(binding.ivBg)

            Glide.with(binding.root.context)
                    .load(sportList[position].thumbnail_pic_s02)
                    .placeholder(R.drawable.img_default_meizi)
                    .error(R.drawable.load_err)
                    .into(binding.ivBgTwo)

            Glide.with(binding.root.context)
                    .load(sportList[position].thumbnail_pic_s03)
                    .placeholder(R.drawable.img_default_meizi)
                    .error(R.drawable.load_err)
                    .into(binding.ivBgThree)
        }
    }
}

