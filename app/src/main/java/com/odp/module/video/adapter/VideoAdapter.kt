package com.odp.module.video.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.odp.R
import com.odp.bean.GankIoDataBean
import com.odp.databinding.ItemVideoListBinding
import com.odp.module.main.adapter.BaseAdapter
import com.odp.module.main.inerface.IItemListener

/**
 * @author  ChenHh
 * @time   2018/12/29 16:40
 * @des  video适配器
 **/
class VideoAdapter : BaseAdapter<GankIoDataBean>() {
    private var listener: IItemListener? = null

    private var index: Int = 0

    fun setAdapterData(beans: List<GankIoDataBean>) {
        datas.clear()
        datas = beans
        notifyDataSetChanged()
    }

    fun setVideoPlay(position: Int) {
        index = position

    }

    override fun getODPItemCount(): Int {
        return datas.size
    }

    override fun setNormalHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NormalHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_video_list, parent, false))
    }


    override fun onBindNormalViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NormalHolder).bindData()
    }

    internal inner class NormalHolder(itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {

        private val itemBinding: ItemVideoListBinding = itemView as ItemVideoListBinding

        fun bindData() {
//            val bean = datas[position] as GankIoDataBean
            itemBinding.vvVideo.setVideoPath("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
//            itemBinding.vvVideo.setMediaController(MediaController(itemBinding.root.context))
//            itemBinding.vvVideo.pause()

            itemBinding.ivPlay.setOnClickListener {
                if (!itemBinding.vvVideo.isPlaying) {
                    itemBinding.vvVideo.start()
                    itemBinding.ivPlay.visibility = View.GONE
                }
            }
        }
    }

    fun setItemClickListener(listener: IItemListener) {
        this.listener = listener
    }
}
