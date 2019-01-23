package com.example.gaofengze.demo.base;

import android.os.Bundle;

import com.example.gaofengze.demo.tools.DeBugUtil;
import com.example.gaofengze.demo.tools.LoadingUtil;
import com.example.gaofengze.demo.tools.NetWorkHelper;

import androidx.annotation.Nullable;


/**
 * Created by gfz on 2018/10/10
 */
public abstract class BaseMVPActivity<V extends IBaseView,T extends BasePresenter<V>> extends BaseActivity implements IBaseView {
    public T presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = BindPresenter();
        presenter.attachView((V)this);
    }

    /**
     * 绑定presenter
     * @return
     */
    protected abstract T BindPresenter();

    /**
     * 解绑presenter
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    /**
     * 显示加载
     */
    @Override
    public void showProgress() {
        LoadingUtil.loadWait(this);
    }

    /**
     * 隐藏加载
     */
    @Override
    public void hideProgress() {
        LoadingUtil.loadFinish();
    }

    /**
     * 处理网络或服务器异常
     */
    @Override
    public void networkerror(String code) {
        DeBugUtil.error("activity"+code);
        NetWorkHelper.showError(code);

    }
}
