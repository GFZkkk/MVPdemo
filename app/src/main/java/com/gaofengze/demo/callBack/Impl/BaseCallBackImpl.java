package com.gaofengze.demo.callBack.Impl;


import com.gaofengze.demo.base.viewIF.IBaseView;
import com.gaofengze.demo.callBack.BaseCallBack;

/**
 * Created by gaofengze on 2018/12/3
 */
public class BaseCallBackImpl<V extends IBaseView> implements BaseCallBack {
    public V v;
    public boolean showLoading;

    public BaseCallBackImpl(V v) {
        this(v,false);
    }

    public BaseCallBackImpl(V v, boolean showLoading) {
        this.v = v;
        this.showLoading = showLoading;
    }

    @Override
    public void onSubscribe() {
        if (showLoading){
            v.showProgress();
        }
    }

    @Override
    public void error(String code, String err) {
        v.error(code,err);
    }

    @Override
    public void onFailure(String code) {
        v.networkerror(code);
    }

    @Override
    public void onComplete() {
        v.hideProgress();
    }

}
