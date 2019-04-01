package com.gaofengze.demo.network;


import com.gaofengze.demo.base.bean.ResultBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaofengze on 2019/1/12
 */
public interface VCodeApi {
    /**
     * 验证验证码
     */
    @GET("api-third-party/ivalleytech.lavie_third_party/sendCode/checkPhoneCode")
    Observable<ResultBean<Object>> checkVcode(@Query("phone") String tel, @Query("code") String code);

    /**
     * 发送验证码
     */
    @GET("api-third-party/ivalleytech.lavie_third_party/sendCode/phone")
    Observable<ResultBean<Object>> sendVcode(@Query("phone") String tel);

}
