package com.odp.module.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.odp.R;
import com.odp.databinding.LayoutFootviewBinding;
import com.odp.http.GankIoDataBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author ChenHh
 * @time 2018/11/16 15:36
 * @des odpAdapter
 **/
public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List datas = new ArrayList(); // 数据源
    private int normalType = 0;     // 第一种ViewType，正常的item
    private int footType = 1;       // 第二种ViewType，底部的提示View

    private boolean hasMore = true;   // 变量，是否有更多数据
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示
    private Context context;


    // 获取条目数量，之所以要加1是因为增加了一条footView

    @Override
    public int getItemCount() {
        return getODPItemCount() + 1;
    }

    // 根据条目位置返回ViewType，以供onCreateViewHolder方法内获取不同的Holder

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {//最后一个
            return footType;
        } else {
            return normalType;
        }
    }

    // // 底部footView的ViewHolder，用以缓存findView操作
    class FootHolder extends RecyclerView.ViewHolder {

        private final LayoutFootviewBinding itemBinding;
        private TextView tips;

        public FootHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = (LayoutFootviewBinding) itemView;
            tips = itemBinding.tips;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        // 根据返回的ViewType，绑定不同的布局文件，这里只有两种
        if (viewType == normalType) {
            return setNormalHolder(parent, viewType);
        } else {
            return new FootHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.layout_footview, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        // 如果是正常的imte，直接设置TextView的值
        if (holder instanceof FootHolder) {
            // 之所以要设置可见，是因为我在没有更多数据时会隐藏了这个footView
            ((FootHolder) holder).tips.setVisibility(View.VISIBLE);
            // 只有获取数据为空时，hasMore为false，所以当我们拉到底部时基本都会首先显示“正在加载更多...”
            if (hasMore) {
                // 不隐藏footView提示
                fadeTips = false;
                if (datas.size() > 0) {
                    ((FootHolder) holder).tips.setText("加载更多数据...");
                }
            } else {
                if (datas.size() > 0) {
                    Toast.makeText(context, "无更多数据了喔~", Toast.LENGTH_SHORT).show();
                    ((FootHolder) holder).tips.setText("无更多数据了喔~");
                    ((FootHolder) holder).tips.setVisibility(View.VISIBLE);
                    // 将fadeTips设置true
                    fadeTips = true;
                    // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
                    hasMore = true;
                }
            }
        } else {
            onBindNormalViewHolder(holder, position);
        }
    }

    public abstract int getODPItemCount();

    public abstract RecyclerView.ViewHolder setNormalHolder(ViewGroup parent, int viewType);

    public abstract void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position);

    // 暴露接口，改变fadeTips的方法
    public boolean isFadeTips() {
        return fadeTips;
    }


    // 暴露接口，下拉刷新时，通过暴露方法将数据源置为空
    public void resetDatas() {
        datas.clear();
        notifyDataSetChanged();
    }

    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void updateList(List<GankIoDataBean> newDatas, boolean hasMore) {
        // 在原有的数据之上增加新数据
        if (newDatas != null) {
            datas.addAll(newDatas);
        }
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (getItemViewType(position) == footType)
                            ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }
}