package com.example.gaofengze.demo.callBack.Impl;


import com.example.gaofengze.demo.base.viewIF.IDataView;

/**
 * Created by gaofengze on 2018/12/3
 */
public class CallBackDataImpl<T> extends CallBackDataBaseImpl<T, IDataView<T>>{

    public CallBackDataImpl(IDataView<T> tiDataView) {
        super(tiDataView);
    }

    public CallBackDataImpl(IDataView<T> tiDataView, boolean showLoading) {
        super(tiDataView, showLoading);
    }

    @Override
    public void success(T data) {
        v.showData(data);
    }
}
