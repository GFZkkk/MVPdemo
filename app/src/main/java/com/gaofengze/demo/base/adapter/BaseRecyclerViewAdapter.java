package com.gaofengze.demo.base.adapter;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by gfz on 2018/10/19
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T>> implements View.OnClickListener {
    private List<T> list;
    protected OnItemClickListener listener;

    /**
     * 初始化数据列表
     */
    public BaseRecyclerViewAdapter(){
        this.list = new ArrayList<>();
    }

    /**
     * 给holder提供数据
     * 绑定点击事件
     */
    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder<T> holder, int position) {
        holder.onBindViewHolder(getData().get(position),position);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
    }
    /*    public BaseRecyclerViewHolder<T> createViewHolder(Class<BaseRecyclerViewHolder<T>> viewHolder,ViewGroup parent) {
        Class<?>[] params = {View.class};
        Constructor<?> constructor = viewHolder.getDeclaredConstructor(params);
        return constructor.newInstance(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_collect,parent,false));
    }*/

    /**
     *
     * @return 数据长度
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 添加单个数据
     */
    public void add(T data){
        if(data == null) return;
        list.add(data);
        /*notifyItemInserted(getItemCount());
        notifyItemChanged(getItemCount());*/
        notifyDataSetChanged();
    }



    /**
     * 添加数据列表
     */
    public void addAll(List<T> data){
        if(data == null) return;
        list.addAll(data);
        /*notifyItemRangeInserted(getItemCount()-data.size(),getItemCount());
        notifyItemRangeChanged(getItemCount()-data.size(),getItemCount());*/
        notifyDataSetChanged();
    }

    /**
     * 移除某个位置的数据
     */
    public void remove(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position);
//        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     */
    public void refresh(List<T> data){
        clear();
        if(data != null){
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear(){
        list.clear();
    }

    /**
     * @return 绑定的数据
     */
    public List<T> getData(){
        return list;
    }

    /**
     * 绑定点击事件
     * @param listener 点击事件接口
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 设置点击事件
     * @param v 点击的视图
     */
    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        click(v, position);
        if(listener != null){
            listener.onClick(v,position);
        }
    }

    protected void click(View v, int position){ }
}
