package com.example.gaofengze.demo.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by gfz on 2018/10/22
 */
public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(T data,final int position);
}
