package com.example.gaofengze.demo.base;

import java.lang.ref.WeakReference;

/**
 * Created by gfz on 2018/10/12
 */
public class BasePresenter<T> {
    protected WeakReference<T> mViewRef;

    /**
     * 绑定view
     * @param view view
     */
    public void attachView(T view){
        mViewRef = new WeakReference<T>(view);
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
