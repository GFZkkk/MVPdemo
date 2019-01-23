package com.example.gaofengze.demo.base;

import com.example.gaofengze.demo.callBack.BaseCallBack;
import com.example.gaofengze.demo.data.NetWorkCode;
import com.example.gaofengze.demo.tools.DeBugUtil;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by gaofengze on 2018/12/3
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private BaseCallBack callBackData;

    public BaseObserver(BaseCallBack callBackData) {
        this.callBackData = callBackData;
    }

    @Override
    public void onSubscribe(Disposable d) {
        callBackData.onSubscribe();
    }

    @Override
    public void onError(Throwable e) {
        DeBugUtil.error("msg:"+e.getMessage());
        String msg = e.getMessage().contains(NetWorkCode.logout) ? NetWorkCode.logout : "";
        callBackData.onFailure(msg);
        callBackData.onComplete();
        if(e instanceof HttpException){
            ResponseBody body = ((HttpException) e).response().errorBody();
            try {
                DeBugUtil.error(body.string());
            } catch (IOException IOe) {
                IOe.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {
        callBackData.onComplete();
    }
}
