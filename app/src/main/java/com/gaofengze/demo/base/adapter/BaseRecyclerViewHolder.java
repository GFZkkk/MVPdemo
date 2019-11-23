package com.gaofengze.demo.base.adapter;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

/**
 * Created by gfz on 2018/10/22
 */
public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public abstract void onBindViewHolder(T data, final int position);
}
