package com.example.gaofengze.demo.model;


import com.example.gaofengze.demo.callBack.CallBackData;

/**
 * Created by gaofengze on 2019/1/12
 */
public interface ISend<T> {
    void send(String tel, CallBackData<T> callBack);
}
