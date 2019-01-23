package com.example.gaofengze.demo.model;


import com.example.gaofengze.demo.callBack.CallBack;

/**
 * Created by gaofengze on 2019/1/12
 */
public interface ICheckVCode {
    void check(String tel, String code, CallBack callBack);
}
