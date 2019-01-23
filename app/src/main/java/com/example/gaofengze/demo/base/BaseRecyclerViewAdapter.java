package com.example.gaofengze.demo.base;

import android.view.View;


import com.example.gaofengze.demo.callBack.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by gfz on 2018/10/19
 */
public abstract class BaseRecyclerViewAdapter<T,VH extends BaseRecyclerViewHolder<T>> extends RecyclerView.Adapter<VH> implements View.OnClickListener {
    private List<T> list;
    private OnItemClickListener listener;

    /**
     * 初始化数据列表
     */
    public BaseRecyclerViewAdapter(){
        this.list = new ArrayList<>();
    }

    /**
     * 给holder提供数据
     * 绑定点击事件
     * @param holder 绑定的viewholder
     * @param position 位置
     */
    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.onBindViewHolder(getData().get(position),position);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
    }

    /*@Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bill,parent,false));
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
     * @param data 新数据
     */
    public void add(T data){
        list.add(data);
        notifyItemInserted(getItemCount());
        notifyItemChanged(getItemCount());
    }

    /**
     * 添加数据列表
     * @param data 新数据list
     */
    public void addAll(List<T> data){
        list.addAll(data);
        notifyItemRangeInserted(getItemCount()-data.size(),getItemCount());
        notifyItemRangeChanged(getItemCount()-data.size(),getItemCount());
    }

    /**
     * 移除某个位置的数据
     * @param position 位置
     */
    public void remove(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position);
    }

    /**
     * 刷新数据
     * @param data
     */
    public void refresh(List<T> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
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
