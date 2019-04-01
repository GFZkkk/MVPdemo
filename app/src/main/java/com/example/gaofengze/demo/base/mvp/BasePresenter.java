package com.example.gaofengze.demo.base.mvp;

import java.lang.ref.WeakReference;

/**
 * Created by gfz on 2018/10/12
 */
public class BasePresenter<V> {
    protected WeakReference<V> mViewRef;

    /**
     * 绑定view
     * @param view view
     */
    public void attachView(V view){
        mViewRef = new WeakReference<>(view);
    }

    /**
     * 解绑view
     */
    public void detachView(){
        if (mViewRef!=null){
            mViewRef.clear();
        }
    }
}
