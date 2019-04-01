package com.gaofengze.demo.callBack;

/**
 * Created by gaofengze on 2018/11/30
 */
public interface CallBackData<T> extends BaseCallBack {
    void success(T data);
}
