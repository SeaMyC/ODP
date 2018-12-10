package com.odp.module.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.odp.R;
import com.odp.bean.GankIoDataBean;
import com.odp.databinding.ItemFragementWealBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author ChenHh
 * @time 2018/11/22 14:16
 * @des 福利 适配器
 **/
public class WealAdapter extends BaseAdapter {

    private IWealItemListener wealListener;

    public void setAdapterData(List<GankIoDataBean> beans) {
        datas.clear();
        datas = beans;
        notifyDataSetChanged();
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
            itemBinding.ivWeal.setOnClickListener(v -> wealListener.onItemListener(((GankIoDataBean) datas.get(position)).getUrl(),itemBinding.ivWeal));
        }
    }

    public void setItemClickListener(IWealItemListener listener) {
        this.wealListener = listener;
    }

    public interface IWealItemListener {
        void onItemListener(String str, View view);
    }
}
