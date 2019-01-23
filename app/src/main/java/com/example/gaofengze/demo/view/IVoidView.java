package com.example.gaofengze.demo.view;

import com.example.gaofengze.demo.base.IBaseView;

/**
 * Created by gfz on 2018/10/11
 */
public interface IVoidView extends IBaseView {
    void success();
    void error(String err);
}
