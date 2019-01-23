package com.example.gaofengze.demo.model.Impl;

import com.example.gaofengze.demo.base.BaseObserver;
import com.example.gaofengze.demo.callBack.CallBack;
import com.example.gaofengze.demo.data.NetWorkCode;
import com.example.gaofengze.demo.model.ICodeLogin;
import com.example.gaofengze.demo.model.IPwdLogin;
import com.example.gaofengze.demo.model.bean.Login;
import com.example.gaofengze.demo.model.bean.ResultBean;
import com.example.gaofengze.demo.network.NetWork;
import com.example.gaofengze.demo.tools.RxUtil;
import com.example.gaofengze.demo.tools.UserUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaofengze on 2019/a1/7
 */
public class LoginImpl implements ICodeLogin, IPwdLogin {

    @Override
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
                        if(NetWorkCode.success.equals(loginResultBean.getCode())){
                            UserUtil.saveUserInfo(loginResultBean.getData());
                            callBack.success();
                        }else{
                            callBack.error(loginResultBean.getMsg());
                        }
                    }
                });
    }

    @Override
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
                if(NetWorkCode.success.equals(loginResultBean.getCode())){
                    UserUtil.saveUserInfo(loginResultBean.getData(),pwd);
                    callBack.success();
                }else{
                    callBack.error(loginResultBean.getMsg());
                }
            }
        });
    }
}
