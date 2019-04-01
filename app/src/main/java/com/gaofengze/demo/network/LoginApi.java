package com.gaofengze.demo.network;

import com.gaofengze.demo.base.bean.ResultBean;
import com.gaofengze.demo.model.bean.Login;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by gaofengze on 2019/1/12
 */
public interface LoginApi {
    @POST("api/v1/ivalleytech.lavie_zuul/users/codeLogin")
    Observable<ResultBean<Login>> codeLogin(@Body Map<String, String> map);

    @POST("api/v1/ivalleytech.lavie_zuul/users/pwdLogin")
    Observable<ResultBean<Login>> pwdLogin(@Body Map<String, String> map);


}
