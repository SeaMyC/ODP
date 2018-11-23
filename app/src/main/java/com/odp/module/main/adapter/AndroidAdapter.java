package com.odp.module.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.odp.R;
import com.odp.bean.GankIoDataBean;
import com.odp.databinding.ItemFragementAndroidBinding;
import com.odp.util.storage.ODPStorageUtil;
import com.odp.util.storage.StorageCode;

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
public class AndroidAdapter extends BaseAdapter {

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
                R.layout.item_fragement_android, parent, false));
    }


    @Override
    public void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NormalHolder) holder).bindData(position);
    }

    class NormalHolder extends RecyclerView.ViewHolder {

        private final ItemFragementAndroidBinding itemBinding;

        public NormalHolder(@NonNull ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = (ItemFragementAndroidBinding) itemView;
        }

        public void bindData(int position) {
            Glide.with(itemBinding.getRoot().getContext())
                    .load(((GankIoDataBean) datas.get(position)).getImages() == null
                            ? ODPStorageUtil.getDefault().getString(StorageCode.Odp.ERROR_IMAGE_URL)
                            : ((GankIoDataBean) datas.get(position)).getImages().get(0))
                    .placeholder(R.drawable.img_default_meizi)
                    .error(R.drawable.load_err)
                    .into(itemBinding.ivWeal);
            itemBinding.setAndroidBean((GankIoDataBean) datas.get(position));
        }
    }
}
