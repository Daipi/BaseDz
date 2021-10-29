package com.daipi.base.base;

import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * author:daijs
 * time:2021/10/21 10:21
 * details:
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mSparseArray;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mSparseArray = new SparseArray<>();
    }

    public <V extends View> V findViewById(int id) {
        View view = mSparseArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mSparseArray.put(id, view);
        }
        return (V) view;
    }
}