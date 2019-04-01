package com.example.gaofengze.demo.model.Impl;

import com.example.gaofengze.demo.callBack.CallBack;
import com.example.gaofengze.demo.data.NetWorkData;
import com.example.gaofengze.demo.diy.retrofit.BaseObserver;
import com.example.gaofengze.demo.diy.retrofit.RxUtil;
import com.example.gaofengze.demo.model.bean.Login;
import com.example.gaofengze.demo.base.bean.ResultBean;
import com.example.gaofengze.demo.network.NetWork;
import com.example.gaofengze.demo.util.tools.UserUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaofengze on 2019/a1/7
 */
public class LoginImpl {

    public void codeLogin(String tel, String code, final CallBack callBack) {
        Map<String,String> map = new HashMap<>();
        map.put("code",code);
        map.put("userPhone",tel);
        NetWork.getLoginApi()
                .codeLogin(map)
                .compose(RxUtil.<ResultBean<Login>>applySchedulers())
                .subscribe(new BaseObserver<ResultBean<Login>>(callBack) {
                    @Override
                    public void onNext(ResultBean<Login> loginResultBean) {
                        if(NetWorkData.success.equals(loginResultBean.getCode())){
                            UserUtil.saveUserInfo(loginResultBean.getData());
                            callBack.success();
                        }else{
                            callBack.error(loginResultBean.getCode(),loginResultBean.getMsg());
                        }
                    }
                });
    }

    public void pwdLogin(String tel, final String pwd, final CallBack callBack) {
        Map<String,String> map = new HashMap<>();
        map.put("userPhone",tel);
        map.put("userPwd",pwd);
        NetWork.getLoginApi()
                .pwdLogin(map)
                .compose(RxUtil.<ResultBean<Login>>applySchedulers())
        .subscribe(new BaseObserver<ResultBean<Login>>(callBack) {
            @Override
            public void onNext(ResultBean<Login> loginResultBean) {
                if(NetWorkData.success.equals(loginResultBean.getCode())){
                    UserUtil.saveUserInfo(loginResultBean.getData(),pwd);
                    callBack.success();
                }else{
                    callBack.error(loginResultBean.getCode(),loginResultBean.getMsg());
                }
            }
        });
    }
}
