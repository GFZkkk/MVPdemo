package com.example.gaofengze.demo.callBack.Impl;

import com.example.gaofengze.demo.callBack.CallBackData;
import com.example.gaofengze.demo.view.IVoidView;

/**
 * Created by gaofengze on 2018/12/3
 */
public abstract class CallBackDataImpl<T,V extends IVoidView> extends BaseCallBackImpl<V> implements CallBackData<T> {
     private V v;

    public CallBackDataImpl(V v) {
        super(v);
        this.v = v;
    }

    @Override
    public void error(String err) {
        v.error(err);
    }
}
