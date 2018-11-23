package com.odp.module.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.odp.R;
import com.odp.databinding.ItemFragementWealBinding;
import com.odp.http.GankIoDataBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author ChenHh
 * @time 2018/11/22 14:16
 * @des android 适配器
 **/
public class WealAdapter extends BaseAdapter {

    public WealAdapter(List<GankIoDataBean> beans) {
        datas = beans;
    }

    @Override
    public int getODPItemCount() {
        return datas.size();
    }

    @Override
    public RecyclerView.ViewHolder setNormalHolder(ViewGroup parent, int viewType) {
        return new NormalHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_fragement_weal, parent, false));
    }


    @Override
    public void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NormalHolder) holder).bindData(position);
    }

    class NormalHolder extends RecyclerView.ViewHolder {

        private final ItemFragementWealBinding itemBinding;

        public NormalHolder(@NonNull ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = (ItemFragementWealBinding) itemView;
        }

        public void bindData(int position) {
            Glide.with(itemBinding.getRoot().getContext())
                    .load(((GankIoDataBean) datas.get(position)).getUrl())
                    .placeholder(R.drawable.img_default_meizi)
                    .error(R.drawable.load_err)
                    .into(itemBinding.ivWeal);
        }
    }
}
