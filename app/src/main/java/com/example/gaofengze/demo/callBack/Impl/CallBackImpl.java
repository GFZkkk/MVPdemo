package com.example.gaofengze.demo.callBack.Impl;

import com.example.gaofengze.demo.callBack.CallBack;
import com.example.gaofengze.demo.view.IVoidView;

/**
 * Created by gaofengze on 2018/12/3
 */
public class CallBackImpl<V extends IVoidView> extends BaseCallBackImpl<V> implements CallBack {
    private V v;

    public CallBackImpl(V v) {
        super(v);
        this.v = v;
    }

    @Override
    public void success() {
        v.success();
    }

    @Override
    public void error(String err) {
        v.error(err);
    }
}
