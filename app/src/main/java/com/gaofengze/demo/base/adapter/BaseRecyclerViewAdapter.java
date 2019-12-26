package com.gaofengze.demo.base.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaofengze.demo.data.App;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by gfz on 2018/10/19
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T>> implements View.OnClickListener {

    private int clickIndex;
    private List<T> list;
    private Context context;
    protected OnItemClickListener listener;
    private boolean needAutoRefreshClickItem;
    private boolean needAutoSetClickIndex;

    /**
     * 初始化数据列表
     */
    protected BaseRecyclerViewAdapter(){
        this(null);
    }

    protected BaseRecyclerViewAdapter(List<T> list){
        this(list, -1);
    }

    protected BaseRecyclerViewAdapter(List<T> list, int clickIndex){
        if (list == null){
            list = new ArrayList<>();
        }
        this.list = list;
        this.clickIndex = clickIndex;
        needAutoRefreshClickItem = false;
        needAutoSetClickIndex = true;
    }

    /**
     * 给holder提供数据
     * 绑定点击事件
     */
    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder<T> holder, int position) {
        holder.onBindViewHolder(getData(position),holder.getAdapterPosition());
        setListener(holder.itemView,holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 得到当前点击的itemIndex
     */
    public int getClickIndex() {
        return clickIndex;
    }

    /**
     * 主动设置选中的itemIndex
     */
    public void setClickIndex(int clickIndex) {
        int preClickIndex = this.clickIndex;
        this.clickIndex = clickIndex;
        if (needAutoRefreshClickItem && preClickIndex != clickIndex){
            notifyItemChanged(preClickIndex);
            notifyItemChanged(clickIndex);
        }
    }

    /**
     * @return 绑定的数据
     */
    public List<T> getData(){
        return list;
    }

    /**
     * @return 绑定的数据
     */
    public T getData(int position){
        if (isIndex(position)){
            return list.get(position);
        }
        return null;
    }

    /**
     * 主动设置context
     */
    public void setContext(Context context){
        this.context = context;
    }

    /**
     * 绑定点击事件
     * @param listener 点击事件接口
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 设置是否自动刷新点击的click，默认关闭
     */
    public void setNeedAutoRefreshClickItem(boolean needAutoRefreshClickItem) {
        this.needAutoRefreshClickItem = needAutoRefreshClickItem;
    }

    /**
     * 设置是否自动设置选中的item为当前点击的item，默认开启
     */
    public void setNeedAutoSetClickIndex(boolean needAutoSetClickIndex) {
        this.needAutoSetClickIndex = needAutoSetClickIndex;
    }

    /**
     * 设置点击事件
     * @param v 点击的视图
     */
    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        if(!fastClick(getClickDoubleTime()) && !click(v, position)){
            if (needAutoSetClickIndex){
                setClickIndex(position);
            }
            if(listener != null){
                listener.onClick(v,position);
            }
        }

    }

    /**
     * 添加单个数据
     */
    public void addData(T data){
        if(data == null) return;
        list.add(data);
    }

    /**
     * 添加数据列表
     */
    public void addAllData(List<T> dataList){
        if (dataList == null) return;
        for (T data : dataList){
            addData(data);
        }
    }

    public void setData(List<T> data){
        if (data != null){
            list = data;
        }
    }

    /**
     * 设置某个位置的数据
     */
    public void setData(int position, T data){
        if (data != null){
            if (isIndex(position)){
                list.set(position, data);
            }
        }else{
            removeData(position);
        }
    }

    /**
     * 设置list
     */
    public void setDataList(List<T> data){
        clear();
        addAllData(data);
    }

    /**
     * 移除某个位置的数据
     */
    public void removeData(int position){
        if (isIndex(position)){
            list.remove(position);
        }
    }

    /**
     * 移除某个数据
     */
    public int removeData(T data){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == data){
                remove(i);
                return i;
            }
        }
        return -1;
    }

    /**
     * 刷新添加某个数据后的视图
     */
    public void add(T data){
        addData(data);
        notifyItemInserted(getItemCount());
    }

    /**
     * 刷新添加数据列表后的视图
     */
    public void addAll(List<T> data){
        addAllData(data);
        notifyItemRangeInserted(getItemCount() - data.size(), data.size());
    }

    /**
     * 刷新移除某个位置的数据后的视图
     */
    public void remove(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 刷新全部数据
     */
    public void refresh(List<T> data){
        setDataList(data);
        notifyDataSetChanged();
    }

    /**
     * 刷新某个数据
     */
    public void replace(int position, T data){
        setData(position, data);
        notifyItemChanged(position);
    }

    /**
     * 清空数据
     */
    public void clear(){
        list.clear();
    }

    /**
     * 获取创建viewHolder时的view
     * 顺便取一下context
     */
    protected View getView(ViewGroup viewGroup, int layout){
        if (context == null){
            context = viewGroup.getContext();
        }
        return LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);
    }

    /**
     * 获取一个可用的context
     */
    protected Context getContext(){
        return context != null ? context : getAppContext();
    }

    /**
     * 设置控件显隐
     */
    protected int setDisplay(boolean show){
        return show ? View.VISIBLE : View.GONE;
    }

    /**
     * 某个view是否显示
     */
    protected boolean isDisplay(View view){
        return view.getVisibility() == View.VISIBLE;
    }

    /**
     * 设置item中控件的点击事件
     */
    protected void setListener(View view, int position){
        if (position > -1 && position < getItemCount()){
            view.setTag(position);
            view.setOnClickListener(this);
        }
    }

    protected boolean isIndex(int position){
        return position > -1 && position < list.size();
    }

    /**
     * item点击间隔
     */
    protected int getClickDoubleTime(){
        return 300;
    }

    /**
     * 拿一个全局context用来加载资源
     */
    private Context getAppContext(){
        return App.getmContext();
    }

    /**
     * 根据资源id获取颜色
     */
    protected int getColor(int resId){
        return getContext().getResources().getColor(resId);
    }

    /**
     * 根据资源id获取图片
     */
    protected Drawable getDrawable(int resId){
        return getContext().getResources().getDrawable(resId);
    }

    /**
     * 根据资源id获取背景图片
     */
    protected Drawable getBackground(int resId){
        return getContext().getResources().getDrawable(resId);
    }

    /**
     * 处理内部点击事件
     * 可用于处理点击去重
     * @return 是否消费掉了此次点击事件
     */
    protected boolean click(View v, int position){
        return false;
    }

    private long lastClickTime;

    public boolean fastClick(int dur){
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if(0 < timeD && timeD < dur) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }
}