package com.example.gaofengze.demo.model;


import com.example.gaofengze.demo.callBack.CallBack;

/**
 * Created by gaofengze on 2019/1/12
 */
public interface IForgetPwd {
    void forgetPwd(String tel, String pwd, CallBack callBack);
}
