package com.example.gaofengze.demo.base;


import android.view.View;

import com.example.gaofengze.demo.base.adapter.BaseRecyclerViewAdapter;
import com.example.gaofengze.demo.base.bean.PageList;
import com.example.gaofengze.demo.base.mvp.BaseMVPFragment;
import com.example.gaofengze.demo.base.mvp.BasePresenter;
import com.example.gaofengze.demo.base.viewIF.IDataView;
import com.example.gaofengze.demo.util.tools.UUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import androidx.annotation.NonNull;

/**
 * Created by gaofengze on 2019/3/26
 */
public abstract class LoadMoreFragment<T, V extends IDataView<PageList<T>>, P extends BasePresenter<V>> extends BaseMVPFragment<V,P>
        implements IDataView<PageList<T>>{
    protected int pageNum;
    protected int typeLoading;

    /**
     * 初始化加载数据
     */
    public void initLoad(){
        pageNum = 1;
        typeLoading = 1;
        getAdapter().clear();
        getData(pageNum,false);
    }

    /**
     * 失败后刷新
     */
    public void refreshLoad(){
        pageNum = 1;
        typeLoading = 1;
        getAdapter().clear();
        getData(pageNum,false);
        networkError(false);
    }

    /**
     * 设置刷新监听
     */
    public void setOnRefreshLoadMoreListener(){
        getRefresh().setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                typeLoading = 0;
                getData(pageNum,false);
            }
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initLoad();
            }
        });
    }

    @Override
    public void showData(PageList<T> data) {
        if(data.getNextPage() != 0){
            pageNum = data.getNextPage();
        }
        if(typeLoading == 0){
            getAdapter().addAll(data.getList());
            if(!data.isLastPage()){
                getRefresh().finishLoadMore();
            }else{
                getRefresh().finishLoadMoreWithNoMoreData();
            }
        }else{
            getAdapter().refresh(data.getList());
            getRefresh().finishRefresh();
            getRefresh().setNoMoreData(data.isLastPage());
        }

    }

    @Override
    public void networkerror(String code) {
        if(typeLoading == 0){
            getRefresh().finishLoadMore(false);
        }else{
            getRefresh().finishRefresh();
            networkError(true);
        }
    }

    /**
     * 刷新失败
     */
    public void networkError(boolean isError){
        if (getNetworkErrorView() != null) {
            getRefresh().setVisibility(UUtil.getVisibilityByBoolean(!isError));
            getNetworkErrorView().setVisibility(UUtil.getVisibilityByBoolean(isError));
        }
    }

    /**
     * 获得刷新失败view
     */
    public View getNetworkErrorView(){
        return null;
    }

    /**
     * 获取刷新控件
     */
    public abstract SmartRefreshLayout getRefresh();

    /**
     * 获取适配器
     */
    public abstract BaseRecyclerViewAdapter<T> getAdapter();

    /**
     * 获取数据
     */
    public abstract void getData(int num, boolean loading);
}
