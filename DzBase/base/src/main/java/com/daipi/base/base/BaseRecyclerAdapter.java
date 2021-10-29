package com.daipi.base.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:daijs
 * time:2021/10/21 10:20
 * details:
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    private List<T> mList;
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    public BaseRecyclerAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = (list == null) ? new ArrayList<T>() : list;
        mInflater = LayoutInflater.from(context);
    }

    public List<T> getLists() {
        return mList;
    }

    public void add(int pos, T d) {
        mList.add(pos, d);
        notifyItemInserted(pos);
    }

    public void remove(int pos) {
        mList.remove(pos);
        notifyItemRemoved(pos);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(getLayoutId(viewType), parent, false);
        final BaseViewHolder holder = new BaseViewHolder(view);
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
        if (mItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mItemLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder, mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int pos);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }

    public abstract void bindData(BaseViewHolder holder, T item, int position);

    public abstract int getLayoutId(int viewType);

}
