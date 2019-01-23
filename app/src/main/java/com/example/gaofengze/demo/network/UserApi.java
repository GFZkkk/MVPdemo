package com.example.gaofengze.demo.network;

import com.example.gaofengze.demo.model.bean.Login;
import com.example.gaofengze.demo.model.bean.ResultBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by gaofengze on 2019/1/12
 */
public interface UserApi {
    /**
     * 登录页面的修改密码
     */
    @POST("api/v1/ivalleytech.lavie_zuul/users/forgetPwdLogin")
    Observable<ResultBean<Login>> forgetPwd(@Body Map<String, String> map);

    /**
     * 用户界面的修改密码
     */
    @POST("api-user/api/v1/ivalleytech.lavie_user/users/updatePwd")
    Observable<ResultBean<Object>> changePwd(@Header("Authorization") String token, @Body Map<String, String> body);



}
