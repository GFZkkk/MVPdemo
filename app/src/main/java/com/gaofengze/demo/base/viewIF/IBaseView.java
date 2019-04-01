package com.gaofengze.demo.base.viewIF;

public interface IBaseView {
    void networkerror(String code);
    void showProgress();
    void hideProgress();
    void error(String code, String err);
}
