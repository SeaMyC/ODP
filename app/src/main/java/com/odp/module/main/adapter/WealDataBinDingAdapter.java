package com.odp.module.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.odp.R;
import com.odp.databinding.ItemFragementWealBinding;
import com.odp.http.GankIoDataBean;
import com.odp.http.GankIoDataList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author ChenHh
 * @time 2018/11/3 9:16
 * @des weal列表的适配器
 **/
public class WealDataBinDingAdapter extends RecyclerView.Adapter<WealDataBinDingAdapter.ViewHolder> {
    private List<GankIoDataBean> resultsBeans = new ArrayList<>();

    public void setData(GankIoDataList list) {
        resultsBeans.clear();
        if (list.getResults() == null) {
            notifyDataSetChanged();
            return;
        }
        List<GankIoDataBean> listResults = list.getResults();
        resultsBeans.addAll(listResults);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WealDataBinDingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_fragement_weal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WealDataBinDingAdapter.ViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemFragementWealBinding itemBinding;

        public ViewHolder(@NonNull ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = (ItemFragementWealBinding) itemView;
        }

        public void bindData(int position) {
            Glide.with(itemBinding.getRoot().getContext())
                    .load(resultsBeans.get(position).getUrl())
                    .placeholder(R.drawable.img_default_meizi)
                    .error(R.drawable.load_err)
                    .into(itemBinding.ivWeal);
        }
    }
}
