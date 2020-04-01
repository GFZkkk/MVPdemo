package com.gaofengze.demo.base.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;

import android.os.SystemClock;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaofengze.demo.data.App;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by gfz on 2019/10/19
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T>>{

    /**
     * 主要数据
     */
    private List<T> list;
    /**
     * 持有的context
     */
    private Context context;
    /**
     * 点击事件
     */
    protected OnItemClickListener listener;
    /**
     * 焦点下标
     */
    private int clickIndex;
    /**
     * 是否自动刷新点击的item
     */
    private boolean needAutoRefreshClickItem;
    /**
     * 是否自动设置当前点击的position为clickIndex
     */
    private boolean needAutoSetClickIndex;
    /**
     * 是否自动过滤空数据
     */
    private boolean needAutoFilterEmptyData;
    /**
     * 存储ViewType和ViewLayout的关系
     */
    protected SparseArray<Integer> viewHolderLayoutIds;

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
        this.list = new ArrayList<>();
        viewHolderLayoutIds = new SparseArray<>(10);
        if (list == null){
            list = getPreData();
            if (list == null){
                list = new ArrayList<>();
            }
        }
        addAllData(list);
        this.clickIndex = clickIndex;
        needAutoRefreshClickItem = false;
        needAutoSetClickIndex = true;
        needAutoFilterEmptyData = true;
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseRecyclerViewHolder<T> holder = getViewHolder(getViewByViewType(parent,viewType), viewType);
        setHolderListener(holder);
        return holder;
    }

    /**
     * 给holder提供数据
     * 绑定点击事件
     */
    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder<T> holder, int position) {
        holder.onBindViewHolder(getData(position),position);
    }

    public abstract BaseRecyclerViewHolder<T> getViewHolder(View view, int viewType);

    /**
     * 添加view类型以及对应的视图id
     * @param type 视图类型
     * @param layoutId 视图布局id
     */
    public void addItemType(int type, int layoutId){
        viewHolderLayoutIds.append(type,layoutId);
    }

    public void setLayoutId(int layoutId){
        viewHolderLayoutIds.append(0,layoutId);
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
        if (!isItemIndex(clickIndex)) return;
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
        if (isDataIndex(position)){
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
     * 是否过滤空数据
     */
    public void setNeedAutoFilterEmptyData(boolean needAutoFilterEmptyData) {
        this.needAutoFilterEmptyData = needAutoFilterEmptyData;
    }

    /**
     * 设置点击事件
     * @param v 点击的视图
     */
    public void clickEvent(View v, int position) {
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
        if(needAutoFilterEmptyData && data == null){
            return;
        }
        list.add(data);
    }

    /**
     * 添加数据列表
     */
    public void addAllData(List<T> dataList){
        if (needAutoFilterEmptyData){
            if (dataList != null){
                for (T data : dataList){
                    addData(data);
                }
            }
        }else{
            list.addAll(dataList);
        }
    }

    public void setData(List<T> data){
        if (data != null){
            list = data;
        }else{
            clear();
        }
    }

    /**
     * 设置某个位置的数据
     */
    public void setData(int position, T data){
        if (isDataIndex(position)){
            if (data == null && needAutoFilterEmptyData){
                removeData(position);
            }else{
                list.set(position, data);
            }
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
        if (isDataIndex(position)){
            list.remove(position);
        }
    }

    public int getIndex(T data){
        return list.indexOf(data);
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
        removeData(position);
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
     * 适用于提前加载数据的情况
     */
    protected List<T> getPreData(){
        return null;
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
     * 根据viewType获取view
     */
    protected View getViewByViewType(ViewGroup viewGroup, int viewType){
        Integer layoutId = viewHolderLayoutIds.get(viewType);
        if (layoutId == null) return null;
        return getView(viewGroup,layoutId);
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
    protected void setDisplay(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
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
        if (isItemIndex(position)){
            view.setOnClickListener(v -> clickEvent(view, position));
        }
    }

    /**
     * 设置item中控件的点击事件
     */
    protected void setHolderListener(BaseRecyclerViewHolder holder){
        View view = holder.itemView;
        view.setOnClickListener(v -> clickEvent(view, holder.getLayoutPosition()));
    }

    /**
     * 是否是数组下标
     */
    protected boolean isDataIndex(int position){
        return position > -1 && position < list.size();
    }

    /**
     * 是否是item下标
     */
    protected boolean isItemIndex(int position){
        return position > -1 && position < getItemCount();
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
        return App.getInstance();
    }

    /**
     * 根据资源id获取颜色
     */
    protected int getColor(int resId){
        return ContextCompat.getColor(getContext(), resId);
    }

    /**
     * 根据资源id获取图片
     */
    protected Drawable getDrawable(int resId){
        return ContextCompat.getDrawable(getContext(),resId);
    }

    /**
     * 根据资源id获取图片
     */
    protected Drawable getDrawableWithBounds(int resId){
        Drawable drawable = ContextCompat.getDrawable(getContext(),resId);
        if (drawable != null){
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            return drawable;
        }else{
            return null;
        }
    }

    /**
     * 处理内部点击事件
     * 可用于处理点击去重
     * @return 是否消费掉了此次点击事件
     */
    protected boolean click(View v, int position){
        return false;
    }

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

    private static long lastClickTime;

    public static boolean fastClick(int dur){
        long time = SystemClock.elapsedRealtime();
        long timeD = time - lastClickTime;
        if(0 < timeD && timeD < dur) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
