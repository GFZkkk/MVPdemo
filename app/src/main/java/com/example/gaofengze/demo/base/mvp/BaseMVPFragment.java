package com.example.gaofengze.demo.base.mvp;

import android.app.Activity;
import android.os.Bundle;

import com.example.gaofengze.demo.base.BaseActivity;
import com.example.gaofengze.demo.base.BaseFragment;
import com.example.gaofengze.demo.base.viewIF.IBaseView;
import com.example.gaofengze.demo.data.NetWorkData;
import com.example.gaofengze.demo.util.hepler.NetWorkErrorHelper;
import com.example.gaofengze.demo.util.tools.DeBugUtil;
import com.example.gaofengze.demo.util.tools.KProgressHUDUtil;

import androidx.annotation.Nullable;


/**
 * Created by gaofengze on 2018/11/27
 */
public abstract class BaseMVPFragment<V extends IBaseView,T extends BasePresenter<V>> extends BaseFragment implements IBaseView {
    public T presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = BindPresenter();
        presenter.attachView((V)this);
    }
    protected abstract T BindPresenter();

    @Override
    public void error(String code, String err) {
        if(NetWorkData.logout.equals(code)){
            DeBugUtil.error("fragment 登出");
           Activity activity = getActivity();
           if(activity instanceof BaseActivity){
               ((BaseActivity) activity).logout();
           }
        }
    }

    @Override
    public void showProgress() {
        KProgressHUDUtil.loadWait(getActivity());
    }

    @Override
    public void hideProgress() {
        KProgressHUDUtil.loadFinish();
    }

    @Override
    public void networkerror(String code) {
        NetWorkErrorHelper.showError(code);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KProgressHUDUtil.loadFinish();
        presenter.detachView();
    }
}
