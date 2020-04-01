package com.gaofengze.demo.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.gaofengze.demo.base.adapter.BaseRecyclerViewAdapter;
import com.gaofengze.demo.base.adapter.BaseRecyclerViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * 单一足布局和空布局设计
 * created by gfz on 2019-11-14
 */

public abstract class EmptyAndFooterAdapter<T> extends BaseRecyclerViewAdapter<T> {

    protected final int EMPTY = -1;
    protected final int FOOT = -2;

    public EmptyAndFooterAdapter() {
        super();
    }

    public EmptyAndFooterAdapter(List<T> list) {
        super(list);
    }

    public EmptyAndFooterAdapter(List<T> list, int clickIndex) {
        super(list, clickIndex);
        addItemType(EMPTY,getEmptyLayoutId());
        addItemType(FOOT,getFooterLayoutId());
    }

    @Override
    public BaseRecyclerViewHolder<T> getViewHolder(View view, int viewType) {
        switch (viewType){
            case EMPTY:return getEmptyViewHolder(view);
            case FOOT:return getFooterViewHolder(view);
            default:return getEFViewHolder(view, viewType);
        }
    }

    public abstract BaseRecyclerViewHolder<T> getEFViewHolder(View view, int viewType);

    @Override
    public int getItemCount() {
        if(isHaveEmpty() && getDataItemCount() == 0) return 1;
        if(isHaveFoot()) return getDataItemCount() + 1;
        return getDataItemCount();
    }

    /**
     * 获取数据长度
     */
    public int getDataItemCount(){
        return getData().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHaveEmpty() && getDataItemCount() == 0) return EMPTY;
        if (isHaveFoot() && getDataItemCount() == position) return FOOT;
        return 0;
    }

    public int getEmptyLayoutId(){
        return 0;
    }

    public int getFooterLayoutId(){
        return 0;
    }

    /**
     * 是否有足布局
     */
    protected boolean isHaveFoot(){
        return getFooterLayoutId() != 0;
    }

    /**
     * 是否有空布局
     */
    private boolean isHaveEmpty(){
        return getEmptyLayoutId() != 0;
    }

    /**
     * 是否是空布局
     */
    private boolean isEmptyView(int pos){
        return getItemViewType(pos) == EMPTY;
    }

    /**
     * 是否是足布局
     */
    private boolean isFootView(int pos){
        return getItemViewType(pos) == FOOT;
    }

    public FooterViewHolder getFooterViewHolder(View view){

        return new FooterViewHolder(view);
    }

    public EmptyViewHolder getEmptyViewHolder(View view){

        return new EmptyViewHolder(view);
    }

    /**
     * 如果是GridLayoutManager布局，空布局需要独占一行
     * 重写该方法时注意处理
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = (GridLayoutManager) manager;
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isEmptyView(position) || isFootView(position)){
                        return 1;
                    }
                    return gridManager.getSpanCount();
                }
            });
        }
    }

    //针对流式布局
    @Override
    public void onViewAttachedToWindow(BaseRecyclerViewHolder holder) {
        int layoutPosition = holder.getLayoutPosition();
        if (isEmptyView(layoutPosition) || isFootView(layoutPosition)){
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                    //占领全部空间;
                    params.setFullSpan(true);
                }
            }
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

    class FooterViewHolder extends BaseRecyclerViewHolder<T>{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindViewHolder(Object data, int position) {

        }
    }
}

