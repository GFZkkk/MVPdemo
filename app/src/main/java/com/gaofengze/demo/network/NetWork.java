package com.gaofengze.demo.network;

import com.gaofengze.demo.data.NetWorkData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gfz on 2018/10/16
 */
public class NetWork {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create(gson);
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    private static LoginApi loginApi;
    private static VCodeApi vCodeApi;

    /**
     * 默认注册
     */
    private static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(NetWorkData.baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    private static Retrofit getRetrofit(String url){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    /**
     * 登录
     */
    public static LoginApi getLoginApi(){
        if(loginApi == null){
            loginApi = getRetrofit().create(LoginApi.class);
        }
        return loginApi;
    }

    /**
     * 短信
     */
    public static VCodeApi getVCodeApi(){
        if(vCodeApi == null){
            vCodeApi = getRetrofit().create(VCodeApi.class);
        }
        return vCodeApi;
    }

}
