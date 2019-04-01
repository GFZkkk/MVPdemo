package com.gaofengze.demo.base.mvp;


import android.os.Bundle;

import com.gaofengze.demo.base.BaseActivity;
import com.gaofengze.demo.base.viewIF.IBaseView;
import com.gaofengze.demo.data.NetWorkData;
import com.gaofengze.demo.util.hepler.NetWorkErrorHelper;
import com.gaofengze.demo.util.tools.KProgressHUDUtil;

import androidx.annotation.Nullable;

/**
 * Created by gfz on 2018/10/10
 */
public abstract class BaseMVPActivity<V extends IBaseView,P extends BasePresenter<V>> extends BaseActivity implements IBaseView {
    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = BindPresenter();
        presenter.attachView((V)this);
    }

    @Override
    public void error(String code, String err) {
        if(NetWorkData.logout.equals(code)){
            // TODO: 2019/3/28 登出提示
            logout();
        }
    }

    /**
     * 绑定presenter
     */
    protected abstract P BindPresenter();

    /**
     * 解绑presenter
     */
    @Override
    protected void onDestroy() {
        KProgressHUDUtil.loadFinish();
        presenter.detachView();
        super.onDestroy();

    }


    /**
     * 显示加载
     */
    @Override
    public void showProgress() {
        KProgressHUDUtil.loadWait(this);
    }

    /**
     * 隐藏加载
     */
    @Override
    public void hideProgress() {
        KProgressHUDUtil.loadFinish();
    }

    /**
     * 处理网络或服务器异常
     */
    @Override
    public void networkerror(String code) {
        NetWorkErrorHelper.showError(code);
    }
}
