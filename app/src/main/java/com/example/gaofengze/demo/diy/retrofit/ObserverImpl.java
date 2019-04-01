package com.example.gaofengze.demo.diy.retrofit;

import com.example.gaofengze.demo.base.bean.ResultBean;
import com.example.gaofengze.demo.callBack.BaseCallBack;
import com.example.gaofengze.demo.callBack.CallBack;
import com.example.gaofengze.demo.callBack.CallBackData;
import com.example.gaofengze.demo.data.NetWorkData;

/**
 * Created by gaofengze on 2019/3/21
 */
public class ObserverImpl<T> extends BaseObserver<T> {
    private BaseCallBack callBack;
    public ObserverImpl(BaseCallBack callBackData) {
        super(callBackData);
        callBack = callBackData;
    }

    @Override
    public void onNext(T t) {
        if(NetWorkData.success.equals(((ResultBean)t).getCode())){
            if(callBack instanceof CallBack){
                ((CallBack) callBack).success();
            }else if(callBack instanceof CallBackData){
                ((CallBackData) callBack).success(((ResultBean)t).getData());
            }
        }else{
            callBack.error(((ResultBean)t).getCode(), ((ResultBean)t).getMsg());
        }

    }
}
