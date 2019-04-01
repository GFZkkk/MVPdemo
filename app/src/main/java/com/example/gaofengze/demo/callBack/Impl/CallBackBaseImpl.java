package com.example.gaofengze.demo.callBack.Impl;


import com.example.gaofengze.demo.base.viewIF.IBaseView;
import com.example.gaofengze.demo.callBack.CallBack;

/**
 * Created by gaofengze on 2018/12/4
 */
public abstract class CallBackBaseImpl <V extends IBaseView> extends BaseCallBackImpl<V> implements CallBack {

    public CallBackBaseImpl(V v) {
        super(v);
    }

    public CallBackBaseImpl(V v, boolean showLoading) {
        super(v, showLoading);
    }
}
