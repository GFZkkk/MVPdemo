package com.example.gaofengze.demo.model.Impl;


import com.example.gaofengze.demo.base.BaseObserver;
import com.example.gaofengze.demo.callBack.CallBack;
import com.example.gaofengze.demo.callBack.CallBackData;
import com.example.gaofengze.demo.data.NetWorkCode;
import com.example.gaofengze.demo.model.ICheckVCode;
import com.example.gaofengze.demo.model.ISend;
import com.example.gaofengze.demo.model.bean.ResultBean;
import com.example.gaofengze.demo.network.NetWork;
import com.example.gaofengze.demo.tools.RxUtil;

/**
 * Created by gaofengze on 2019/1/12
 */
public class VCodeImpl implements ISend<String>, ICheckVCode {

    @Override
    public void check(String tel, String code, final CallBack callBack) {
        NetWork.getVCodeApi()
                .checkVcode(tel,code)
                .compose(RxUtil.<ResultBean<Object>>applySchedulers())
                .subscribe(new BaseObserver<ResultBean<Object>>(callBack) {
                    @Override
                    public void onNext(ResultBean<Object> objectResultBean) {
                        if(NetWorkCode.success.equals(objectResultBean.getCode())){
                            callBack.success();
                        }else{
                            callBack.error(objectResultBean.getMsg());
                        }
                    }
                });
    }

    @Override
    public void send(String tel, final CallBackData<String> callBack) {
        NetWork.getVCodeApi()
                .sendVcode(tel)
                .compose(RxUtil.<ResultBean<Object>>applySchedulers())
                .subscribe(new BaseObserver<ResultBean<Object>>(callBack) {
                    @Override
                    public void onNext(ResultBean<Object> objectResultBean) {
                        if(NetWorkCode.success.equals(objectResultBean.getCode())){
                            callBack.success(objectResultBean.getMsg());
                        }else{
                            callBack.error(objectResultBean.getMsg());
                        }
                    }
                });
    }
}
