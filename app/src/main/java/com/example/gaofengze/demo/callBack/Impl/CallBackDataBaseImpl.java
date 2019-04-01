package com.example.gaofengze.demo.callBack.Impl;


import com.example.gaofengze.demo.base.viewIF.IBaseView;
import com.example.gaofengze.demo.callBack.CallBackData;

/**
 * Created by gaofengze on 2018/12/4
 */
public abstract class CallBackDataBaseImpl <T,V extends IBaseView> extends BaseCallBackImpl<V> implements CallBackData<T> {

    public CallBackDataBaseImpl(V v) {
        super(v);
    }

    public CallBackDataBaseImpl(V v, boolean showLoading) {
        super(v, showLoading);
    }
}
