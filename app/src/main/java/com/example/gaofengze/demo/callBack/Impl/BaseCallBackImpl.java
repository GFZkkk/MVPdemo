package com.example.gaofengze.demo.callBack.Impl;

import com.example.gaofengze.demo.base.IBaseView;
import com.example.gaofengze.demo.callBack.BaseCallBack;
import com.example.gaofengze.demo.tools.DeBugUtil;

/**
 * Created by gaofengze on 2018/12/3
 */
public abstract class BaseCallBackImpl<V extends IBaseView> implements BaseCallBack {
    private V v;

    public BaseCallBackImpl(V v) {
        this.v = v;
    }

    @Override
    public void onSubscribe() {
        v.showProgress();
    }

    @Override
    public void onFailure(String code) {
        DeBugUtil.error("callback"+code);
        v.networkerror(code);
        v.hideProgress();
    }

    @Override
    public void onComplete() {
        v.hideProgress();
    }
}
