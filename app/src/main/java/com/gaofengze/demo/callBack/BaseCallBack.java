package com.gaofengze.demo.callBack;

/**
 * Created by gaofengze on 2018/11/30
 */
public interface BaseCallBack {
    void onSubscribe();
    void error(String code, String err);
    void onFailure(String code);
    void onComplete();
}
