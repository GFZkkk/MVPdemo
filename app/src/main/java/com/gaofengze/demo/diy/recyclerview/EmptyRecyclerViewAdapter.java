package com.gaofengze.demo.diy.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaofengze.demo.base.adapter.BaseRecyclerViewAdapter;
import com.gaofengze.demo.base.adapter.BaseRecyclerViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by gaofengze on 2019/3/26
 */
public abstract class EmptyRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T> {
    protected final int EMPTY = -1;
    @NonNull
    @Override
    public BaseRecyclerViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == EMPTY) return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(getEmptyLayoutId(),parent,false));
        else return onmCreateViewHolder(parent,viewType);
    }

    @Override
    public int getItemCount() {
        if(getData().size() == 0) return 1;
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if(getData().size() == 0) return EMPTY;
        else return getmItemViewType(position);
    }

    public int getmItemViewType(int position){
        return 0;
    }

    public abstract BaseRecyclerViewHolder<T> onmCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder<T> holder, int position) {
        if(getData().size()!=0) super.onBindViewHolder(holder, position);
    }

    public abstract int getEmptyLayoutId();

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = (GridLayoutManager) manager;
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == EMPTY ? gridManager.getSpanCount(): 1;
                }
            });
        }
    }

    class EmptyViewHolder extends BaseRecyclerViewHolder<T>{

        EmptyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindViewHolder(Object data, int position) {

        }
    }
}
