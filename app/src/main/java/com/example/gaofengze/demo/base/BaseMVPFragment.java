package com.example.gaofengze.demo.base;

import android.os.Bundle;

import com.example.gaofengze.demo.tools.LoadingUtil;
import com.example.gaofengze.demo.tools.NetWorkHelper;

import androidx.annotation.Nullable;


/**
 * Created by gaofengze on 2018/11/27
 */
public abstract class BaseMVPFragment<V extends IBaseView,T extends BasePresenter<V>>
        extends BaseFragment implements IBaseView {
    public T presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = BindPresenter();
        presenter.attachView((V)this);
    }
    protected abstract T BindPresenter();
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


    @Override
    public void showProgress() {
        LoadingUtil.loadWait(getActivity());
    }

    @Override
    public void hideProgress() {
        LoadingUtil.loadFinish();
    }

    @Override
    public void networkerror(String code) {
        NetWorkHelper.showError(code);
    }
}
