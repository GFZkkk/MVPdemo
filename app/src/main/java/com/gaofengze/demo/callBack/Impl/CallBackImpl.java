package com.gaofengze.demo.callBack.Impl;


import com.gaofengze.demo.base.viewIF.IVoidView;

/**
 * Created by gaofengze on 2018/12/3
 */
public class CallBackImpl extends CallBackBaseImpl<IVoidView>{

    public CallBackImpl(IVoidView iVoidView) {
        super(iVoidView);
    }

    public CallBackImpl(IVoidView iVoidView, boolean showLoading) {
        super(iVoidView, showLoading);
    }

    @Override
    public void success() {
        v.success();
    }
}
