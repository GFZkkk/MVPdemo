package com.gaofengze.demo.base.bean;

import java.util.List;

/**
 * Created by gaofengze on 2018/12/3
 */
public class ResultListBean<T>{
    private String code;
    private List<T> data;
    private String msg;

    public ResultListBean(String code, List<T> data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
