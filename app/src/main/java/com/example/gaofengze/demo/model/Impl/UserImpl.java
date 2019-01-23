package com.example.gaofengze.demo.model.Impl;

import com.example.gaofengze.demo.base.BaseObserver;
import com.example.gaofengze.demo.callBack.CallBack;
import com.example.gaofengze.demo.data.NetWorkCode;
import com.example.gaofengze.demo.model.IChangePwd;
import com.example.gaofengze.demo.model.IForgetPwd;
import com.example.gaofengze.demo.model.bean.Login;
import com.example.gaofengze.demo.model.bean.ResultBean;
import com.example.gaofengze.demo.network.NetWork;
import com.example.gaofengze.demo.tools.LoginHelper;
import com.example.gaofengze.demo.tools.RxUtil;
import com.example.gaofengze.demo.tools.SPUtil;
import com.example.gaofengze.demo.tools.UserUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaofengze on 2019/1/12
 */
public class UserImpl implements IChangePwd, IForgetPwd {
    @Override
    public void changepwd(final String pwd, final CallBack callBack) {
        Map<String,String> map  =new HashMap<>();
        map.put("userPwd",pwd);
        NetWork.getUserApi()
                .changePwd(UserUtil.getToken(),map)
                .compose(RxUtil.<ResultBean<Object>>applySchedulers())
                .subscribe(new BaseObserver<ResultBean<Object>>(callBack) {
                    @Override
                    public void onNext(ResultBean<Object> objectResultBean) {
                        if(NetWorkCode.success.equals(objectResultBean.getCode())){
                            SPUtil.putString("pwd",pwd);
                            callBack.success();
                        }else if(NetWorkCode.logout.equals(objectResultBean.getCode())){
                            LoginHelper.logout();
                        }else{
                            callBack.error(objectResultBean.getMsg());
                        }
                    }
                });
    }

    @Override
    public void forgetPwd(String tel, final String pwd, final CallBack callBack) {
        Map<String,String> map = new HashMap<>();
        map.put("userPhone",tel);
        map.put("userPwd",pwd);
        NetWork.getUserApi()
                .forgetPwd(map)
                .compose(RxUtil.<ResultBean<Login>>applySchedulers())
                .subscribe(new BaseObserver<ResultBean<Login>>(callBack) {
                    @Override
                    public void onNext(ResultBean<Login> objectResultBean) {
                        if(NetWorkCode.success.equals(objectResultBean.getCode())){
                            UserUtil.saveUserInfo(objectResultBean.getData(),pwd);
                            callBack.success();
                        }else{
                            callBack.error(objectResultBean.getMsg());
                        }
                    }
                });
    }
}
