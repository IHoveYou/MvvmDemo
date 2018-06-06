package com.lxy.frame.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Tany on 2018/3/20.
 * Desc:万能RecyclerView适配器
 */


public abstract class BaseAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter {
    protected Context mContext;
    protected int mLayoutId;
    public List<T> mDatas;
    protected LayoutInflater mInflater;
    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    public BaseAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        if (context != null)
            mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        B mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), mLayoutId, parent, false);
        ViewHolder holder = new ViewHolder(mContext, mBinding.getRoot());
        setListener(parent, holder, viewType);
        return holder;
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    private void setListener(ViewGroup parent, final ViewHolder holder, int viewType) {
        if (!isEnabled(viewType)) return;
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, holder, position);
                }
            }
        });
        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    int position = holder.getAdapterPosition();
                    return mOnItemLongClickListener.onItemLongClick(v, holder, position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        B binding = DataBindingUtil.getBinding(holder.itemView);
        onBindItem((ViewHolder) holder, binding, mDatas.get(position), position);
    }

    protected abstract void onBindItem(ViewHolder holder, B binding, T t, int position);

    public List<T> getDatas() {
        return mDatas;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemClickListener) {
        this.mOnItemLongClickListener = onItemClickListener;
    }
}
